package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Loot;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.objects.LootStatisticsItem;
import com.wikia.tibia.objects.LootWrapper;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.LootRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class FixLootStatistics {

    private static final boolean DEBUG_MODE = false;

    private final CreatureRepository creatureRepository;
    private final LootRepository lootRepository;
    private List<LootWrapper> loot = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    private Map<String, Creature> creaturePagesToUpdate = new ConcurrentHashMap<>(); // creature name, actual creature
    private static final Map<String, String> DIFFERENTLY_NAMED_ITEMS = Map.of(
            "Skull", "Skull (Item)",
            "Black Skull", "Black Skull (Item)",
            "Dead Snake", "Dead Snake (Item)",
            "Dead Frog", "Dead Frog (Item)",
            "Clusters of Solace", "Cluster of Solace",
            "Bag With Stolen Gold", "Bag with Stolen Gold"
    );
    private static Map<String, List<String>> forbiddenCreaturesAndLoot = new HashMap<>();

    static {
        forbiddenCreaturesAndLoot.put("Demon", List.of("Small Stone", "Bone", "Leather Armor", "Mouldy Cheese"));
        forbiddenCreaturesAndLoot.put("Mountain Troll", List.of("Bunch of Troll Hair"));
        forbiddenCreaturesAndLoot.put("Minotaur Bruiser", List.of("Minotaur Horn", "Minotaur Leather"));
        forbiddenCreaturesAndLoot.put("Muglex Clan Footman", List.of("Goblin Ear"));
        forbiddenCreaturesAndLoot.put("Woodling", List.of("Piece of Swampling Wood", "Swampling Moss"));
        forbiddenCreaturesAndLoot.put("Meadow Strider", List.of("Seeds", "Marsh Stalker Beak", "Marsh Stalker Feather"));
        forbiddenCreaturesAndLoot.put("Dawnfly", List.of("Damselfly Eye", "Damselfly Wing"));
        forbiddenCreaturesAndLoot.put("Scar Tribe Shaman", List.of("Shamanic Hood", "Orc Tooth", "Broken Shamanic Staff"));
        forbiddenCreaturesAndLoot.put("Scar Tribe Warrior", List.of("Skull Belt", "Orc Leather"));
        forbiddenCreaturesAndLoot.put("Brittle Skeleton", List.of("Pelvis Bone"));
        forbiddenCreaturesAndLoot.put("Minotaur Poacher", List.of("Broken Crossbow", "Minotaur Leather", "Minotaur Horn"));
        forbiddenCreaturesAndLoot.put("Juvenile Cyclops", List.of("Cyclops Toe"));
        forbiddenCreaturesAndLoot.put("Minotaur Occultist", List.of("Minotaur Horn", "Purple Robe"));
        forbiddenCreaturesAndLoot.put("Lesser Fire Devil", List.of("Small Pitchfork"));
        forbiddenCreaturesAndLoot.put("Troll Marauder", List.of("Bunch of Troll Hair", "Trollroot"));
    }

    public FixLootStatistics() {
        this.creatureRepository = new CreatureRepository();
        this.lootRepository = new LootRepository();
    }

    public FixLootStatistics(CreatureRepository creatureRepository, LootRepository lootRepository) {
        this.creatureRepository = creatureRepository;
        this.lootRepository = lootRepository;
    }

    /**
     * Fix creature pages by adding loot from their loot statistics pages.
     * 1. Get a list of Loot Statistics pages and sort it.
     * 2. Filter out loot statistics pages with no loot.
     * 2. For every loot statistics page, go over all its loot items and add them to the corresponding creature pages' loot list, if not already present.
     */
    public Map<String, Creature> checkLootStatistics() {
        getLootPages().stream()
                .map(LootWrapper::getMergedLoot)
                .filter(lootPage -> lootPage.getName() != null && !lootPage.isEmpty())
                .sorted(Comparator.comparing(Loot::getName))
                .peek(c -> log.debug("Processing loot statistics page of creature: {}", c.getName()))
                .forEach(lootPage -> {
                    final Creature correspondingCreature = getCreature(lootPage.getName())
                            .orElseGet(() -> {
                                log.error("Could not find creature with pageName '{}' and name '{}' in collection of creatures.", lootPage.getPageName(), lootPage.getName());
                                return null;
                            });

                    if (correspondingCreature != null) {
                        lootPage.getLoot()
                                .forEach(lootStatisticsItem -> {
                                    boolean lootStatisticsItemExistsInCreatureLootList = correspondingCreature.getLoot().stream()
                                            .anyMatch(lootItem -> Objects.equals(lootItem.getItemName(), replaceSomeNames(lootStatisticsItem.getItemName())));

                                    // loot item exists on loot statistics page, but not on creature page. Add it if applicable.
                                    if (!lootStatisticsItemExistsInCreatureLootList && shouldAddLootItemToCreature(lootStatisticsItem, correspondingCreature)) {
                                        log.info("Adding item '{}' to loot list of creature '{}'.", lootStatisticsItem.getItemName(), correspondingCreature.getName());

                                        final LootItem newLootItem = LootItem.builder()
                                                .itemName(lootStatisticsItem.getItemName())
                                                .build();

                                        if (!creaturePagesToUpdate.containsKey(correspondingCreature.getName())) {
                                            // creature not already in creaturePages cache, add it
                                            correspondingCreature.getLoot().add(newLootItem);
                                            creaturePagesToUpdate.put(correspondingCreature.getName(), correspondingCreature);
                                        } else {
                                            // creature already present in creaturePages cache, get the existing creature and add the new loot item
                                            final Creature existingCreature = creaturePagesToUpdate.get(correspondingCreature.getName());
                                            existingCreature.getLoot().add(newLootItem);
                                        }
                                    }
                                });
                    }
                });

        saveCreatureArticles();

        return creaturePagesToUpdate;
    }

    @SuppressWarnings("unchecked")
    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            var tryList = creatureRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                creatures = (List<Creature>) tryList.get();
            } else {
                log.error("Failed to get a list of creatures: %s", tryList.getCause());
            }
        }
        return creatures;
    }

    private Optional<Creature> getCreature(String creatureName) {
        return getCreatures().stream()
                .filter(i -> Objects.equals(i.getName(), creatureName))
                .findAny();
    }

    @SuppressWarnings("unchecked")
    private List<LootWrapper> getLootPages() {
        if (loot == null || loot.isEmpty()) {
            var tryList = lootRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                loot = (List<LootWrapper>) tryList.get();
            } else {
                log.error("Failed to get a list of lootPages: %s", tryList.getCause());
            }
        }
        return loot;
    }

    private void saveCreatureArticles() {
        log.info("If debug mode is disabled, {} creature articles are being edited NOW.", creaturePagesToUpdate.size());
        creaturePagesToUpdate.forEach((key, value) -> {
                    creatureRepository.saveWikiObject(value, "[bot] adding missing item(s) to loot list.", DEBUG_MODE);
                    pauseForABit();
                }
        );
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Some items are actually named differently on the wiki, because of page names conflicts
     */
    private String replaceSomeNames(String itemName) {
        if (DIFFERENTLY_NAMED_ITEMS.containsKey(itemName)) {
            return DIFFERENTLY_NAMED_ITEMS.get(itemName);
        }
        return itemName;
    }

    /**
     * There are a few reasons when NOT to add a loot item to a creature:
     * - the loot statistics item name is "Empty", that is not relevant on the creature's loot page
     * - the loot statistics data is incorrect, e.g. with Demon / Demon (Goblin)
     */
    private boolean shouldAddLootItemToCreature(LootStatisticsItem lootStatisticsItem, Creature correspondingCreature) {
        return !"Empty".equals(lootStatisticsItem.getItemName()) && !forbiddenCombinationOfLootAndCreature(lootStatisticsItem, correspondingCreature);
    }

    /**
     * Example: https://tibia.fandom.com/wiki/Loot_Statistics:Demon contains certain loot items from https://tibia.fandom.com/wiki/Demon_(Goblin).
     * These loot items should not be added to the loot list of Demons, because they are wrong.
     */
    private boolean forbiddenCombinationOfLootAndCreature(LootStatisticsItem lootStatisticsItem, Creature correspondingCreature) {
        return forbiddenCreaturesAndLoot.entrySet().stream()
                .anyMatch(e -> e.getKey().equals(correspondingCreature.getName()) && e.getValue().contains(lootStatisticsItem.getItemName()));
    }
}
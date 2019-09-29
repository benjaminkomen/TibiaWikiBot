package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FixItems {

    private static final Logger LOG = LoggerFactory.getLogger(FixItems.class);
    private static final boolean DEBUG_MODE = false;
    private static final List<String> ORC_RAID_ITEMS = Arrays.asList("Amazon Armor", "Amazon Helmet", "Amazon Shield");

    private CreatureRepository creatureRepository;
    private ItemRepository itemRepository;
    private List<Item> items = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    private Map<String, Creature> creaturePagesToUpdate = new ConcurrentHashMap<>(); // creature name, actual creature

    public FixItems() {
        this.creatureRepository = new CreatureRepository();
        this.itemRepository = new ItemRepository();
    }

    public FixItems(CreatureRepository creatureRepository, ItemRepository itemRepository) {
        this.creatureRepository = creatureRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * 1. Get a list of Items and sort it.
     * 2. Filter out deprecated or event items.
     * 3. Filter out items with no dropped by listed.
     * 4. For every item, go over all its droppedby creatures.
     * 5. For every creature, add a backreference to the lootItem, unless already present.
     * 6. Save all the changes.
     * <p>
     * The end result is a list of Creatures with their LootTable extended.
     */
    @SuppressWarnings("squid:S3864") // usage of Stream.peek for debugging purposes is justified in this case
    public Map<String, Creature> checkItems() {

        getItems().stream()
                .sorted(Comparator.comparing(Item::getName))
                .filter(Item::notDeprecatedOrEvent)
                .filter(item -> item.getDroppedby() != null && !item.getDroppedby().isEmpty())
                .peek(i -> LOG.debug("Processing item: {}", i.getName()))
                .forEach(item -> item.getDroppedby().stream()
                        .map(creatureName -> {
                            final Optional<Creature> creature = getCreature(creatureName);
                            if (creature.isEmpty()) {
                                LOG.error("Could not find creature with name '{}' from item '{}' in collection" +
                                        " of creatures.", creatureName, item.getName());
                            }
                            return creature.orElse(null);
                        })
                        .filter(Objects::nonNull)
                        .forEach(creature -> addItemToLootTableOfCreature(item, creature))
                );

        saveCreatureArticles();

        return creaturePagesToUpdate;
    }

    @SuppressWarnings("unchecked")
    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            creatures = creatureRepository.getWikiObjects();
        }
        return creatures;
    }

    private Optional<Creature> getCreature(String creatureName) {
        return getCreatures().stream()
                .filter(i -> Objects.equals(i.getName(), creatureName))
                .findAny();
    }

    @SuppressWarnings("unchecked")
    private List<Item> getItems() {
        if (items == null || items.isEmpty()) {
            items = itemRepository.getWikiObjects();
        }
        return items;
    }

    /**
     * If the item is not already in the loot table of the creature and the creature is eligible for adding, add it.
     */
    private void addItemToLootTableOfCreature(Item item, Creature creature) {
        if (!creature.getLoot().contains(LootItem.fromName(item.getName())) && itemShouldBeAdded(creature.getName(), item.getName())) {
            LOG.info("Adding item '{}' to loot table of creature '{}'.", item.getName(), creature.getName());

            if (!creaturePagesToUpdate.containsKey(creature.getName())) {
                // creature not already in creaturePages cache, add it
                creature.getLoot().add(LootItem.fromName(item.getName()));
                creaturePagesToUpdate.put(creature.getName(), creature);
            } else {
                // creature already present in creaturePages cache, get the existing creature and add the new lootItem
                final Creature existingCreature = creaturePagesToUpdate.get(creature.getName());
                existingCreature.getLoot().add(LootItem.fromName(item.getName()));
            }
        }
    }

    /**
     * Exclude certain items/creatures.
     */
    private boolean itemShouldBeAdded(String creaturePageName, String lootItemNamePrecise) {
        return addOrcWarlordLootIfNotOrcRaidItem(creaturePageName, lootItemNamePrecise) &&
                addOldAndUsedBackpackIfNotOrc(creaturePageName, lootItemNamePrecise) &&
                addOrcTuskIfNotOrc(creaturePageName, lootItemNamePrecise);
    }

    private boolean addOrcWarlordLootIfNotOrcRaidItem(String creaturePageName, String lootItemNamePrecise) {
        if ("Orc Warlord".equals(creaturePageName)) {
            return !ORC_RAID_ITEMS.contains(lootItemNamePrecise);
        }
        return true;
    }

    /**
     * Do not add this item to the loot table of Orcs, only a specific Orc in Orc Fortress drops this item
     */
    private boolean addOldAndUsedBackpackIfNotOrc(String creaturePageName, String lootItemNamePrecise) {
        if ("Old and Used Backpack".equals(lootItemNamePrecise)) {
            return !creaturePageName.equals("Orc");
        }
        return true;
    }

    private boolean addOrcTuskIfNotOrc(String creaturePageName, String lootItemNamePrecise) {
        if ("Orc Tusk".equals(lootItemNamePrecise)) {
            return !creaturePageName.contains("Orc");
        }
        return true;
    }

    private void saveCreatureArticles() {
        LOG.info("If debug mode is disabled, {} creature articles are being edited NOW.", creaturePagesToUpdate.size());
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
}
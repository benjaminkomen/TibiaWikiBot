package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Loot;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.LootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FixLootStatistics {

    private static final Logger LOG = LoggerFactory.getLogger(FixLootStatistics.class);

    private static final boolean DEBUG_MODE = false;

    private CreatureRepository creatureRepository;
    private LootRepository lootRepository;
    private List<Loot> loot = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    private Map<String, Creature> creaturePagesToUpdate = new ConcurrentHashMap<>(); // creature name, actual creature

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
                .sorted(Comparator.comparing(Loot::getName))
                .filter(lootPage -> !lootPage.isEmpty())
                .peek(c -> LOG.debug("Processing loot statistics page of creature: {}", c.getName()))
                .forEach(lootPage -> {
                    Creature correspondingCreature = getCreature(lootPage.getName());

                    lootPage.getLoot()
                            .forEach(lootStatisticsItem -> {
                                boolean lootStatisticsItemExistsInCreatureLootList = correspondingCreature.getLoot().stream()
                                        .anyMatch(lootItem -> Objects.equals(lootItem.getItemName(), lootStatisticsItem.getItemName()));

                                // loot item exists on loot statistics page, but not on creature page. Add it.
                                if (!lootStatisticsItemExistsInCreatureLootList && !"Empty".equals(lootStatisticsItem.getItemName())) {

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
                });

        saveCreatureArticles();

        return creaturePagesToUpdate;
    }

    private Creature getCreature(String creaturePageName) {
        Object wikiObject = creatureRepository.getWikiObject(creaturePageName);

        if (wikiObject instanceof Creature) {
            return (Creature) wikiObject;
        } else {
            LOG.error("Failed to get wikiObject of type Creature with name {}", creaturePageName);
            throw new IllegalStateException(String.format("Failed to get wikiObject of type Creature with name %s", creaturePageName));
        }
    }

    @SuppressWarnings("unchecked")
    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            creatures = creatureRepository.getWikiObjects();
        }
        return creatures;
    }

    @SuppressWarnings("unchecked")
    private List<Loot> getLootPages() {
        if (loot == null || loot.isEmpty()) {
            loot = lootRepository.getWikiObjects();
        }
        return loot;
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
package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FixCreatures {

    private static final Logger LOG = LoggerFactory.getLogger(FixCreatures.class);

    private static final List ITEMS_WITH_NO_DROPPEDBY_LIST = Arrays.asList("Gold Coin", "Platinum Coin");
    private static final List EVENT_ITEMS = Arrays.asList(
            "Bunch of Winterberries",
            "Envelope from the Wizards",
            "Fireworks Rocket",
            "Party Trumpet",
            "Party Hat",
            "Silver Raid Token",
            "Golden Raid Token"
    );
    private static final List NON_EVENT_CREATURES_DROPPING_SNOWBALLS = Arrays.asList("Yeti", "Grynch Clan Goblin");
    private static final boolean DEBUG_MODE = false;

    private CreatureRepository creatureRepository;
    private Map<String, Item> itemPagesToUpdate = new ConcurrentHashMap<>(); // item name, actual item (for easy lookup)
    private ItemRepository itemRepository;
    private List<Item> items = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();

    public FixCreatures() {
        this.creatureRepository = new CreatureRepository();
        this.itemRepository = new ItemRepository();
    }

    /**
     * 1. Get a list of Creatures and sort it.
     * 2. Filter out deprecated or event creatures.
     * 3. Filter out creatures with no loot listed.
     * 4. For every creature, go over all its loot items.
     * 5. For every loot item, add a backreference to the creature, unless already present.
     * 6. Save all the changes.
     * <p>
     * The end result is a list of Items with their droppedby list extended.
     */
    public void checkCreatures() {
        getCreatures().stream()
                .sorted(Comparator.comparing(Creature::getName))
                .filter(creature -> !creature.isDeprecatedOrEvent())
                .filter(creature -> creature.getLoot() != null && !creature.getLoot().isEmpty())
                .peek(c -> LOG.debug("Processing creature: {}", c.getName()))
                .forEach(creature -> creature.getLoot().stream()
                        .map(lootItem -> {
                            final Optional<Item> item = getItem(lootItem.getItemName());
                            if (item.isEmpty()) {
                                LOG.error("Could not find loot item with name '{}' from creature '{}' in collection" +
                                        " of items.", lootItem.getItemName(), creature.getName());
                            }
                            return item.orElse(null);
                        })
                        .filter(Objects::nonNull)
                        .forEach(item -> addCreatureToDroppedByListOfItem(creature, item))
                );

        saveItemArticles();
    }

    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            creatures = creatureRepository.getCreatures();
        }
        return creatures;
    }

    private List<Item> getItems() {
        if (items == null || items.isEmpty()) {
            items = itemRepository.getItems();
        }
        return items;
    }

    private Optional<Item> getItem(String itemName) {
        return getItems().stream()
                .filter(i -> Objects.equals(i.getName(), itemName))
                .findAny();
    }

    private boolean itemShouldBeAdded(String creaturePageName, String lootItemNamePrecise) {
        if ("Snowball".equals(lootItemNamePrecise)) {
            return NON_EVENT_CREATURES_DROPPING_SNOWBALLS.contains(creaturePageName);
        }
        if (ITEMS_WITH_NO_DROPPEDBY_LIST.contains(lootItemNamePrecise)) {
            return false;
        }
        if (EVENT_ITEMS.contains(lootItemNamePrecise)) {
            return false;
        }
        return true;
    }

    /**
     * If the creature is not already on the droppedby list of the item and the item is eligible for adding, add it.
     * Also sort it.
     */
    private void addCreatureToDroppedByListOfItem(Creature creature, Item item) {
        if (!item.getDroppedby().contains(creature.getName()) && itemShouldBeAdded(creature.getName(), item.getName())) {
            LOG.info("Adding creature '{}' to droppedby list of item '{}'.", creature.getName(), item.getName());

            if (!itemPagesToUpdate.keySet().contains(item.getName())) {
                // item not already in itemPages cache, add it
                item.getDroppedby().add(creature.getName());
                Collections.sort(item.getDroppedby());
                itemPagesToUpdate.put(item.getName(), item);
            } else {
                // item already presetn in itemPages cache, get the existing item and add the new creature name
                final Item existingItem = itemPagesToUpdate.get(item.getName());
                existingItem.getDroppedby().add(creature.getName());
                Collections.sort(existingItem.getDroppedby());
            }
        }
    }

    private void saveItemArticles() {
        LOG.info("If debug mode is disabled, {} item articles are being edited NOW.", itemPagesToUpdate.size());
        if (!DEBUG_MODE) {
            itemPagesToUpdate.forEach((key, value) -> {
                itemRepository.saveItem(value, "[bot] adding missing creature(s) to droppedby list.");
                        pauseForABit();
                    }
            );
        }
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
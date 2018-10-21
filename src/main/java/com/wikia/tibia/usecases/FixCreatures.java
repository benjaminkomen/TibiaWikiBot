package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.ItemRepository;
import net.sourceforge.jwbf.core.contentRep.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixCreatures {

    private static final Logger log = LoggerFactory.getLogger(FixCreatures.class);

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
    private static final String REGEX_DROPPED_BY = "\\{\\{Dropped By\\|(.*?)}}";
    private static final boolean DEBUG_MODE = true;

    private CreatureRepository creatureRepository;
    private Map<String, Item> itemPagesToUpdate = new HashMap<>();
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
     * 5. For every loot item, make sure it has a backreference to the creature.
     */
    public void checkCreatures() {
//        CategoryMembersSimple categoryMembers = wikiArticleRepository.getMembersFromCategory("Creatures");

//        for (String creaturePageName : categoryMembers) {
//            Creature creature = (Creature) wikiArticleRepository.getWikiObject(creaturePageName);
//            if (creature != null && !creature.isDeprecatedOrEvent() && !creature.getLoot().isEmpty()) {
//                for (LootItem lootItem : creature.getLoot()) {
//                    checkIfCreatureNameIsPresent(creaturePageName, lootItem.getItemName());
//                }
//            }
//        }
//        saveItemArticles();

        getCreatures().stream()
                .sorted(Comparator.comparing(Creature::getName))
                .filter(creature -> !creature.isDeprecatedOrEvent())
                .filter(creature -> !creature.getLoot().isEmpty())
                .peek(c -> log.info("Processing creature: " + c.getName()))
                .forEach(creature -> creature.getLoot().stream()
                        .map(lootItem -> getItem(lootItem.getItemName()))
                        .forEach(item -> {
                            if (!item.getDroppedby().contains(creature.getName())) {
                                item.getDroppedby().add(creature.getName());
                                log.info("[bot] adding creature '{}' to item '{}'.", creature.getName(), item.getName());
                            }
                        })
                );

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

    private Item getItem(String itemName) {
        return getItems().stream()
                .filter(i -> Objects.equals(i.getName(), itemName))
                .findAny()
                .orElse(null);
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

    private String updateCreatureToDroppedByList(List<String> creatureNames, String newCreature) {
        creatureNames.add(newCreature);
        Collections.sort(creatureNames);
        String newDroppedByList = String.join("|", creatureNames);
        return "{{Dropped By|" + newDroppedByList + "}}";
    }

    private void addMissingCreatureNameToDroppedByList(String creaturePageName, Article itemPage, String textToInsert) {
        String itemPageText = itemPage.getText();
        Pattern p = Pattern.compile(REGEX_DROPPED_BY);
        Matcher m = p.matcher(itemPageText);
        if (m.find()) {
            String newArticleText = m.replaceAll(textToInsert);
            itemPage.setText(newArticleText);
            itemPage.setEditSummary(String.format("[bot] adding creature '%s' to item '%s'.", creaturePageName, itemPage.getTitle()));
            //itemPagesToUpdate.put(itemPage.getTitle(), itemPage);
            log.info("[bot] adding creature '{}' to item '{}'.", creaturePageName, itemPage.getTitle());
        }
    }

    private void saveItemArticles() {
        log.info("If debug mode is disabled I am going to update {} item articles with missing creatures.", itemPagesToUpdate.size());
        if (!DEBUG_MODE) {
            for (Map.Entry<String, Item> itemPage : itemPagesToUpdate.entrySet()) {
                //repository.saveArticle(itemPage.getValue()); // TODO fix the saving process, wikiObject -> json -> Article
            }
        }
    }
}
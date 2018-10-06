package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
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

    private WikiArticleRepository wikiArticleRepository;
    private CreatureRepository creatureRepository;
    private Map<String, Item> itemPagesToUpdate = new HashMap<>();

    public FixCreatures(MediaWikiBot mediaWikiBot) {
        this.wikiArticleRepository = new WikiArticleRepository(mediaWikiBot);
        this.creatureRepository = new CreatureRepository();
    }

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

        List<Creature> creatures = creatureRepository.getCreatures();

        for (Creature creature: creatures) {
            if (creature != null && !creature.isDeprecatedOrEvent() && !creature.getLoot().isEmpty()) {
                log.info("Processing creature: " + creature.getName());
            }
        }
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

    private void checkIfCreatureNameIsPresent(String creaturePageName, String lootItem) {
        Item item = null;

        if (itemPagesToUpdate.containsKey(lootItem)) {
            item = itemPagesToUpdate.get(lootItem);
        } else {
            WikiObject wikiObject = wikiArticleRepository.getWikiObject(lootItem);
            if (wikiObject instanceof Item) {
                item = (Item) wikiObject;
            }
        }

        if (item == null) {
            return;
        }

        List<String> creatureNames = item.getDroppedby();

        if (!creatureNames.contains(creaturePageName)) {
            creatureNames.add(creaturePageName);
            item.setDroppedby(creatureNames);
            itemPagesToUpdate.put(item.getName(), item);
            log.info("[bot] adding creature '{}' to item '{}'.", creaturePageName, item.getName());
        }
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
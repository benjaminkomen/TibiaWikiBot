package com.wikia.tibia.usecases;

import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** */
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
    private static final String REGEX_LOOT_ITEM = "\\{\\{Loot Item";
    private static final String REGEX_LOOT_ITEM_NAME = "\\{\\{Loot Item\\|(([^|}]+)(\\|([^|}]+))?(\\|([^|}]+))?)}}";

    private static final String REGEX_DROPPED_BY = "\\{\\{Dropped By\\|(.*?)}}";
    private static final String REGEX_DEPRECATED_OR_EVENT = "\\{\\{(Deprecated|Event)}}";
    private static final boolean DEBUG_MODE = true;

    private MediaWikiBot mediaWikiBot;
    private WikiArticleRepository repository;

    public FixCreatures(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
        this.repository = new WikiArticleRepository(mediaWikiBot);
    }

    public void checkCreatures() {
        CategoryMembersSimple categoryMembers = repository.getMembersFromCategory("Creatures");

        for (String creaturePageName : categoryMembers) {
            Article creaturePage = repository.getArticle(creaturePageName);
            String articleText = creaturePage.getText();
            if (!articleDeprecatedOrEvent(articleText) && articleTextContainsLootItems(articleText)) {
                List<String> listOfLootItems = makeListOfLootItems(creaturePageName, articleText);
                for (String lootItem : listOfLootItems) {
                    checkIfCreatureNameIsPresent(creaturePageName, lootItem);
                }
            }
        }
    }

    private boolean articleDeprecatedOrEvent(String articleText) {
        Pattern p = Pattern.compile(REGEX_DEPRECATED_OR_EVENT);
        Matcher m = p.matcher(articleText);
        return m.find();
    }

    private boolean articleTextContainsLootItems(String articleText) {
        Pattern p = Pattern.compile(REGEX_LOOT_ITEM);
        Matcher m = p.matcher(articleText);
        return m.find();
    }

    private List<String> makeListOfLootItems(String creaturePageName, String articleText) {
        List<String> lootItems = new ArrayList<>();
        String lootItemName;
        Pattern pattern = Pattern.compile(REGEX_LOOT_ITEM_NAME);
        Matcher matcher = pattern.matcher(articleText);
        while (matcher.find()) {
            if (matcher.group(6) != null) { // Match |{{Loot Item|0-4|Gold Coin|very rare}}
                lootItemName = matcher.group(4);
            } else if (matcher.group(3) == null) { // Match |{{Loot Item|Apple}}
                lootItemName = matcher.group(2);
            } else if (Character.isUpperCase(matcher.group(2).charAt(0))) { // Match |{{Loot Item|Apple|always}}
                lootItemName = matcher.group(2);
            } else {  // Match |{{Loot Item|0-4|Gold Coin}}
                lootItemName = matcher.group(4);
            }
            if (itemShouldBeAdded(creaturePageName, lootItemName)) {
                lootItems.add(lootItemName);
            }
        }
        return lootItems;
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
        Article itemPage = repository.getArticle(lootItem);
        String itemPageText = itemPage.getText();
        Pattern p = Pattern.compile(REGEX_DROPPED_BY);
        Matcher m = p.matcher(itemPageText);
        if (m.find()) {
            String creatureNames = m.group(1);
            if (!creatureNames.contains(creaturePageName)) {
                String newDroppedByList = updateCreatureToDroppedByList(creatureNames, creaturePageName);
                addMissingCreatureNameToDroppedByList(creaturePageName, itemPage, newDroppedByList);
            }
        } else {
            log.warn("No DroppedBy template encountered on item '{}' while trying to add creature '{}'.", lootItem, creaturePageName);
        }
    }

    private String updateCreatureToDroppedByList(String currentDroppedByList, String newCreature) {
        List<String> creatureNames = new LinkedList<>(Arrays.asList(currentDroppedByList.split("\\|")));
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
            if (!DEBUG_MODE) {
                itemPage.save();
            }
            log.info("[bot] adding creature '{}' to item '{}'.", creaturePageName, itemPage.getTitle());
        }
    }
}
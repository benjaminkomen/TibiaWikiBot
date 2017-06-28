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

public class FixItems {

    private static final Logger log = LoggerFactory.getLogger(FixItems.class);

    private static final String REGEX_DROPPED_BY = "\\{\\{Dropped By\\|(.*?)}}";
    private static final String REGEX_FILLED_DROPPED_BY = "\\{\\{Dropped By\\|(.+?)}}";
    private static final String REGEX_LOOT_ITEM_NAME = "\\{\\{Loot Item\\|(([^|}]+)(\\|([^|}]+))?(\\|([^|}]+))?)}}";

    private static final String REGEX_DEPRECATED_OR_EVENT = "\\{\\{(Deprecated|Event)}}";
    private static final boolean DEBUG_MODE = true;

    private MediaWikiBot mediaWikiBot;
    private WikiArticleRepository repository;
    private Map<String, Article> creaturePagesToUpdate = new HashMap<>();

    public FixItems(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
        this.repository = new WikiArticleRepository(mediaWikiBot);
    }

    public void checkItems() {
        CategoryMembersSimple items = repository.getMembersFromCategory("Items");

        for (String itemPageName : items) {
            Article itemPage = repository.getArticle(itemPageName);
            String articleText = itemPage.getText();
            if (!articleDeprecatedOrEvent(articleText) && articleTextContainsFilledDroppedBy(articleText)) {
                List<String> listOfDroppedByCreatures = makeListOfDroppedByCreatures(articleText);
                for (String droppedByCreature : listOfDroppedByCreatures) {
                    boolean itemNameIsPresent = checkIfItemNameIsPresent(itemPageName, droppedByCreature);
                    if (itemNameIsPresent) {
//                        String lootItems = extractLootItems(droppedByCreature);
                        List<String> listOfLootItems = makeListOfLootItems(droppedByCreature, articleText);
//                        String newLootItemList = updateItemToLootItemList(lootItems, itemPageName);
//                        addMissingLootItemToLootItemList(itemPageName, droppedByCreature, newLootItemList);
                    }
                }
            }
        }
        saveCreatureArticles();
    }

    private boolean articleDeprecatedOrEvent(String articleText) {
        Pattern p = Pattern.compile(REGEX_DEPRECATED_OR_EVENT);
        Matcher m = p.matcher(articleText);
        return m.find();
    }

    private boolean articleTextContainsFilledDroppedBy(String articleText) {
        Pattern p = Pattern.compile(REGEX_FILLED_DROPPED_BY);
        Matcher m = p.matcher(articleText);
        return m.find();
    }

    private List<String> makeListOfDroppedByCreatures(String creaturePageText) {
        Pattern p = Pattern.compile(REGEX_DROPPED_BY);
        Matcher m = p.matcher(creaturePageText);
        if (m.find()) {
            return new ArrayList<>(Arrays.asList(m.group(1).split("\\|")));
        }
        return new ArrayList<>();
    }

    private String extractLootItems(String droppedByCreature) {
        Article creaturePage = getCreaturePage(droppedByCreature);
        String creaturePageText = creaturePage.getText();
        Pattern p = Pattern.compile(REGEX_LOOT_ITEM_NAME);
        Matcher m = p.matcher(creaturePageText);
        if (m.find()) {
            return m.group(0);
        }
        log.warn("No LootItem template encountered on creature '{}'.", droppedByCreature);
        return null;
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
                lootItems.add(lootItemName);
        }
        return lootItems;
    }

    /**
     * Get the Wiki Article from a specific creature, either the already updated cached version, or a fresh copy from the internet
     */
    Article getCreaturePage(String droppedByCreature) {
        Article creaturePage;
        if (creaturePagesToUpdate.containsKey(droppedByCreature)) {
            creaturePage = creaturePagesToUpdate.get(droppedByCreature);
        } else {
            creaturePage = repository.getArticle(droppedByCreature);
        }
        return creaturePage;
    }

    private void saveCreatureArticles() {
        // TODO implement this method
    }

    /**
     * Check if item (e.g. Abyss Hammer) is present in the Loot Item list of given creature (e.g. Ferumbras)
     */
    private boolean checkIfItemNameIsPresent(String itemPageName, String droppedByCreature) {
        String lootItems = extractLootItems(droppedByCreature);
        return lootItems != null && !lootItems.contains(itemPageName);
    }

    private void addMissingLootItemToLootItemList(String itemPageName, String creaturePageName, String newLootItemList) {
        Article creaturePage = getCreaturePage(creaturePageName);
    }

    private String updateItemToLootItemList(String lootItems, String itemPageName) {
        return null;
    }
}
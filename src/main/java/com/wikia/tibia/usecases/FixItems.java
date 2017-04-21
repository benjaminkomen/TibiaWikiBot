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
    private static final String REGEX_LOOT_ITEM = "\\{\\{Loot Item";

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
                    checkIfItemNameIsPresent(itemPageName, droppedByCreature);
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
        return null;
    }

    private void saveCreatureArticles() {
    }

    /**
     * Check if item (e.g. Abyss Hammer) is present in the Loot Item list of given creature (e.g. Ferumbras)
     */
    private void checkIfItemNameIsPresent(String itemPageName, String droppedByCreature) {
        Article creaturePage;
        if (creaturePagesToUpdate.containsKey(droppedByCreature)) {
            creaturePage = creaturePagesToUpdate.get(droppedByCreature);
        } else {
            creaturePage = repository.getArticle(droppedByCreature);
        }
        String creaturePageText = creaturePage.getText();
        Pattern p = Pattern.compile(REGEX_LOOT_ITEM);
        Matcher m = p.matcher(creaturePageText);
        if (m.find()) {
            String lootItems = m.group(1); //FIXME no group 1?
            if (!lootItems.contains(itemPageName)) {
                String newLootItemList = updateItemToLootItemList(lootItems, itemPageName);
                addMissingLootItemToLootItemList(itemPageName, creaturePage, newLootItemList);
            }
        } else {
            log.warn("No LootItem template encountered on creature '{}' while trying to add item '{}'.", droppedByCreature, itemPageName);
        }
    }

    private void addMissingLootItemToLootItemList(String itemPageName, Article creaturePage, String newLootItemList) {
    }

    private String updateItemToLootItemList(String lootItems, String itemPageName) {
        return null;
    }
}
package com.wikia.tibia.usecases;

import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static final String DEFAULT_EDIT_SUMMARY = "[bot] adding missing creatures to droppedby list";
    private static final String REGEX_LOOT_ITEM = "\\{\\{Loot Item";
    private static final String REGEX_LOOT_ITEM_NAME = "\\{\\{Loot Item\\|(.*?)([A-Z].*?)}}";
    private static final String REGEX_DROPPED_BY = "\\{\\{Dropped By\\|(.*?)}}";
    private static final boolean DEBUG = false;

    private MediaWikiBot mediaWikiBot;
    private WikiArticleRepository repository;

    public FixCreatures(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
        this.repository = new WikiArticleRepository(mediaWikiBot);
    }

    public void checkCreatures() {
        CategoryMembersSimple categoryMembers = repository.getMembersFromCategory("Creatures");

        for (String creaturePageName : categoryMembers) {
            Article article = repository.getArticle(creaturePageName);
            String articleText = article.getText();
            if (checkIfArticleTextContainsLootItems(articleText)) {
                List<String> listOfLootItems = makeListOfLootItems(creaturePageName, articleText);
                for (String lootItem : listOfLootItems) {
                    checkIfCreatureNameIsPresent(creaturePageName, lootItem);
                }
            }
        }
    }

    private boolean checkIfArticleTextContainsLootItems(String articleText) {
        Pattern p = Pattern.compile(REGEX_LOOT_ITEM);
        Matcher m = p.matcher(articleText);
        return m.find();
    }

    private List<String> makeListOfLootItems(String creaturePageName, String articleText) {
        List<String> lootItems = new ArrayList<>();
        String lootItemNamePrecise;
        Pattern patternRough = Pattern.compile(REGEX_LOOT_ITEM_NAME);
        Matcher matcherRough = patternRough.matcher(articleText);
        while (matcherRough.find()) {
            String lootItemNameRough = matcherRough.group(2);
            int pipePosition = lootItemNameRough.indexOf('|');
            if (pipePosition > 0) {
                lootItemNamePrecise = lootItemNameRough.substring(0, pipePosition);
            } else {
                lootItemNamePrecise = lootItemNameRough;
            }
            if (itemShouldBeAdded(creaturePageName, lootItemNamePrecise)) {
                lootItems.add(lootItemNamePrecise);
            }
        }
        return lootItems;
    }

    private boolean itemShouldBeAdded(String creaturePageName, String lootItemNamePrecise) {
        if ("Snowball".equals(lootItemNamePrecise)) {
            if (NON_EVENT_CREATURES_DROPPING_SNOWBALLS.contains(creaturePageName)) {
                return true;
            } else {
                return false;
            }
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
        Article article = repository.getArticle(lootItem);
        String articleText = article.getText();
        Pattern p = Pattern.compile(REGEX_DROPPED_BY);
        Matcher m = p.matcher(articleText);
        if (m.find()) {
            String creatureNames = m.group(1);
            if (!creatureNames.contains(creaturePageName)) {
                String newCreatureNames = "{{Dropped By|" + creatureNames + "|" + creaturePageName + "}}";
                addMissingCreatureNameToDroppedByList(article, newCreatureNames);
            }
        } else {
            log.info("Oops, no DroppedBy template encountered on Item page.");
        }
    }

    private void addMissingCreatureNameToDroppedByList(Article article, String textToInsert) {
        String articleText = article.getText();
        Pattern p = Pattern.compile(REGEX_DROPPED_BY);
        Matcher m = p.matcher(articleText);
        if (m.find()) {
            String newArticleText = m.replaceAll(textToInsert);
            article.setText(newArticleText);
            article.setEditSummary(DEFAULT_EDIT_SUMMARY);
            if (DEBUG) {
                article.save();
            } else {
                log.info("[bot] adding creature '{}' to item '{}'.", textToInsert, article.getTitle());
            }
        }
    }
}
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(MediaWikiBot.class);
	private static final String DEFAULT_WIKI_URI = "https://tibia.wikia.com/";
	private static final String DEFAULT_EDIT_SUMMARY = "[bot] adding missing creatures to droppedby list";
	private static final List FILTERED_LOOT_ITEMS = Arrays.asList("Gold Coin");
    private static MediaWikiBot wikiBot;

    private Main() {
        throw new IllegalAccessError("Do not instantiate this class.");
    }

    public static void main(String[] args) {
        wikiBot = new MediaWikiBot(DEFAULT_WIKI_URI);
	    wikiBot.login("469Bot", getPasswordFromProperties());
        checkCreaturesForLootItems();
    }

    private static String getPasswordFromProperties() {
        String output = "";
        try {
            Properties props = new Properties();
            InputStream is = props.getClass().getResourceAsStream("/credentials.properties");
            props.load(is);
            return props.getProperty("password");
        } catch(Exception ex) {
            log.error(String.valueOf(ex));
        }
        return output;
    }

    private static void checkCreaturesForLootItems() {
        CategoryMembersSimple categoryMembers = new CategoryMembersSimple(wikiBot, "Creatures");

        for (String creaturePageName : categoryMembers) {
            checkIfArticleContainsLootItems(creaturePageName);
        }
    }

    private static void checkIfArticleContainsLootItems(String creaturePageName) {
        Article article = wikiBot.getArticle(creaturePageName);
        String articleText = article.getText();
        Pattern p = Pattern.compile("\\{\\{Loot Item");
        Matcher m = p.matcher(articleText);
        if (m.find()) {
            List<String> listOfLootItems = makeListOfLootItems(articleText);
            for (String lootItem : listOfLootItems) {
                checkIfCreatureNameIsPresent(creaturePageName, lootItem);
            }
        }
	}

    private static List<String> makeListOfLootItems(String articleText) {
        List<String> lootItems = new ArrayList<>();
        String lootItemNamePrecise;
        String regexPatternRough = "\\{\\{Loot Item\\|(.*?)([A-Z].*?)}}";
        Pattern patternRough = Pattern.compile(regexPatternRough);
        Matcher matcherRough = patternRough.matcher(articleText);
        while (matcherRough.find()) {
            String lootItemNameRough = matcherRough.group(2);
            int pipePosition = lootItemNameRough.indexOf('|');
            if (pipePosition > 0) {
                lootItemNamePrecise = lootItemNameRough.substring(0, pipePosition);
            } else {
                lootItemNamePrecise = lootItemNameRough;
            }
            if (!FILTERED_LOOT_ITEMS.contains(lootItemNamePrecise)) {
                lootItems.add(lootItemNamePrecise);
            }
        }
        return lootItems;
    }

    private static void checkIfCreatureNameIsPresent(String creaturePageName, String lootItem) {
        Article article = wikiBot.getArticle(lootItem);
        String articleText = article.getText();
        Pattern p = Pattern.compile("\\{\\{Dropped By\\|(.*?)}}");
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

    private static void addMissingCreatureNameToDroppedByList(Article article, String textToInsert) {
        String articleText = article.getText();
        Pattern p = Pattern.compile("\\{\\{Dropped By\\|(.*?)}}");
        Matcher m = p.matcher(articleText);
        if (m.find()) {
            String newArticleText = m.replaceAll(textToInsert);
            article.setText(newArticleText);
            article.setEditSummary(DEFAULT_EDIT_SUMMARY);
            article.save();
        }
    }
}
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static final String DEFAULT_WIKI_URI = "https://tibia.wikia.com/";
	private static MediaWikiBot wikiBot;

    public static void main(String[] args) {
        wikiBot = new MediaWikiBot(DEFAULT_WIKI_URI);
	    wikiBot.login("469Bot", "");
        checkCreaturesForLootItems();
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
            int pipePosition = lootItemNameRough.indexOf("|");
            if (pipePosition > 0) {
                lootItemNamePrecise = lootItemNameRough.substring(0, pipePosition);
            } else {
                lootItemNamePrecise = lootItemNameRough;
            }
            lootItems.add(lootItemNamePrecise);
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
                String newCreatureNames = creatureNames + "|" + creaturePageName;
                addMissingCreatureNameToDroppedByList(article, newCreatureNames);
            }
        } else {
            System.out.println("Oops, no DroppedBy template encountered on Item page.");
        }
    }

    private static void addMissingCreatureNameToDroppedByList(Article article, String textToInsert) {
        String articleText = article.getText();
        Pattern p = Pattern.compile("\\{\\{Dropped By\\|(.*?)}}");
        Matcher m = p.matcher(articleText);
        if (m.find()) {
            String newArticleText = m.replaceAll(textToInsert);
            article.setText(newArticleText);
            article.save();
        }
    }
}
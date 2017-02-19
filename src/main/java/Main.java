import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class Main {
	private static final String DEFAULT_WIKI_URI = "https://tibia.wikia.com/";
	private static MediaWikiBot wikiBot;

	  public static void main(String[] args) {
	    wikiBot = new MediaWikiBot(DEFAULT_WIKI_URI);
//	    Article article = wikiBot.getArticle("Bear");
//	    System.out.println(article.getText().substring(5, 42));

	    CategoryMembersSimple categoryMembers = new CategoryMembersSimple(wikiBot, "Creatures");
	    
	    for (String creaturePageName : categoryMembers) {
	    	checkIfContainsLootItems(creaturePageName);
	    }
	    
	    
//	    applyChangesTo(article);
//	    wikiBot.login("user", "***");
//	    article.save();
	  }

	  private static void checkIfContainsLootItems(String creaturePageName) {
		  Article article = wikiBot.getArticle(creaturePageName);
		  article.getText();
		
	}

	static void applyChangesTo(Article article) {
	    // edits the article...
	  }
}
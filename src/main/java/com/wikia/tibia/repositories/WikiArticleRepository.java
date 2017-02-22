package com.wikia.tibia.repositories;

import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

/** */
public class WikiArticleRepository {

    MediaWikiBot mediaWikiBot;

    public WikiArticleRepository(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
    }

    public CategoryMembersSimple getMembersFromCategory(String categoryName) {

        return new CategoryMembersSimple(mediaWikiBot, categoryName);
    }

    public Article getArticle(String creaturePageName) {

        return mediaWikiBot.getArticle(creaturePageName);
    }
}
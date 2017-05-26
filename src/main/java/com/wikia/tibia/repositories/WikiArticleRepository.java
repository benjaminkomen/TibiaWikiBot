package com.wikia.tibia.repositories;

import com.wikia.tibia.factories.ArticleFactory;
import com.wikia.tibia.objects.WikiObject;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class WikiArticleRepository {

    private MediaWikiBot mediaWikiBot;

    public WikiArticleRepository(MediaWikiBot mediaWikiBot) {
        this.mediaWikiBot = mediaWikiBot;
    }

    public CategoryMembersSimple getMembersFromCategory(String categoryName) {

        return new CategoryMembersSimple(mediaWikiBot, categoryName);
    }

    public Article getArticle(String pageName) {

        return mediaWikiBot.getArticle(pageName);
    }

    public WikiObject getWikiObject(String pageName) {
        Article article = getArticle(pageName);
        ArticleFactory articleFactory = new ArticleFactory();
        return articleFactory.createWikiObject(article);
    }

    public void saveArticle(Article articleToSave) {
        articleToSave.save();
    }
}
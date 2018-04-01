package com.wikia.tibia.repositories;

import com.wikia.tibia.factories.ArticleFactory;
import com.wikia.tibia.objects.WikiObject;
import net.sourceforge.jwbf.core.actions.util.ProcessException;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.editing.FileUpload;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import net.sourceforge.jwbf.mediawiki.actions.queries.ImageInfo;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import net.sourceforge.jwbf.mediawiki.contentRep.SimpleFile;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Article> getArticles(List<String> pageNames) {
        return getArticles(pageNames.toArray(new String[0]));
    }

    /**
     * Given a list of pageNames, return a list of Articles in one go, which is supposedly faster and more efficient
     * than {@link #getArticle}. This is limited to 500? articles?
     */
    public List<Article> getArticles(String[] pageNames) {
        return mediaWikiBot.readData(pageNames).stream()
                .map(sa -> Article.withoutReload(sa, mediaWikiBot))
                .collect(Collectors.toList());
    }

    public WikiObject getWikiObject(String pageName) {
        Article article = getArticle(pageName);
        ArticleFactory articleFactory = new ArticleFactory();
        return articleFactory.createWikiObject(article);
    }

    public List<WikiObject> getWikiObjects(List<String> pageNames) {
        return getWikiObjects(pageNames.toArray(new String[0]));
    }

    public List<WikiObject> getWikiObjects(String[] pageNames) {
        return getArticles(pageNames).stream()
                .map(a -> new ArticleFactory().createWikiObject(a))
                .collect(Collectors.toList());
    }

    public void saveArticle(Article articleToSave) {
        articleToSave.save();
    }

    public void saveArticle(Article articleToSave, String summary) {
        articleToSave.save(summary);
    }

    public URL getImageInfo(String title) {
        final ImageInfo imageInfo = new ImageInfo(mediaWikiBot, title);
        return imageInfo.getUrl();
    }

    public String uploadFile(String title, String editSummary, Path fileLocation) {
        // check if file already exists
        final String filePage = "File:" + title + ".png";
        boolean imageAlreadyExists = true;

        // check if image already exists
        try {
            getImageInfo(filePage);
        } catch (ProcessException e) {
            imageAlreadyExists = false;
        }

        if (imageAlreadyExists) {
            return "File not uploaded. File page " + filePage + " already exists.";
        }

        SimpleFile simpleFile = new SimpleFile(filePage, fileLocation.toFile());
        simpleFile.setEditSummary(editSummary);
        final FileUpload fileUpload = new FileUpload(simpleFile, mediaWikiBot);
        mediaWikiBot.getPerformedAction(fileUpload);
        return "Probably succesfully uploaded file " + fileUpload;
    }
}
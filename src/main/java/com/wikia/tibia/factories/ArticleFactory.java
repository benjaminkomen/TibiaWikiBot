package com.wikia.tibia.factories;

import net.sourceforge.jwbf.core.contentRep.Article;

public class ArticleFactory {

    /**
     * Create a Json representation of a wiki article
     */
    public String create(Article article) {

        String articleContent = article.getText();

        String infoboxTemplatePartOfArticle = getInfoboxTemplatePartOfArticle(articleContent);

        return infoboxTemplatePartOfArticle;
    }

    private String getInfoboxTemplatePartOfArticle(String articleContent) {
        int startingCurlyBrackets = articleContent.indexOf("{{Infobox");
        int endingCurlyBrackets = 0;
        int openBracketsCounter = 0;
        char currentChar;

        for (int i=startingCurlyBrackets; i < articleContent.length(); i++) {
            currentChar = articleContent.charAt(i);
            if ('{' == currentChar) {
                openBracketsCounter++;
            }

            if ('}' == currentChar) {
                openBracketsCounter--;
            }

            if (openBracketsCounter == 0) {
                endingCurlyBrackets = i+1;
                break;
            }
        }

        return articleContent.substring(startingCurlyBrackets, endingCurlyBrackets);
    }
}

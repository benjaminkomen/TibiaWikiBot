package com.wikia.tibia.factories;

import net.sourceforge.jwbf.core.contentRep.Article;
import org.json.JSONObject;

import java.util.*;
import java.util.regex.Pattern;

public class ArticleFactory {

    /**
     * Create a Json representation of a wiki article
     */
    public String create(Article article) {

        String articleContent = article.getText();

        String infoboxTemplatePartOfArticle = getInfoboxTemplatePartOfArticle(articleContent);
        String jsonRepresentation = convertToJson(infoboxTemplatePartOfArticle);

        return jsonRepresentation;
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

    private String convertToJson(String infoboxTemplatePartOfArticle) {

        infoboxTemplatePartOfArticle = removeFirstAndLastLine(infoboxTemplatePartOfArticle);
        Map<String, String> parametersAndValues = splitByParameter(infoboxTemplatePartOfArticle);
        String jsonRepresentation = MapToJson(parametersAndValues);

        return jsonRepresentation;
    }

    private String removeFirstAndLastLine(String infoboxTemplatePartOfArticle) {
        String firstLineRemoved = infoboxTemplatePartOfArticle
                .substring(infoboxTemplatePartOfArticle.indexOf('\n')+1);
        return firstLineRemoved.substring(0, firstLineRemoved.lastIndexOf("}}"));
    }

    private Map<String, String> splitByParameter(String infoboxTemplatePartOfArticle) {
        Map<String, String> keyValuePair = new HashMap<>();
        List<String> splitLines = Arrays.asList(Pattern.compile("(^|\n)\\|").split(infoboxTemplatePartOfArticle));

        for (String line : splitLines) {
            if (line.indexOf('=') != -1) {
                String key = line.substring(0, line.indexOf('=')).trim();
                String value = line.substring(line.indexOf('=') + 1, line.length()).trim();
                keyValuePair.put(key, value);
            }
        }
        return keyValuePair;
    }

    private String MapToJson(Map<String, String> parametersAndValues) {
        return new JSONObject(parametersAndValues).toString(2);
    }
}

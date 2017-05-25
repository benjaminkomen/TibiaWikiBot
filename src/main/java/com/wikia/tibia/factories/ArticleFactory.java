package com.wikia.tibia.factories;

import com.wikia.tibia.utils.TemplateUtils;
import net.sourceforge.jwbf.core.contentRep.Article;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ArticleFactory {

    private static final Logger log = LoggerFactory.getLogger(ArticleFactory.class);
    private static final String TEMPLATE_TYPE = "templateType";
    private static final String TEMPLATE_TYPE_CREATURE = "Creature";
    private static final String TEMPLATE_TYPE_ITEM = "Item";
    private Article article;

    /**
     * Create a Json representation of a wiki article
     */
    public String create(Article article) {
        this.article = article;

        String articleContent = article.getText();

        String infoboxTemplatePartOfArticle = TemplateUtils.getBetweenBalancedBrackets(articleContent, "{{Infobox");
        String jsonRepresentation = convertToJson(infoboxTemplatePartOfArticle);

        return jsonRepresentation;
    }

    private String convertToJson(String infoboxTemplatePartOfArticle) {
        String templateType = getTemplateType(infoboxTemplatePartOfArticle);
        String infoboxTemplatePartOfArticleSanitized = TemplateUtils.removeFirstAndLastLine(infoboxTemplatePartOfArticle);
        Map<String, String> parametersAndValues = splitByParameter(infoboxTemplatePartOfArticleSanitized);
        parametersAndValues.put("templateType", templateType);
        JSONObject jsonRepresentation = MapToJson(parametersAndValues);
        return jsonRepresentation.toString(2);
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

    private JSONObject MapToJson(Map<String, String> parametersAndValues) {
        JSONObject jsonObject = new JSONObject(parametersAndValues);
        return sanitizeJsonObject(jsonObject);
    }

    private String getTemplateType(String infoboxTemplatePartOfArticle) {
        int startOfTemplateName = infoboxTemplatePartOfArticle.indexOf("{{Infobox") +9;
        int endOfTemplateName = infoboxTemplatePartOfArticle.indexOf('|');
        if (startOfTemplateName >= 0 && endOfTemplateName >= 0) {
            return infoboxTemplatePartOfArticle.substring(startOfTemplateName, endOfTemplateName).trim();
        }
        log.warn("Template type for page {} could not be determined.", article.getTitle());
        return "Unknown";
    }

    private JSONObject sanitizeJsonObject(JSONObject jsonObject) {
        if (jsonObject.has(TEMPLATE_TYPE)) {
            String templateType = jsonObject.getString(TEMPLATE_TYPE);
            if (TEMPLATE_TYPE_CREATURE.equals(templateType)) {
                CreatureFactory creatureFactory = new CreatureFactory();
                return creatureFactory.create(jsonObject);
            }
            if (TEMPLATE_TYPE_ITEM.equals(templateType)) {
                ItemFactory itemFactory = new ItemFactory();
                return itemFactory.create(jsonObject);

            }
            log.warn("Template type {} is currently not supported.", templateType);
        }
        return jsonObject;
    }
}

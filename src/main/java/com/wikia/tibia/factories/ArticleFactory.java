package com.wikia.tibia.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.mixins.CreatureMixIn;
import com.wikia.tibia.objects.*;
import com.wikia.tibia.utils.TemplateUtils;
import net.sourceforge.jwbf.core.contentRep.Article;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class ArticleFactory {

    private static final Logger log = LoggerFactory.getLogger(ArticleFactory.class);
    private static final String OBJECT_TYPE = "objectType";
    private static final String OBJECT_TYPE_ACHIEVEMENT = "Achievement";
    private static final String OBJECT_TYPE_BOOK = "Book";
    private static final String OBJECT_TYPE_BUILDING = "Building";
    private static final String OBJECT_TYPE_CREATURE = "Creature";
    private static final String OBJECT_TYPE_ITEM = "Item";
    private static final String INFOBOX_HEADER = "{{Infobox";
    private static final String REGEX_EVENT = "\\{\\{(E|e)vent";
    private static final String REGEX_DEPRECATED = "\\{\\{(D|d)eprecated";

    private Article article;
    private String objectType;

    public WikiObject createWikiObject(Article article) {
        this.article = article;
        String articleContent = article.getText();

        if (!articleContent.contains(INFOBOX_HEADER)) {
            return null;
        }

        String wikiObjectPartOfArticle = TemplateUtils.getBetweenOuterBalancedBrackets(articleContent, INFOBOX_HEADER);
        String wikiObjectJson = convertToJson(wikiObjectPartOfArticle);

        WikiObject wikiObject;

        switch (objectType) {
            case OBJECT_TYPE_ACHIEVEMENT :
                wikiObject = mapJsonToObject(wikiObjectJson, Achievement.class);
                break;
            case OBJECT_TYPE_BOOK :
                wikiObject = mapJsonToObject(wikiObjectJson, Book.class);
                break;
            case OBJECT_TYPE_BUILDING :
                wikiObject = mapJsonToObject(wikiObjectJson, Building.class);
                break;
            case OBJECT_TYPE_CREATURE :
                wikiObject = mapJsonToObject(wikiObjectJson, Creature.class);
                break;
            case OBJECT_TYPE_ITEM :
                wikiObject = mapJsonToObject(wikiObjectJson, Item.class);
                break;
            default:
                log.warn("object type '{}' not supported, terminating..", objectType);
                return null;
        }

        wikiObject.setStatus(setStatusEventOrDeprecated(articleContent));

        return wikiObject;
    }

    private Status setStatusEventOrDeprecated(String articleContent) {
        if (articleContent.matches(REGEX_EVENT)) {
            return Status.EVENT;
        }
        if (articleContent.matches(REGEX_DEPRECATED)) {
            return Status.DEPRECATED;
        }
        return null;
    }

    private String convertToJson(String wikiObjectPartOfArticle) {
        objectType = getTemplateType(wikiObjectPartOfArticle);
        String infoboxTemplatePartOfArticleSanitized = TemplateUtils.removeFirstAndLastLine(wikiObjectPartOfArticle);
        Map<String, String> parametersAndValues = TemplateUtils.splitByParameter(infoboxTemplatePartOfArticleSanitized);
        parametersAndValues.put(OBJECT_TYPE, objectType);
        JSONObject jsonRepresentation = mapToJson(parametersAndValues);
        return jsonRepresentation.toString(2);
    }

    private JSONObject mapToJson(Map<String, String> parametersAndValues) {
        JSONObject jsonObject = new JSONObject(parametersAndValues);
        return sanitizeJsonObject(jsonObject);
    }

    private String getTemplateType(String infoboxTemplatePartOfArticle) {
        int startOfTemplateName = infoboxTemplatePartOfArticle.indexOf("{{Infobox") + 9;
        int endOfTemplateName = infoboxTemplatePartOfArticle.indexOf('|');
        if (startOfTemplateName >= 0 && endOfTemplateName >= 0) {
            return infoboxTemplatePartOfArticle.substring(startOfTemplateName, endOfTemplateName).trim();
        }
        log.warn("Template type for page {} could not be determined.", article.getTitle());
        return "Unknown";
    }

    private JSONObject sanitizeJsonObject(JSONObject jsonObject) {
        if (jsonObject.has(OBJECT_TYPE)) {
            String templateType = jsonObject.getString(OBJECT_TYPE);

            switch (templateType) {
                case OBJECT_TYPE_CREATURE:
                    CreatureFactory creatureFactory = new CreatureFactory();
                    return creatureFactory.create(jsonObject);
                case OBJECT_TYPE_ITEM:
                    ItemFactory itemFactory = new ItemFactory();
                    return itemFactory.create(jsonObject);
            }
        }
        return jsonObject;
    }

    private <T> T mapJsonToObject(String wikiObjectJson, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixInAnnotations(Creature.class, CreatureMixIn.class);
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Creature.class, new CreatureDeserializer(Creature.class));
//        objectMapper.registerModule(module);
        try {
            return objectMapper.readValue(wikiObjectJson, clazz);
        } catch (IOException e) {
            log.error("Unable to convert json to {} object.", clazz.toString(), e);
        }
        return null;
    }
}
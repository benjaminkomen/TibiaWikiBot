package com.wikia.tibia.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikia.tibia.mixins.CreatureMixIn;
import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.WikiObject;
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
    private static final String OBJECT_TYPE_CREATURE = "Creature";
    private static final String OBJECT_TYPE_ITEM = "Item";
    private static final String INFOBOX_HEADER = "{{Infobox";
    private Article article;
    private String objectType;

    public WikiObject createWikiObject(Article article) {
        this.article = article;
        String articleContent = article.getText();

        if (!articleContent.contains(INFOBOX_HEADER)) {
            return null;
        }

        String wikiObjectPartOfArticle = TemplateUtils.getBetweenBalancedBrackets(articleContent, INFOBOX_HEADER);
        String wikiObjectJson = convertToJson(wikiObjectPartOfArticle);

        WikiObject wikiObject = new WikiObject();
        if (OBJECT_TYPE_CREATURE.equals(objectType)) {
            wikiObject = mapCreatureJsonToObject(wikiObjectJson);
        }
        if (OBJECT_TYPE_ITEM.equals(objectType)) {
            wikiObject = mapItemJsonToObject(wikiObjectJson);
        }

        return wikiObject;
    }

    private String convertToJson(String wikiObjectPartOfArticle) {
        objectType = getTemplateType(wikiObjectPartOfArticle);
        String infoboxTemplatePartOfArticleSanitized = TemplateUtils.removeFirstAndLastLine(wikiObjectPartOfArticle);
        Map<String, String> parametersAndValues = TemplateUtils.splitByParameter(infoboxTemplatePartOfArticleSanitized);
        parametersAndValues.put(OBJECT_TYPE, objectType);
        JSONObject jsonRepresentation = MapToJson(parametersAndValues);
        return jsonRepresentation.toString(2);
    }

    private JSONObject MapToJson(Map<String, String> parametersAndValues) {
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
            if (OBJECT_TYPE_CREATURE.equals(templateType)) {
                CreatureFactory creatureFactory = new CreatureFactory();
                return creatureFactory.create(jsonObject);
            }
            if (OBJECT_TYPE_ITEM.equals(templateType)) {
                ItemFactory itemFactory = new ItemFactory();
                return itemFactory.create(jsonObject);

            }
            log.warn("Template type {} is currently not supported.", templateType);
        }
        return jsonObject;
    }

    private WikiObject mapCreatureJsonToObject(String wikiObjectJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixInAnnotations(Creature.class, CreatureMixIn.class);
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Creature.class, new CreatureDeserializer(Creature.class));
//        objectMapper.registerModule(module);

        try {
            return objectMapper.readValue(wikiObjectJson, Creature.class);
        } catch (IOException e) {
            log.error("Unable to convert json to Creature object.", e);
        }
        return null;
    }

    private WikiObject mapItemJsonToObject(String wikiObjectJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(wikiObjectJson, Item.class);
        } catch (IOException e) {
            log.error("Unable to convert json to Item object.", e);
        }
        return null;
    }
}
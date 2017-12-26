package com.wikia.tibia.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.wikia.tibia.mixins.CreatureMixIn;
import com.wikia.tibia.objects.*;
import com.wikia.tibia.utils.TemplateUtils;
import net.sourceforge.jwbf.core.bots.WikiBot;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class ArticleFactory {

    private static final Logger log = LoggerFactory.getLogger(ArticleFactory.class);
    private static final String OBJECT_TYPE = "objectType";
    private static final String OBJECT_TYPE_ACHIEVEMENT = "Achievement";
    private static final String OBJECT_TYPE_BOOK = "Book";
    private static final String OBJECT_TYPE_BUILDING = "Building";
    private static final String OBJECT_TYPE_CREATURE = "Creature";
    private static final String OBJECT_TYPE_KEY = "Key";
    private static final String OBJECT_TYPE_ITEM = "Item";
    private static final String OBJECT_TYPE_NPC = "NPC";
    private static final String OBJECT_TYPE_OBJECT = "Object";
    private static final String INFOBOX_HEADER = "{{Infobox";
    private static final String SOUNDS = "sounds";
    private static final String SPAWN_TYPE = "spawntype";
    private static final String LOOT = "loot";
    private static final String DROPPED_BY = "droppedby";
    private static final String ITEM_ID = "itemid";
    private static final List ITEMS_WITH_NO_DROPPEDBY_LIST = Arrays.asList("Gold Coin", "Platinum Coin");


    private Article article;
    private String articleName;
    private String objectType;

    /**
     * Creates a WikiObject from an Article, when reading from the wiki.
     * The reverse is achieved by {@link #createArticle} when saving the JSON back to the wiki.
     */
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
            case OBJECT_TYPE_ACHIEVEMENT:
                wikiObject = mapJsonToObject(wikiObjectJson, Achievement.class);
                break;
            case OBJECT_TYPE_BOOK:
                wikiObject = mapJsonToObject(wikiObjectJson, Book.class);
                break;
            case OBJECT_TYPE_BUILDING:
                wikiObject = mapJsonToObject(wikiObjectJson, Building.class);
                break;
            case OBJECT_TYPE_CREATURE:
                wikiObject = mapJsonToObject(wikiObjectJson, Creature.class);
                break;
            case OBJECT_TYPE_KEY:
                wikiObject = mapJsonToObject(wikiObjectJson, Key.class);
                break;
            case OBJECT_TYPE_ITEM:
                wikiObject = mapJsonToObject(wikiObjectJson, Item.class);
                break;
            case OBJECT_TYPE_NPC:
                wikiObject = mapJsonToObject(wikiObjectJson, NPC.class);
                break;
            case OBJECT_TYPE_OBJECT:
                wikiObject = mapJsonToObject(wikiObjectJson, TibiaObject.class);
                break;
            default:
                log.warn("object type '{}' not supported, terminating..", objectType);
                return null;
        }

        return wikiObject;
    }

    /**
     * Creates an Article from a JSON string, for saving to the wiki.
     * The reverse is achieved by {@link #createWikiObject(Article)} when reading from the wiki.
     */
    public Article createArticle(WikiBot wikiBot, JSONObject json) {
        return new Article(wikiBot, createSimpleArticle(wikiBot, json));
    }

    private SimpleArticle createSimpleArticle(WikiBot wikiBot, JSONObject json) {
        SimpleArticle simpleArticle = new SimpleArticle();
        simpleArticle.setEditor(wikiBot.getUserinfo().getUsername());
        simpleArticle.setEditTimestamp(new Date(ZonedDateTime.now().toEpochSecond()));
        simpleArticle.setTitle(json.get("name").toString());
        simpleArticle.setText(createArticleText(json));
        return simpleArticle;
    }

    private String createArticleText(JSONObject json) {
        StringBuilder sb = new StringBuilder();
        sb.append("{{Infobox Building|List={{{1|}}}|GetValue={{{GetValue|}}}").append("\n");

        int maxKeyLength = json.keySet().stream().mapToInt(String::length).max().orElse(0);

        for (String key : json.keySet()) {
            int keyLength = key.length();
            int padding = maxKeyLength - keyLength; // ammount of spaces to pad
            Object value = json.get(key);
//            String paddedKey = key + new String(new char[padding - key.length()]).replace('\0', ' ');
            String paddedKey = Strings.padEnd(key, padding, ' ');
            sb.append("| ")
                    .append(paddedKey)
                    .append(" = ")
                    .append(value)
                    .append("\n");
        }

        sb.append("}}").append("\n");
        return sb.toString();
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
        return enhanceJsonObject(jsonObject);
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

    private JSONObject enhanceJsonObject(JSONObject jsonObject) {
        if (jsonObject.has(OBJECT_TYPE)) {

            assert (jsonObject.has("name")) : "parameter name not found in jsonObject:" + jsonObject.toString(2);
            this.articleName = jsonObject.getString("name");

            if (jsonObject.has(SOUNDS)) {
                String soundsValue = jsonObject.getString(SOUNDS);
                JSONArray soundsArray = makeSoundsArray(soundsValue);
                jsonObject.put(SOUNDS, soundsArray);
            }

            if (jsonObject.has(SPAWN_TYPE)) {
                String spawntypeValue = jsonObject.getString(SPAWN_TYPE);
                JSONArray spawntypeArray = new JSONArray(TemplateUtils.splitByCommaAndTrim(spawntypeValue));
                jsonObject.put(SPAWN_TYPE, spawntypeArray);
            }

            if (jsonObject.has(LOOT)) {
                String lootValue = jsonObject.getString(LOOT);
                JSONArray lootTableArray = makeLootTableArray(lootValue);
                jsonObject.put(LOOT, lootTableArray);
            }

            if (jsonObject.has(DROPPED_BY)) {
                String droppedbyValue = jsonObject.getString(DROPPED_BY);
                JSONArray droppedbyArray = makeDroppedByArray(droppedbyValue);
                jsonObject.put(DROPPED_BY, droppedbyArray);
            }

            if (jsonObject.has(ITEM_ID)) {
                String itemIdValue = jsonObject.getString(ITEM_ID);
                JSONArray itemIdArray = new JSONArray(TemplateUtils.splitByCommaAndTrim(itemIdValue));
                jsonObject.put(ITEM_ID, itemIdArray);
            }
        }
        return jsonObject;
    }

    private <T> T mapJsonToObject(String wikiObjectJson, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Creature.class, CreatureMixIn.class);
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

    private JSONArray makeSoundsArray(String soundsValue) {
        if (soundsValue.length() < 2) {
            return new JSONArray();
        }
        assert (soundsValue.contains("{{Sound List")) : "soundsValue '" + soundsValue + "' from article '" + articleName +
                "' does not contain Template:Sound List";
        String sounds = TemplateUtils.removeStartAndEndOfTemplate(soundsValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(sounds));
        return new JSONArray(splitLines);
    }

    private JSONArray makeLootTableArray(String lootValue) {
        List<JSONObject> lootItemJsonObjects = new ArrayList<>();

        if (lootValue.matches("\\{\\{Loot Table(\\||\\s|)}}")) {
            return new JSONArray();
        }

        String lootItemsPartOfLootTable = TemplateUtils.getBetweenOuterBalancedBrackets(lootValue, "{{Loot Table");
        lootItemsPartOfLootTable = TemplateUtils.removeFirstAndLastLine(lootItemsPartOfLootTable);

        if (lootItemsPartOfLootTable.length() < 3) {
            return new JSONArray();
        }

        List<String> lootItemsList = Arrays.asList(Pattern.compile("(^|\n)(\\s|)\\|").split(lootItemsPartOfLootTable));

        for (String lootItemTemplate : lootItemsList) {
            if (lootItemTemplate.length() < 1) {
                continue;
            }
            String lootItem = TemplateUtils.removeStartAndEndOfTemplate(lootItemTemplate);
            if (lootItem == null) {
                log.error("Unable to create lootTableArray from lootValue: {}", lootValue);
                return new JSONArray();
            }
            List<String> splitLootItem = Arrays.asList(Pattern.compile("\\|").split(lootItem));
            JSONObject lootItemJsonObject = makeLootItemJsonObject(splitLootItem);
            lootItemJsonObjects.add(lootItemJsonObject);
        }

        return new JSONArray(lootItemJsonObjects);
    }

    private JSONObject makeLootItemJsonObject(List<String> splitLootItem) {
        Map<String, String> lootItemMap = new HashMap<>();

        for (String lootItemPart : splitLootItem) {
            if (Character.isUpperCase(lootItemPart.charAt(0))) {
                lootItemMap.put("itemName", lootItemPart);
            } else if (Character.isDigit(lootItemPart.charAt(0))) {
                lootItemMap.put("amount", lootItemPart);
            } else if (Character.isLowerCase(lootItemPart.charAt(0))) {
                lootItemMap.put("rarity", lootItemPart);
            } else {
                log.warn("The text '{}' in Template:Loot Item could not be identified as item name, amount or rarity.", lootItemPart);
            }
        }
        return new JSONObject(lootItemMap);
    }

    private JSONArray makeDroppedByArray(String droppedbyValue) {
        if (droppedbyValue.length() < 2 || droppedbyValue.matches("(N|n)one(\\.|)") || legallyHasNoDroppedByTemplate(articleName)) {
            return new JSONArray();
        }
        assert (droppedbyValue.contains("{{Dropped By")) : "droppedbyValue " +
                droppedbyValue + "' from article '" + articleName + "' does not contain Template:Dropped By";
        String creatures = TemplateUtils.removeStartAndEndOfTemplate(droppedbyValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(creatures));
        return new JSONArray(splitLines);
    }

    private boolean legallyHasNoDroppedByTemplate(String name) {
        return ITEMS_WITH_NO_DROPPEDBY_LIST.contains(name);
    }
}
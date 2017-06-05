package com.wikia.tibia.factories;

import com.wikia.tibia.utils.TemplateUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

public class CreatureFactory {

    private static final Logger log = LoggerFactory.getLogger(CreatureFactory.class);
    private static final String SOUNDS = "sounds";
    private static final String SPAWN_TYPE = "spawntype";
    private static final String LOOT = "loot";

    private String name;

    public JSONObject create(JSONObject jsonObject) {

        this.name = jsonObject.getString("name");

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

        return jsonObject;
    }

    private JSONArray makeSoundsArray(String soundsValue) {
        if (soundsValue.length() < 2) {
            return new JSONArray();
        }
        assert (soundsValue.contains("{{Sound List")) : "soundsValue '" + soundsValue + "' from creature '" + name +
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
                lootItemMap.put("item", lootItemPart);
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
}
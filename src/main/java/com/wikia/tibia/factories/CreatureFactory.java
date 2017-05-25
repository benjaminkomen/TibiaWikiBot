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
    private static final String WALKS_THROUGH = "walksthrough";
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
            JSONArray spawntypeArray = new JSONArray(splitByCommaAndTrim(spawntypeValue));
            jsonObject.put(SPAWN_TYPE, spawntypeArray);
        }

        if (jsonObject.has(WALKS_THROUGH)) {
            String walksthroughValue = jsonObject.getString(WALKS_THROUGH);
            JSONArray walksthroughArray = new JSONArray(splitByCommaAndTrim(walksthroughValue));
            jsonObject.put(WALKS_THROUGH, walksthroughArray);
        }

        if (jsonObject.has(LOOT)) {
            String lootValue = jsonObject.getString(LOOT);
            JSONArray lootTableArray = makeLootTableArray(lootValue);
            jsonObject.put(LOOT, lootTableArray);
        }

        return jsonObject;
    }

    private JSONArray makeSoundsArray(String soundsValue) {
        String sounds = TemplateUtils.removeStartAndEndOfTemplate(soundsValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(sounds));
        return new JSONArray(splitLines);
    }

    private JSONArray makeLootTableArray(String lootValue) {
        List<JSONObject> lootItemJsonObjects = new ArrayList<>();
        String lootItemsPartOfLootTable = TemplateUtils.getBetweenBalancedBrackets(lootValue, "{{Loot Table");
        lootItemsPartOfLootTable = TemplateUtils.removeFirstAndLastLine(lootItemsPartOfLootTable);
        List<String> lootItemsList = Arrays.asList(Pattern.compile("(^|\n)\\s\\|").split(lootItemsPartOfLootTable));

        for (String lootItemTemplate : lootItemsList) {
            if (lootItemTemplate.length() < 1) {
                continue;
            }
            String lootItem = TemplateUtils.removeStartAndEndOfTemplate(lootItemTemplate);
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

    private List<String> splitByCommaAndTrim(String input) {
        List<String> result = new ArrayList<>();
        String[] arrayFromSplitInput = input.split(",");

        for (String arrayElement : arrayFromSplitInput) {
            result.add(arrayElement.trim());
        }

        return result;
    }
}

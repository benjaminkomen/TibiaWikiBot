package com.wikia.tibia.factories;

import com.wikia.tibia.utils.TemplateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFactory {

    private static final String SOUNDS = "sounds";
    private static final String DROPPED_BY = "droppedby";
    private static final String ITEM_ID = "itemid";

    private String name;

    public JSONObject create(JSONObject jsonObject) {

        this.name = jsonObject.getString("name");

        if (jsonObject.has(SOUNDS)) {
            String soundsValue = jsonObject.getString(SOUNDS);
            JSONArray soundsArray = makeSoundsArray(soundsValue);
            jsonObject.put(SOUNDS, soundsArray);
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

        return jsonObject;
    }

    private JSONArray makeSoundsArray(String soundsValue) {
        if (soundsValue.length() < 2) {
            return new JSONArray();
        }
        assert (soundsValue.contains("{{Sound List")) : "soundsValue " + soundsValue + " does not contain Template:Sound List";
        String sounds = TemplateUtils.removeStartAndEndOfTemplate(soundsValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(sounds));
        return new JSONArray(splitLines);
    }

    private JSONArray makeDroppedByArray(String droppedbyValue) {
        if (droppedbyValue.length() < 2 || droppedbyValue.matches("(N|n)one")) {
            return new JSONArray();
        }
        assert (droppedbyValue.contains("{{Dropped By")) : "droppedbyValue " + droppedbyValue + "' from creature '" + name +
                "' does not contain Template:Dropped By";
        String creatures = TemplateUtils.removeStartAndEndOfTemplate(droppedbyValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(creatures));
        return new JSONArray(splitLines);
    }
}
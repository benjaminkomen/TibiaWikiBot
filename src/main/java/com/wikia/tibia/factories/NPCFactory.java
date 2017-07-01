package com.wikia.tibia.factories;

import com.wikia.tibia.utils.TemplateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NPCFactory {

    private static final String SOUNDS = "sounds";

    private String npcName;

    public JSONObject create(JSONObject jsonObject) {

        assert(jsonObject.has("name")) : "parameter name not found in jsonObject:" + jsonObject.toString(2);
        this.npcName = jsonObject.getString("name");

        if (jsonObject.has(SOUNDS)) {
            String soundsValue = jsonObject.getString(SOUNDS);
            JSONArray soundsArray = makeSoundsArray(soundsValue);
            jsonObject.put(SOUNDS, soundsArray);
        }

        return jsonObject;
    }

    private JSONArray makeSoundsArray(String soundsValue) {
        if (soundsValue.length() < 2) {
            return new JSONArray();
        }
        assert (soundsValue.contains("{{Sound List")) : "soundsValue " + soundsValue + "' from NPC '" + npcName +
                "' does not contain Template:Sound List";
        String sounds = TemplateUtils.removeStartAndEndOfTemplate(soundsValue);
        List<String> splitLines = Arrays.asList(Pattern.compile("\\|").split(sounds));
        return new JSONArray(splitLines);
    }
}
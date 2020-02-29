package com.wikia.tibia.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WrappedWikiObject {

    private JSONObject originalJson;
    private JSONObject targetJson;

    public WrappedWikiObject(JSONObject originalJson, JSONObject targetJson) {
        this.originalJson = originalJson;
        this.targetJson = targetJson;
    }

    public String jsonDifference() {
        targetJson.remove("originalJson");
        return JSONCompare.compareJSON(originalJson, targetJson, JSONCompareMode.STRICT).toString();
    }

    public static class WrappedWikiObjectImpl extends WrappedWikiObject {

        public WrappedWikiObjectImpl() {
            super();
        }
    }
}

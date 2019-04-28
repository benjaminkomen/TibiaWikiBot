package com.wikia.tibia.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WrappedWikiObject {

    private String originalJson;
    private String targetJson;

    public WrappedWikiObject(String originalJson, String targetJson) {
        this.originalJson = originalJson;
        this.targetJson = targetJson;
    }

    public String jsonDifference() {
        return JSONCompare.compareJSON(originalJson, targetJson, JSONCompareMode.STRICT).toString();
    }

    public static class WrappedWikiObjectImpl extends WrappedWikiObject {

        public WrappedWikiObjectImpl() {
            super();
        }
    }
}

package com.wikia.tibia.objects;

import lombok.Getter;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

@Getter
public abstract class WrappedWikiObject {

    private String originalJson;
    private String targetJson;

    protected WrappedWikiObject() {
        originalJson = null;
        targetJson = null;
    }

    public WrappedWikiObject(String originalJson, String targetJson) {
        this.originalJson = originalJson;
        this.targetJson = targetJson;
    }

    public void withOriginalJson(String originalJson) {
        this.originalJson = originalJson;
    }

    public void withTargetJson(String targetJson) {
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

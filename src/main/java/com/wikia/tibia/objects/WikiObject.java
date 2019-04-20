package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.Status;
import lombok.Getter;

@Getter
public abstract class WikiObject extends WrappedWikiObject {

    private final String templateType;
    private final String name;
    private final Article article;
    private final String actualname;
    private final String plural;
    private final String implemented;
    private final String notes;
    private String history;
    private final Status status;

    protected WikiObject() {
        templateType = null;
        name = null;
        article = null;
        actualname = null;
        plural = null;
        implemented = null;
        notes = null;
        history = null;
        status = null;
    }

    public WikiObject(String name, Article article, String actualname, String plural, String implemented, String notes,
                      String history, Status status) {
        this.templateType = null;
        this.name = name;
        this.article = article;
        this.actualname = actualname;
        this.plural = plural;
        this.implemented = implemented;
        this.notes = notes;
        this.history = history;
        this.status = status;
    }

    @JsonIgnore
    public boolean notDeprecatedOrEvent() {
        return status == null || status.notDeprecatedTsOrEvent();
    }

    @JsonIgnore
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Class: " + getClassName() + ", name: " + getName();
    }

    public void withHistory(String newHistory) {
        this.history = newHistory;
    }

    public static class WikiObjectImpl extends WikiObject {

        public WikiObjectImpl() {
            super();
        }
    }
}

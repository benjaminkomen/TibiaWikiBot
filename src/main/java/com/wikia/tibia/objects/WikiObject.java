package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WikiObject extends WrappedWikiObject {

    private String templateType;
    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private String implemented;
    private String notes;
    private String history;
    private Status status;

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

    public abstract void setDefaultValues();

    public static class WikiObjectImpl extends WikiObject {

        public WikiObjectImpl() {
            super();
        }

        @Override
        public void setDefaultValues() {
            // Do nothing
        }
    }
}

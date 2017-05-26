package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Article {

    A("a"),
    AN("an"),
    EMPTY("");

    private String description;

    Article(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
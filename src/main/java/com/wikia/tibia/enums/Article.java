package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum Article implements Description {

    A("a"),
    AN("an"),
    EMPTY("");

    private final String description;

    Article(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
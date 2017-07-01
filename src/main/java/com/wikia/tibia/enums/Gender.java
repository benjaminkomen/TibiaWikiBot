package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum Gender implements Description {

    FEMALE("Female"),
    MALE("Male"),
    UNKNOWN("Unknown"),
    EMPTY("");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
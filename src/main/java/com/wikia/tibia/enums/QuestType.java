package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum QuestType implements Description {

    WORLD_CHANGE("change"),
    MINI_WORLD_CHANGE("mwc"),
    WORLD_EVENT("event"),
    WORLD_TASK("task");

    private final String description;

    QuestType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
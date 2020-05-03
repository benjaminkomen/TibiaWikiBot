package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum Status implements Description {

    ACTIVE("Active"),
    DEPRECATED("deprecated"),
    UNOBTAINABLE("unobtainable"),
    TS_ONLY_LOWERCASE("ts-only"),
    TS_ONLY_UPPERCASE("TS-only"),
    EVENT("event");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public boolean notDeprecatedTsOrEvent() {
        return this != DEPRECATED && this != TS_ONLY_LOWERCASE && this != TS_ONLY_UPPERCASE && this != EVENT;
    }
}
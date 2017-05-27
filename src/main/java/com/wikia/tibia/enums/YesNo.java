package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum YesNo {

    YES_LOWERCASE("yes"),
    YES_UPPERCASE("Yes"),
    YES_DOT("Yes."),
    YES_UNKNOWN("yes?"),
    NO_LOWERCASE("no"),
    NO_UPPERCASE("No"),
    NO_DOT("No."),
    NO_UNKNOWN("no?"),
    UNKNOWN("?"),
    EMPTY("");

    private String description;

    YesNo(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}

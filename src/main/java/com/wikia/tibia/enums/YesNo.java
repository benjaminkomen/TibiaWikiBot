package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum YesNo implements Description {

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

    public boolean isYes() {
        return this == YES_DOT ||
                this == YES_LOWERCASE ||
                this == YES_UNKNOWN ||
                this == YES_UPPERCASE;
    }

    public boolean isNo() {
        return this == NO_DOT ||
                this == NO_LOWERCASE ||
                this == NO_UNKNOWN ||
                this == NO_UPPERCASE;
    }
}

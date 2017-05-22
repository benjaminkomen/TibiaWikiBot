package com.wikia.tibia.enums;

public enum YesNo {

    YES("yes"),
    NO("no"),
    UNKNOWN("?");

    private String description;

    YesNo(String description) {
        this.description = description;
    }
}

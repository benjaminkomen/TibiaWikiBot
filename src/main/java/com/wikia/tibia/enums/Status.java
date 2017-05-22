package com.wikia.tibia.enums;

public enum Status {

    ACTIVE("Active"),
    DEPRECATED("deprecated"),
    UNOBTAINABLE("unobtainable"),
    TS_ONLY("ts-only");

    private String description;

    Status(String description) {
        this.description = description;
    }
}

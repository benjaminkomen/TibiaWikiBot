package com.wikia.tibia.enums;

public enum Article {

    A("a"),
    AN("an"),
    EMPTY("");

    private String description;

    Article(String description) {
        this.description = description;
    }
}

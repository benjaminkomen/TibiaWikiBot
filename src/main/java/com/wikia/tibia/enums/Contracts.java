package com.wikia.tibia.enums;

import com.wikia.tibia.interfaces.Description;

public enum Contracts implements Description {

    HOST("http://localhost:8080"),
    API(HOST.getDescription() + "/api"),
    ACHIEVEMENTS(API.getDescription() + "/achievements"),
    BOOKS(API.getDescription() + "/books"),
    CREATURES(API.getDescription() + "/creatures"),
    ITEMS(API.getDescription() + "/items"),
    ;

    private String description;

    Contracts(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

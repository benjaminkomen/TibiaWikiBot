package com.wikia.tibia.enums;

import com.wikia.tibia.interfaces.Description;

public enum Contracts implements Description {

    HOST("http://localhost:8080"),
    API(HOST.getDescription() + "/api"),
    ACHIEVEMENTS(API.getDescription() + "/achievements"),
    BOOKS(API.getDescription() + "/books"),
    BUILDINGS(API.getDescription() + "/buildings"),
    CORPSES(API.getDescription() + "/corpses"),
    CREATURES(API.getDescription() + "/creatures"),
    EFFECTS(API.getDescription() + "/effects"),
    HUNTING_PLACES(API.getDescription() + "/huntingplaces"),
    ITEMS(API.getDescription() + "/items"),
    KEYS(API.getDescription() + "/keys"),
    LOCATIONS(API.getDescription() + "/locations"),
    @Deprecated
    LOOT_STATISTICS(API.getDescription() + "/loot"),
    LOOT_STATISTICS_V2(API.getDescription() + "/v2/loot"),
    MISSILES(API.getDescription() + "/missiles"),
    MOUNTS(API.getDescription() + "/mounts"),
    NPCS(API.getDescription() + "/npcs"),
    OBJECTS(API.getDescription() + "/objects"),
    OUTFITS(API.getDescription() + "/outfits"),
    QUESTS(API.getDescription() + "/quests"),
    SPELLS(API.getDescription() + "/spells"),
    STREETS(API.getDescription() + "/streets");

    private final String description;

    Contracts(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

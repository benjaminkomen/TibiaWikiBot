package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class Contract(@JsonValue override val description: String) : Description {
    HOST("http://localhost:8080"),
    API(HOST.description + "/api"),
    ACHIEVEMENTS(API.description + "/achievements"),
    BOOKS(API.description + "/books"),
    BUILDINGS(API.description + "/buildings"),
    CORPSES(API.description + "/corpses"),
    CREATURES(API.description + "/creatures"),
    EFFECTS(API.description + "/effects"),
    HUNTING_PLACES(API.description + "/huntingplaces"),
    ITEMS(API.description + "/items"),
    KEYS(API.description + "/keys"),
    LOCATIONS(API.description + "/locations"),

    @Deprecated("use LOOT_STATISTICS_V2")
    LOOT_STATISTICS(API.description + "/loot"),
    LOOT_STATISTICS_V2(API.description + "/v2/loot"),
    MISSILES(API.description + "/missiles"),
    MOUNTS(API.description + "/mounts"),
    NPCS(API.description + "/npcs"),
    OBJECTS(API.description + "/objects"),
    OUTFITS(API.description + "/outfits"),
    QUESTS(API.description + "/quests"),
    SPELLS(API.description + "/spells"),
    STREETS(API.description + "/streets");
}

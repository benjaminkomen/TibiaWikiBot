package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wikia.tibia.interfaces.Description;

public enum KeyType implements Description {

    BONE("Bone"),
    COPPER("Copper"),
    CRYSTAL("Crystal"),
    GOLDEN("Golden"),
    GOBLIN_BONE("Goblin Bone"),
    MAGICAL("Magical"),
    OTHER("Other"),
    SILVER("Silver"),
    WOODEN("Wooden");

    private String description;

    KeyType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
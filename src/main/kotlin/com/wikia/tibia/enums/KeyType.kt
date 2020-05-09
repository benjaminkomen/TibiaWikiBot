package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class KeyType(private val description: String) : Description {
    BONE("Bone"),
    COPPER("Copper"),
    CRYSTAL("Crystal"),
    GOLDEN("Golden"),
    GOBLIN_BONE("Goblin Bone"),
    MAGICAL("Magical"),
    OTHER("Other"),
    SILVER("Silver"),
    WOODEN("Wooden");

    @JsonValue
    override fun getDescription(): String {
        return description
    }
}
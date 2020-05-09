package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class BestiaryOccurrence(private val description: String) : Description {
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    VERY_RARE("Very Rare");

    @JsonValue
    override fun getDescription(): String {
        return description
    }

}
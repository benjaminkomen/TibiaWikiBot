package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class Gender(private val description: String) : Description {
    FEMALE("Female"),
    MALE("Male"),
    UNKNOWN("Unknown"),
    EMPTY("");

    @JsonValue
    override fun getDescription(): String {
        return description
    }
}
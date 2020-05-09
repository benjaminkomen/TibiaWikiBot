package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class Article(private val description: String) : Description {
    A("a"),
    AN("an"),
    EMPTY("");

    @JsonValue
    override fun getDescription(): String {
        return description
    }
}
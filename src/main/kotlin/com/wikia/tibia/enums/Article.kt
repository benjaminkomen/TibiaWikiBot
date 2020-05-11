package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class Article(@JsonValue override val description: String) : Description {
    A("a"),
    AN("an"),
    EMPTY("");
}
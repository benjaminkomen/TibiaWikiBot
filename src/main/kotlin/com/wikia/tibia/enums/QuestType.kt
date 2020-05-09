package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class QuestType(private val description: String) : Description {
    WORLD_CHANGE("change"),
    MINI_WORLD_CHANGE("mwc"),
    WORLD_EVENT("event"),
    WORLD_TASK("task");

    @JsonValue
    override fun getDescription(): String {
        return description
    }
}
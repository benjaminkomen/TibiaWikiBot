package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class Status(@JsonValue override val description: String) : Description {
    ACTIVE("Active"),
    DEPRECATED("deprecated"),
    UNOBTAINABLE("unobtainable"),
    TS_ONLY_LOWERCASE("ts-only"),
    TS_ONLY_UPPERCASE("TS-only"),
    EVENT("event");

    fun notDeprecatedTsOrEvent(): Boolean {
        return this != DEPRECATED && this != TS_ONLY_LOWERCASE && this != TS_ONLY_UPPERCASE && this != EVENT
    }
}

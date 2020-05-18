package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class YesNo(@JsonValue override val description: String) : Description {
    YES_LOWERCASE("yes"),
    YES_UPPERCASE("Yes"),
    YES_DOT("Yes."),
    YES_UNKNOWN("yes?"),
    NO_LOWERCASE("no"),
    NO_UPPERCASE("No"),
    NO_DOT("No."),
    NO_UNKNOWN("no?"),
    UNKNOWN("?"),
    EMPTY("");

    val isYes: Boolean
        get() = this == YES_DOT || this == YES_LOWERCASE || this == YES_UNKNOWN || this == YES_UPPERCASE

    val isNo: Boolean
        get() = this == NO_DOT || this == NO_LOWERCASE || this == NO_UNKNOWN || this == NO_UPPERCASE

}
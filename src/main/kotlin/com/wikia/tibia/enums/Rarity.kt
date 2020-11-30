package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

/**
 * See also: https://tibia.fandom.com/wiki/Rareness
 */
enum class Rarity(@JsonValue override val description: String) : Description {
    ALWAYS("always"),
    COMMON("common"),
    UNCOMMON("uncommon"),
    SEMI_RARE("semi-rare"),
    RARE("rare"),
    VERY_RARE("very rare"),
    EXTREMELY_RARE("extremely rare");
}

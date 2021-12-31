package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class BestiaryOccurrence(@JsonValue override val description: String) : Description {
  COMMON("Common"),
  UNCOMMON("Uncommon"),
  RARE("Rare"),
  VERY_RARE("Very Rare");
}

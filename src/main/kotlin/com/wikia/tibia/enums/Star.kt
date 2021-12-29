package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue

// TODO does this JsonValue notation really work on the val?
enum class Star(@get:JsonValue val number: Int) {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5);
}

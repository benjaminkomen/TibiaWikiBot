package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonValue
import lombok.Getter
import lombok.NoArgsConstructor
import java.util.*

data class Percentage(
    @get:JsonValue var originalValue: String,
    private var value: Int?
) {

    private constructor(value: String) {
        originalValue = value
        this.value = sanitize(value)
    }

    private constructor(value: Int) {
        originalValue = "$value%"
        this.value = value
    }

    private fun sanitize(value: String): Int? {
        val sanitizedValue = value.replace("\\D+".toRegex(), "")
        return if (sanitizedValue.isEmpty()) {
            null
        } else Integer.valueOf(sanitizedValue)
    }

    // Equality is determined by originalValue, because the value can strip away question marks and is therefore not reliable for a correct equality check
    override fun equals(other: Any?): Boolean {
        return other is Percentage &&
                originalValue == other.originalValue
    }

    override fun hashCode(): Int {
        return Objects.hash(originalValue)
    }

    companion object {
        val EMPTY = of("")
        val UNKNOWN = of("100%?")

        fun of(value: String): Percentage {
            return Percentage(value)
        }

        fun of(value: Int): Percentage {
            return Percentage(value)
        }
    }
}
package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import java.util.Objects

data class Percentage(
    @get:JsonValue val originalValue: String,
    @JsonIgnore private val value: Int? = null
) {

    // Equality is determined by originalValue, because the value can strip away question marks and is therefore not reliable for a correct equality check
    override fun equals(other: Any?): Boolean {
        return other is Percentage &&
            originalValue == other.originalValue
    }

    override fun hashCode(): Int {
        return Objects.hash(originalValue)
    }

    override fun toString(): String {
        return if (value != null) "$value" else ""
    }

    companion object {
        val EMPTY = of("")
        val UNKNOWN = of("100%?")

        private fun sanitize(value: String): Int? {
            val sanitizedValue = value.replace("""\D+""".toRegex(), "") // strip anything not a digit away
            return if (sanitizedValue.isEmpty()) {
                null
            } else Integer.valueOf(sanitizedValue)
        }

        @JsonCreator
        @JvmStatic
        fun of(value: String): Percentage {
            return Percentage(
                originalValue = value,
                value = sanitize(value)
            )
        }

        fun of(value: Int): Percentage {
            return Percentage(
                originalValue = "$value%",
                value = value
            )
        }
    }
}

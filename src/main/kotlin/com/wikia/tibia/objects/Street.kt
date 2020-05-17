package com.wikia.tibia.objects

import com.wikia.tibia.enums.City
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Street(
         val name: String,
        private val implemented: String,
        private val notes: String,
        private val city: City,
        private val city2: City,
        private val map: String,
        private val floor: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
    }
}
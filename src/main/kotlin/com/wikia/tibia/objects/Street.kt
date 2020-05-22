package com.wikia.tibia.objects

import com.wikia.tibia.enums.City
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Street(
        val name: String,
        val implemented: String,
        val notes: String,
        val city: City,
        val city2: City,
        val map: String,
        val floor: String
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
    }
}
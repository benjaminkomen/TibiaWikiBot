package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Mount(
         val name: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val speed: Int,
        @get:JsonGetter("taming_method") private val tamingMethod: String,
        private val bought: YesNo,
        private val tournament: YesNo, // unit is Tibia Coins
        private val price: Int, // this could link to Achievement
        private val achievement: String,
        private val lightradius: Int,
        private val lightcolor: Int,
        private val artwork: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
    }

}
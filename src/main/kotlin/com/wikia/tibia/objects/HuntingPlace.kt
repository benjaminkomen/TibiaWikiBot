package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Star
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class HuntingPlace(
        private val name: String,
        private val implemented: String?,
        private val image: String?,
        private val city: City,
        private val location: String?,
        private val vocation: String,
        private val lvlknights: String?,
        private val lvlpaladins: String?,
        private val lvlmages: String?,
        private val skknights: String,
        private val skpaladins: String,
        private val skmages: String,
        private val defknights: String,
        private val defpaladins: String,
        private val defmages: String,
        @JsonManagedReference private val lowerlevels: List<HuntingPlaceSkills>,
        private val loot: String?,
        private val lootstar: Star,
        private val exp: String?,
        private val expstar: Star,
        private val bestloot: String,
        private val bestloot2: String,
        private val bestloot3: String,
        private val bestloot4: String,
        private val bestloot5: String,
        private val map: String,
        private val map2: String,
        private val map3: String,
        private val map4: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(image)) {
            this.copy(image = "None")
        }
        if (isEmpty(location)) {
            this.copy(location = "Unknown")
        }
        if (isEmpty(lvlknights)) {
            this.copy(lvlknights = "?")
        }
        if (isEmpty(lvlpaladins)) {
            this.copy(lvlpaladins = "?")
        }
        if (isEmpty(lvlmages)) {
            this.copy(lvlmages = "?")
        }
        if (isEmpty(exp)) {
            this.copy(exp = "Unknown")
        }
        if (isEmpty(loot)) {
            this.copy(loot = "Unknown")
        }
    }
}

data class HuntingPlaceSkills(
        private val areaname: String,
        private val lvlknights: String,
        private val lvlpaladins: String,
        private val lvlmages: String,
        private val skknights: String,
        private val skpaladins: String,
        private val skmages: String,
        private val defknights: String,
        private val defpaladins: String,
        private val defmages: String,
        @JsonBackReference private val huntingPlace: HuntingPlace
)

package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Star
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class HuntingPlace(
        private val name: String,
        private var implemented: String?,
        private var image: String?,
        private val city: City,
        private var location: String?,
        private val vocation: String,
        private var lvlknights: String?,
        private var lvlpaladins: String?,
        private var lvlmages: String?,
        private val skknights: String,
        private val skpaladins: String,
        private val skmages: String,
        private val defknights: String,
        private val defpaladins: String,
        private val defmages: String,
        @JsonManagedReference private val lowerlevels: List<HuntingPlaceSkills>,
        private var loot: String?,
        private val lootstar: Star,
        private var exp: String?,
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
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(image)) {
            image = "None"
        }
        if (isEmpty(location)) {
            location = "Unknown"
        }
        if (isEmpty(lvlknights)) {
            lvlknights = "?"
        }
        if (isEmpty(lvlpaladins)) {
            lvlpaladins = "?"
        }
        if (isEmpty(lvlmages)) {
            lvlmages = "?"
        }
        if (isEmpty(exp)) {
            exp = "Unknown"
        }
        if (isEmpty(loot)) {
            loot = "Unknown"
        }
    }
}
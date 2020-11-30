package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Star
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class HuntingPlace(
    val name: String,
    val implemented: String?,
    val image: String?,
    val city: City,
    val location: String?,
    val vocation: String,
    val lvlknights: String?,
    val lvlpaladins: String?,
    val lvlmages: String?,
    val skknights: String,
    val skpaladins: String,
    val skmages: String,
    val defknights: String,
    val defpaladins: String,
    val defmages: String,
    @JsonManagedReference val lowerlevels: List<HuntingPlaceSkills>,
    val loot: String?,
    val lootstar: Star,
    val exp: String?,
    val expstar: Star,
    val bestloot: String,
    val bestloot2: String,
    val bestloot3: String,
    val bestloot4: String,
    val bestloot5: String,
    val map: String,
    val map2: String,
    val map3: String,
    val map4: String
) : WikiObject() {

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
    val areaname: String,
    val lvlknights: String,
    val lvlpaladins: String,
    val lvlmages: String,
    val skknights: String,
    val skpaladins: String,
    val skmages: String,
    val defknights: String,
    val defpaladins: String,
    val defmages: String,
    @JsonBackReference val huntingPlace: HuntingPlace
)

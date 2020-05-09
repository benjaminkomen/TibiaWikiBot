package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonBackReference

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
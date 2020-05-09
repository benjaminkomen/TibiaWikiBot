package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Location(
        private val name: String,
        private var implemented: String?,
        private val status: Status,
        private val ruler: String,
        private val population: String,
        private var near: String?,
        private val organization: String,
        private val map: String,
        private val map2: String,
        private val map3: String,
        private val map4: String,
        private val map5: String,
        private val map6: String,
        private var links: YesNo?
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(near)) {
            near = "?"
        }
        if (isEmpty(links)) {
            links = YesNo.UNKNOWN
        }
    }

}
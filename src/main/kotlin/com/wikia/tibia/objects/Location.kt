package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Location(
        val name: String,
        val implemented: String?,
        val status: Status,
        val ruler: String,
        val population: String,
        val near: String?,
        val organization: String,
        val map: String,
        val map2: String,
        val map3: String,
        val map4: String,
        val map5: String,
        val map6: String,
        val links: YesNo?
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(near)) {
            this.copy(near = "?")
        }
        if (isEmpty(links)) {
            this.copy(links = YesNo.UNKNOWN)
        }
    }

}
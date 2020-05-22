package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Outfit(
        val name: String,
        val implemented: String,
        val notes: String,
        val history: String,
        val status: Status,
        val primarytype: String,
        val secondarytype: String,
        val premium: YesNo, // class and field name are the same, but that's understandable
        val outfit: String,
        val addons: String,
        val bought: YesNo,
        val fulloutfitprice: Int,
        val achievement: String,
        val artwork: String
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(primarytype)) {
            this.copy(primarytype = "")
            logger.warn("Outfit '{}' has no primarytype set", name)
        }
        if (isEmpty(premium)) {
            this.copy(premium = YesNo.UNKNOWN)
        }
        if (isEmpty(outfit)) {
            this.copy(outfit = "?")
        }
        if (isEmpty(addons)) {
            this.copy(addons = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Outfit::class.java)
    }
}
package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Outfit(
        private val name: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val primarytype: String,
        private val secondarytype: String,
        private val premium: YesNo, // class and field name are the same, but that's understandable
        private val outfit: String,
        private val addons: String,
        private val bought: YesNo,
        private val fulloutfitprice: Int,
        private val achievement: String,
        private val artwork: String
): WikiObject() {

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
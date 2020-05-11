package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Missile(
        private val name: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val missileid: Int,
        private val primarytype: String,
        private val secondarytype: String,
        private val lightradius: Int,
        private val lightcolor: Int,
        private val shotby: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(missileid)) {
            logger.warn("Creature '{}' has no missileId set", name)
        }
        if (isEmpty(primarytype)) {
            this.copy(primarytype = "?")
        }
        if (isEmpty(shotby)) {
            this.copy(shotby = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Missile::class.java)
    }
}
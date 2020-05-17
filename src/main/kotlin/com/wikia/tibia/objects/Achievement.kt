package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Achievement(
        val name: String,
        private val actualname: String,
        private val implemented: String?,
        private val history: String,
        private val status: Status,
        private val grade: Int,
        private val description: String,
        private val spoiler: String,
        private val premium: YesNo?,
        private val points: Int?,
        private val secret: YesNo?,
        private val coincideswith: Int,
        private val achievementid: Int?,
        private val relatedpages: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(grade)) {
            logger.warn("Achievement '{}' has no grade set", name)
            this.copy(grade = -1)
        }
        if (isEmpty(premium)) {
            this.copy(premium = YesNo.UNKNOWN)
        }
        if (isEmpty(points)) {
            logger.warn("Achievement '{}' has no points set", name)
            this.copy(points = -1)
        }
        if (isEmpty(secret)) {
            this.copy(secret = YesNo.UNKNOWN)
        }
        if (isEmpty(achievementid)) {
            logger.warn("Achievement '{}' has no achievementId set", name)
            this.copy(achievementid = -1)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Achievement::class.java)
    }
}
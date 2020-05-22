package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Achievement(
        val name: String,
        val actualname: String,
        val implemented: String?,
        val history: String,
        val status: Status,
        val grade: Int,
        val description: String,
        val spoiler: String,
        val premium: YesNo?,
        val points: Int?,
        val secret: YesNo?,
        val coincideswith: Int,
        val achievementid: Int?,
        val relatedpages: String
) : WikiObject() {

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
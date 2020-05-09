package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Achievement(
        private val name: String,
        private val actualname: String,
        private var implemented: String?,
        private val history: String,
        private val status: Status,
        private var grade: Int,
        private val description: String,
        private val spoiler: String,
        private var premium: YesNo?,
        private var points: Int?,
        private var secret: YesNo?,
        private val coincideswith: Int,
        private var achievementid: Int?,
        private val relatedpages: String
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(grade)) {
            logger.warn("Achievement '{}' has no grade set", name)
            grade = -1
        }
        if (isEmpty(premium)) {
            premium = YesNo.UNKNOWN
        }
        if (isEmpty(points)) {
            logger.warn("Achievement '{}' has no points set", name)
            points = -1
        }
        if (isEmpty(secret)) {
            secret = YesNo.UNKNOWN
        }
        if (isEmpty(achievementid)) {
            logger.warn("Achievement '{}' has no achievementId set", name)
            achievementid = -1
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Achievement::class.java)
    }
}
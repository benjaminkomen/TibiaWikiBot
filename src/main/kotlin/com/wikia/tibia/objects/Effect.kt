package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Effect(
        val name: String,
        private val implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val effectid: List<Int?>?,
        private val primarytype: String?,
        private val secondarytype: String,
        private val lightradius: Int,
        private val lightcolor: Int,
        private val causes: String?,
        private val effect: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(effectid)) {
            logger.warn("Effect '{}' has no effectid set", name)
        }
        if (isEmpty(primarytype)) {
            this.copy(primarytype = "?")
            logger.warn("Effect '{}' has no primarytype set", name)
        }
        if (isEmpty(causes)) {
            this.copy(causes = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Effect::class.java)
    }
}
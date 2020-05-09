package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Effect(
        private val name: String,
        private var implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val effectid: List<Int?>?,
        private var primarytype: String?,
        private val secondarytype: String,
        private val lightradius: Int,
        private val lightcolor: Int,
        private var causes: String?,
        private val effect: String
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(effectid)) {
            logger.warn("Effect '{}' has no effectid set", name)
        }
        if (isEmpty(primarytype)) {
            primarytype = "?"
            logger.warn("Effect '{}' has no primarytype set", name)
        }
        if (isEmpty(causes)) {
            causes = "?"
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Effect::class.java)
    }
}
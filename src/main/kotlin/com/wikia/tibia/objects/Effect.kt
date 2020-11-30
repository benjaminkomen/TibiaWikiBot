package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Effect(
    val name: String,
    val implemented: String?,
    val notes: String,
    val history: String,
    val status: Status,
    val effectid: List<Int?>?,
    val primarytype: String?,
    val secondarytype: String,
    val lightradius: Int,
    val lightcolor: Int,
    val causes: String?,
    val effect: String
) : WikiObject() {

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

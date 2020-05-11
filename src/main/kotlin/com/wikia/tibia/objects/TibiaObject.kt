package com.wikia.tibia.objects

import com.wikia.tibia.enums.Article
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class TibiaObject(
        private val name: String,
        private val article: Article,
        private val actualname: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val itemid: List<Int>,
        private val objectclass: String,
        private val secondarytype: String,
        private val tertiarytype: String,
        private val flavortext: String,
        private val lightradius: Int,
        private val lightcolor: Int,
        private val volume: Int,
        private val destructable: YesNo,
        private val immobile: YesNo,
        private val attrib: String,
        private val walkable: YesNo,
        private val walkingspeed: Int,
        private val unshootable: YesNo,
        private val blockspath: YesNo,
        private val pickupable: YesNo,
        private val holdsliquid: YesNo,
        private val usable: YesNo,
        private val writable: YesNo,
        private val rewritable: YesNo,
        private val writechars: Int,
        private val rotatable: YesNo,
        private val mapcolor: Int,
        private val location: String,
        private val notes2: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(objectclass)) {
            this.copy(objectclass = "")
            logger.warn("Creature '{}' has no objectclass set", name)
        }
        if (isEmpty(walkable)) {
            this.copy(walkable = YesNo.UNKNOWN)
        }
        if (isEmpty(location)) {
            this.copy(location = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TibiaObject::class.java)
    }

}
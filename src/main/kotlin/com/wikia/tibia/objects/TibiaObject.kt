package com.wikia.tibia.objects

import com.wikia.tibia.enums.Article
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class TibiaObject(
    val name: String,
    val article: Article,
    val actualname: String,
    val implemented: String,
    val notes: String,
    val history: String,
    val status: Status,
    val itemid: List<Int>,
    val objectclass: String,
    val secondarytype: String,
    val tertiarytype: String,
    val flavortext: String,
    val lightradius: Int,
    val lightcolor: Int,
    val volume: Int,
    val destructable: YesNo,
    val immobile: YesNo,
    val attrib: String,
    val walkable: YesNo,
    val walkingspeed: Int,
    val unshootable: YesNo,
    val blockspath: YesNo,
    val pickupable: YesNo,
    val holdsliquid: YesNo,
    val usable: YesNo,
    val writable: YesNo,
    val rewritable: YesNo,
    val writechars: Int,
    val rotatable: YesNo,
    val mapcolor: Int,
    val location: String,
    val notes2: String
) : WikiObject() {

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

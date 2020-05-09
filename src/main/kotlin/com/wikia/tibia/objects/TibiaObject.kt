package com.wikia.tibia.objects

import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*
import org.slf4j.LoggerFactory

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class TibiaObject @Builder private constructor(name: String, article: Article, actualname: String, implemented: String, notes: String, history: String,
                                               status: Status, private val itemid: List<Int>, private var objectclass: String, private val secondarytype: String, private val tertiarytype: String,
                                               private val flavortext: String, private val lightradius: Int, private val lightcolor: Int, private val volume: Int, private val destructable: YesNo,
                                               private val immobile: YesNo, private val attrib: String, private var walkable: YesNo, private val walkingspeed: Int, private val unshootable: YesNo,
                                               private val blockspath: YesNo, private val pickupable: YesNo, private val holdsliquid: YesNo, private val usable: YesNo, private val writable: YesNo,
                                               private val rewritable: YesNo, private val writechars: Int, private val rotatable: YesNo, private val mapcolor: Int, private var location: String,
                                               private val notes2: String) : WikiObject(name, article, actualname, null, implemented, notes, history, status) {
    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(objectclass)) {
            objectclass = ""
            LOG.warn("Creature '{}' has no objectclass set", name)
        }
        if (isEmpty(walkable)) {
            walkable = YesNo.UNKNOWN
        }
        if (isEmpty(location)) {
            location = "?"
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(TibiaObject::class.java)
    }

}
package com.wikia.tibia.objects

import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*
import org.slf4j.LoggerFactory

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Missile @Builder private constructor(name: String, implemented: String, notes: String, history: String, status: Status, private val missileid: Int,
                                           private var primarytype: String, private val secondarytype: String, private val lightradius: Int, private val lightcolor: Int, private var shotby: String) : WikiObject(name, null, null, null, implemented, notes, history, status) {
    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(missileid)) {
            LOG.warn("Creature '{}' has no missileId set", name)
        }
        if (isEmpty(primarytype)) {
            primarytype = "?"
        }
        if (isEmpty(shotby)) {
            shotby = "?"
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Missile::class.java)
    }

}
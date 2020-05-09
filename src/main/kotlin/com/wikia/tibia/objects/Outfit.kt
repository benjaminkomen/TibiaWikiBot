package com.wikia.tibia.objects

import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*
import org.slf4j.LoggerFactory

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Outfit @Builder private constructor(name: String, implemented: String, notes: String, history: String, status: Status, private var primarytype: String,
                                          private val secondarytype: String, private var premium: YesNo, // class and field name are the same, but that's understandable
                                          private var outfit: String, private var addons: String, private val bought: YesNo,
                                          private val fulloutfitprice: Int, private val achievement: String, private val artwork: String) : WikiObject(name, null, null, null, implemented, notes, history, status) {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(primarytype)) {
            primarytype = ""
            LOG.warn("Outfit '{}' has no primarytype set", name)
        }
        if (isEmpty(premium)) {
            premium = YesNo.UNKNOWN
        }
        if (isEmpty(outfit)) {
            outfit = "?"
        }
        if (isEmpty(addons)) {
            addons = "?"
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Outfit::class.java)
    }

}
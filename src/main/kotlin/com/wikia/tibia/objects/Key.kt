package com.wikia.tibia.objects

import com.wikia.tibia.enums.KeyType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Key(
        private val name: String,
        private var implemented: String?,
        private val history: String,
        private val status: Status,
        private var number: String?,
        private val aka: String,
        private val primarytype: KeyType?,
        private val secondarytype: KeyType,
        private val location: String,
        private val value: String,
        private val npcvalue: Int,
        private val npcprice: Int,
        private val buyfrom: String,
        private val sellto: String,
        private val origin: String,
        private var shortnotes: String?,
        private val longnotes: String
) {
    fun getName() = String.format("Key %s", number)

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(number)) {
            number = "?"
        }
        if (isEmpty(primarytype)) {
            logger.warn("Key '{}' has no primarytype set", getName())
        }
        if (isEmpty(shortnotes)) {
            shortnotes = "?"
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Key::class.java)
    }
}
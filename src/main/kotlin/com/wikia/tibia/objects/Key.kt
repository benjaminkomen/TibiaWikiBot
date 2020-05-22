package com.wikia.tibia.objects

import com.wikia.tibia.enums.KeyType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Key(
        val implemented: String?,
        val history: String,
        val status: Status,
        val number: String?,
        val aka: String,
        val primarytype: KeyType?,
        val secondarytype: KeyType,
        val location: String,
        val value: String,
        val npcvalue: Int,
        val npcprice: Int,
        val buyfrom: String,
        val sellto: String,
        val origin: String,
        val shortnotes: String?,
        val longnotes: String
) : WikiObject() {

    fun getName() = String.format("Key %s", number)

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(number)) {
            this.copy(number = "?")
        }
        if (isEmpty(primarytype)) {
            logger.warn("Key '{}' has no primarytype set", getName())
        }
        if (isEmpty(shortnotes)) {
            this.copy(shortnotes = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Key::class.java)
    }
}
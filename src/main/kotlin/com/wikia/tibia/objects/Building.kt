package com.wikia.tibia.objects

import com.wikia.tibia.enums.BuildingType
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Building(
        private val name: String,
        private var implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val type: BuildingType,
        private var location: String?,
        private var posx: String?,
        private var posy: String?,
        private var posz: String?,
        private var street: String?,
        private val street2: String,
        private val street3: String,
        private val street4: String,
        private val street5: String,
        private val houseid: Int,
        private val size: Int,
        private val beds: Int,
        private val rent: Int,
        private val ownable: YesNo,
        private val city: City,
        private val openwindows: Int,
        private val floors: Int,
        private val rooms: Int,
        private val furnishings: String,
        private val image: String
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(type)) {
            logger.warn("Building '{}' has no type set", name)
        }
        if (isEmpty(location)) {
            location = "?"
        }
        if (isEmpty(posx)) {
            posx = "?"
        }
        if (isEmpty(posy)) {
            posy = "?"
        }
        if (isEmpty(posz)) {
            posz = "?"
        }
        if (isEmpty(street)) {
            street = "?"
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(houseid)) {
            logger.warn("Building '{}' has no houseid set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(size)) {
            logger.warn("Building '{}' has no size set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(beds)) {
            logger.warn("Building '{}' has no beds set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(rent)) {
            logger.warn("Building '{}' has no rent set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(openwindows)) {
            logger.warn("Building '{}' has no openwindows set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(floors)) {
            logger.warn("Building '{}' has no floors set", name)
        }
        if ((isEmpty(ownable) || ownable.isYes) && isEmpty(rooms)) {
            logger.warn("Building '{}' has no rooms set", name)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Building::class.java)
    }
}
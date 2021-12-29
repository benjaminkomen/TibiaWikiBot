package com.wikia.tibia.objects

import com.wikia.tibia.enums.BuildingType
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Building(
  val name: String,
  val implemented: String?,
  val notes: String,
  val history: String,
  val status: Status,
  val type: BuildingType,
  val location: String?,
  val posx: String?,
  val posy: String?,
  val posz: String?,
  val street: String?,
  val street2: String? = null,
  val street3: String? = null,
  val street4: String? = null,
  val street5: String? = null,
  val houseid: Int,
  val size: Int,
  val beds: Int,
  val rent: Int,
  val ownable: YesNo,
  val city: City,
  val openwindows: Int,
  val floors: Int,
  val rooms: Int,
  val furnishings: String,
  val image: String
) : WikiObject() {

  override fun setDefaultValues() {
    if (isEmpty(implemented)) {
      this.copy(implemented = "?")
    }
    if (isEmpty(type)) {
      logger.warn("Building '{}' has no type set", name)
    }
    if (isEmpty(location)) {
      this.copy(location = "?")
    }
    if (isEmpty(posx)) {
      this.copy(posx = "?")
    }
    if (isEmpty(posy)) {
      this.copy(posy = "?")
    }
    if (isEmpty(posz)) {
      this.copy(posz = "?")
    }
    if (isEmpty(street)) {
      this.copy(street = "?")
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

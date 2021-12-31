package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Missile(
  val name: String,
  val implemented: String,
  val notes: String,
  val history: String,
  val status: Status,
  val missileid: Int,
  val primarytype: String,
  val secondarytype: String,
  val lightradius: Int,
  val lightcolor: Int,
  val shotby: String
) : WikiObject() {

  override fun setDefaultValues() {
    if (isEmpty(implemented)) {
      this.copy(implemented = "?")
    }
    if (isEmpty(missileid)) {
      logger.warn("Creature '{}' has no missileId set", name)
    }
    if (isEmpty(primarytype)) {
      this.copy(primarytype = "?")
    }
    if (isEmpty(shotby)) {
      this.copy(shotby = "?")
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger(Missile::class.java)
  }
}

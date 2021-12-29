package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Mount(
  val name: String,
  val implemented: String,
  val notes: String,
  val history: String,
  val status: Status,
  val speed: Int,
  @get:JsonGetter("taming_method") val tamingMethod: String,
  val bought: YesNo,
  val tournament: YesNo, // unit is Tibia Coins
  val price: Int, // this could link to Achievement
  val achievement: String,
  val lightradius: Int,
  val lightcolor: Int,
  val artwork: String
) : WikiObject() {

  override fun setDefaultValues() {
    if (isEmpty(implemented)) {
      this.copy(implemented = "?")
    }
  }
}

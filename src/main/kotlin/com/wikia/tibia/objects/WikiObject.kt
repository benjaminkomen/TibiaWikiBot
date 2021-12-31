package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status

abstract class WikiObject {
  val templateType: String? = null

  fun isActive(status: Status?): Boolean {
    return status == null || status.isActive()
  }

  abstract fun setDefaultValues()

  class WikiObjectImpl : WikiObject() {
    override fun setDefaultValues() {
      // Do nothing
    }
  }
}

package com.wikia.tibia.objects

import com.wikia.tibia.enums.Status

abstract class WikiObject {
    private val templateType: String? = null

    fun notDeprecatedOrEvent(status: Status?): Boolean {
        return status == null || status.notDeprecatedTsOrEvent()
    }

    abstract fun setDefaultValues()

    class WikiObjectImpl : WikiObject() {
        override fun setDefaultValues() {
            // Do nothing
        }
    }
}
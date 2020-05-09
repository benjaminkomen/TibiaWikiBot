package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonIgnore
import com.wikia.tibia.enums.Article
import com.wikia.tibia.enums.Status
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.json.JSONObject

abstract class WikiObject(
        private val name: String?,
        article: Article?,
        actualname: String?,
        plural: String?,
        implemented: String?,
        notes: String?,
        history: String?,
        status: Status?
) : WrappedWikiObject(JSONObject(), JSONObject()) {
    private val templateType: String? = null
    private val name: String?
    private val article: Article?
    private val actualname: String?
    private val plural: String?
    private val implemented: String?
    private val notes: String?
    private val history: String?
    private val status: Status?

    @JsonIgnore
    fun notDeprecatedOrEvent(): Boolean {
        return status == null || status.notDeprecatedTsOrEvent()
    }

    @get:JsonIgnore
    val className: String
        get() = this.javaClass.simpleName

    override fun toString(): String {
        return "Class: " + className + ", name: " + getName()
    }

    abstract fun setDefaultValues()
    class WikiObjectImpl : WikiObject() {
        override fun setDefaultValues() {
            // Do nothing
        }
    }

    init {
        this.article = article
        this.actualname = actualname
        this.plural = plural
        this.implemented = implemented
        this.notes = notes
        this.history = history
        this.status = status
    }
}
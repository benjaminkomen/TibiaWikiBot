package com.wikia.tibia.repositories

import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.jackson.Parser.list
import com.wikia.tibia.jackson.Parser.listOneByOne
import com.wikia.tibia.jackson.Parser.parse
import com.wikia.tibia.objects.Achievement
import com.wikia.tibia.objects.WikiObject
import io.vavr.control.Try

abstract class WikiObjectRepository<T : WikiObject>(
        private val wikiObjectClass: Class<T>,
        private val wikiObjectGateway: WikiObjectGateway<in WikiObject>
) {

    val wikiObjects: Try<List<T>>
        get() = wikiObjectGateway.getWikiObjects(true)
                .map { listOneByOne(wikiObjectClass, it, -1) }

    fun getWikiObjects(limit: Int = -1): Try<List<T>> {
        return wikiObjectGateway.getWikiObjects(true)
                .map { listOneByOne(wikiObjectClass, it, limit) }
    }

    val wikiObjectsList: Try<List<String>>
        get() = wikiObjectGateway.getWikiObjects(false)
                .map { list(String::class.java, it) }

    fun saveWikiObject(wikiObject: WikiObject, editSummary: String?, dryRun: Boolean): Try<String> {
        return wikiObjectGateway.saveWikiObject(wikiObject, editSummary, dryRun)
    }

    fun getWikiObject(pageName: String): Try<Any?> {
        return wikiObjectGateway.getWikiObject(pageName)
                .map { parse(wikiObjectClass, it) }
    }

}
package com.wikia.tibia.gateways

import com.google.common.net.UrlEscapers
import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.http.Header
import com.wikia.tibia.http.Request
import com.wikia.tibia.objects.WikiObject
import io.vavr.control.Try

abstract class WikiObjectGateway<T : WikiObject?> protected constructor(private val contracts: Contracts) {
    private val request: Request = Request()

    val wikiObjects: Try<String>
        get() = getWikiObjects(false)

    fun getWikiObjects(expand: Boolean): Try<String> {
        return request.get(java.lang.String.format("%s?expand=%s", contracts.description, expand))
    }

    fun saveWikiObject(wikiObject: T, editSummary: String, dryRun: Boolean): Try<String> {
        val header = Header("X-WIKI-Edit-Summary", editSummary)
        return request.put(
                location = contracts.description,
                jsonBody = wikiObject,
                header = header,
                dryRun = dryRun
        )
    }

    fun getWikiObject(pageName: String): Try<String> {
        return request.get("${contracts.description}/${UrlEscapers.urlFragmentEscaper().escape(pageName)}")
    }
}
package com.wikia.tibia.gateways

import com.google.common.net.UrlEscapers
import com.wikia.tibia.enums.Contract
import com.wikia.tibia.http.Request
import io.vavr.control.Try

class LootGateway {

    private val contracts = Contract.LOOT_STATISTICS_V2
    private val request: Request = Request()

    val loot: Try<String>
        get() = getLoot(false)

    fun getLoot(expand: Boolean): Try<String> {
        return request.get("${contracts.description}?expand=$expand")
    }

    fun getLoot(pageName: String): Try<String> {
        return request.get("${contracts.description}/${UrlEscapers.urlFragmentEscaper().escape(pageName)}")
    }
}
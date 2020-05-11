package com.wikia.tibia.repositories

import com.wikia.tibia.gateways.LootGateway
import com.wikia.tibia.jackson.Parser
import com.wikia.tibia.objects.LootWrapper
import io.vavr.control.Try

class LootRepository(
        private val lootGateway: LootGateway
) {

    val wikiObjects: Try<List<LootWrapper>>
        get() = lootGateway.getLoot(true)
                .map { Parser.listOneByOne(LootWrapper::class.java, it, -1) }

    fun getLoot(limit: Int): Try<List<LootWrapper>> {
        return lootGateway.getLoot(true)
                .map { Parser.listOneByOne(LootWrapper::class.java, it, limit) }
    }

    val loot: Try<List<String>>
        get() = lootGateway.getLoot(false)
                .map { Parser.list(String::class.java, it) }

    fun getLoot(pageName: String): Try<Any?> {
        return lootGateway.getLoot(pageName)
                .map { Parser.parse(LootWrapper::class.java, it) }
    }
}
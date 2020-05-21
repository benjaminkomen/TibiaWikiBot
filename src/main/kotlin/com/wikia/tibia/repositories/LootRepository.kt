package com.wikia.tibia.repositories

import com.wikia.tibia.gateways.LootGateway
import com.wikia.tibia.jackson.Parser
import com.wikia.tibia.objects.LootWrapper
import io.vavr.control.Try

class LootRepository(
        private val lootGateway: LootGateway
) {
    fun getLoot(): Try<List<LootWrapper>> {
        return lootGateway.getLoot(true)
                .map { Parser.listOneByOne(type = LootWrapper::class.java, json = it) }
    }

    fun getLoot(pageName: String): Try<LootWrapper> {
        return lootGateway.getLoot(pageName)
                .map { Parser.parse(LootWrapper::class.java, it) }
    }

    fun getLootList(): Try<List<String>> {
        return lootGateway.getLoot(false)
                .map { Parser.list(String::class.java, it) }
    }
}
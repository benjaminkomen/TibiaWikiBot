package com.wikia.tibia

import com.wikia.tibia.gateways.LootGateway
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.ItemRepository
import com.wikia.tibia.repositories.LootRepository
import com.wikia.tibia.usecases.FixCreatures
import com.wikia.tibia.usecases.FixItems
import com.wikia.tibia.usecases.FixLootStatistics
import org.slf4j.LoggerFactory

object Main {

    private val logger = LoggerFactory.getLogger(Main::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val fixLootStatistics = FixLootStatistics(
                creatureRepository = CreatureRepository(),
                lootRepository = LootRepository(lootGateway = LootGateway())
        )
        logger.debug("Starting usecase FixLootStatistics")
        fixLootStatistics.checkLootStatistics()

        val fixCreatures = FixCreatures(
                creatureRepository = CreatureRepository(),
                itemRepository = ItemRepository()
        )
        logger.debug("Starting usecase FixCreatures")
        fixCreatures.checkCreatures()

        val fixItems = FixItems(
                creatureRepository = CreatureRepository(),
                itemRepository = ItemRepository()
        )
        logger.debug("Starting usecase FixItems")
        fixItems.checkItems()
    }
}
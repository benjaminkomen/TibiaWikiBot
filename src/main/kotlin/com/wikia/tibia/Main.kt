package com.wikia.tibia

import com.wikia.tibia.usecases.FixCreatures
import com.wikia.tibia.usecases.FixItems
import com.wikia.tibia.usecases.FixLootStatistics
import org.slf4j.LoggerFactory

object Main {

    private val logger = LoggerFactory.getLogger(Main::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val fixLootStatistics = FixLootStatistics()
        logger.debug("Starting usecase FixLootStatistics")
        fixLootStatistics.checkLootStatistics()

        val fixCreatures = FixCreatures()
        logger.debug("Starting usecase FixCreatures")
        fixCreatures.checkCreatures()

        val fixItems = FixItems()
        logger.debug("Starting usecase FixItems")
        fixItems.checkItems()
    }
}
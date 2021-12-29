package com.wikia.tibia.v2

import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.domain.LootStatisticsService
import org.slf4j.LoggerFactory

object Main {

  private val logger = LoggerFactory.getLogger(this::class.java)

  @JvmStatic
  fun main(args: Array<String>) {
    logger.info("Starting application")
    val creatureRepository = CreatureRepositoryImpl() // define once for reuse
    val lootStatisticsService = LootStatisticsService(creatureRepository = creatureRepository)

    val creaturesToUpdate = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
  }
}

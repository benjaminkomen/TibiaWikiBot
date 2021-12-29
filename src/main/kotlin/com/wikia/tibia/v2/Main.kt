package com.wikia.tibia.v2

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.utils.pauseForABit
import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.domain.LootStatisticsService
import org.slf4j.LoggerFactory

object Main {

  private val logger = LoggerFactory.getLogger("Main")
  private const val DEBUG_MODE = true
  private val creatureRepository by lazy { CreatureRepositoryImpl() } // define once for reuse

  @JvmStatic
  fun main(args: Array<String>) {
    logger.info("Starting application")
    val lootStatisticsService = LootStatisticsService(creatureRepository = creatureRepository)

    val creaturesToUpdate = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()

    saveCreatureArticles(creaturesToUpdate)
  }

  private fun saveCreatureArticles(creatures: List<Creature>) {
    logger.info("If debug mode is disabled, ${creatures.size} creature articles are being edited NOW.")
    creatures
      .takeIf { DEBUG_MODE.not() }
      ?.forEach {
        creatureRepository.updateCreature(it, "[bot] adding missing item(s) to loot list.")
        pauseForABit()
      }
  }
}

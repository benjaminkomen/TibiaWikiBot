package com.wikia.tibia.v2

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.utils.pauseForABit
import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.adapters.item.ItemRepositoryImpl
import com.wikia.tibia.v2.domain.CreaturesService
import com.wikia.tibia.v2.domain.LootStatisticsService
import org.slf4j.LoggerFactory

object Main {

  private val logger = LoggerFactory.getLogger("Main")
  private const val DEBUG_MODE = true
  private val creatureRepository = CreatureRepositoryImpl() // define once for reuse
  private val itemRepository = ItemRepositoryImpl() // define once for reuse

  @JvmStatic
  fun main(args: Array<String>) {
    logger.info("Starting application")
    val lootStatisticsService = LootStatisticsService(creatureRepository = creatureRepository)
    val creaturesToUpdate = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()

    val creaturesService = CreaturesService(creatureRepository = creatureRepository, itemRepository = itemRepository)
    val itemsToUpdate = creaturesService.getItemsWithUpdatedLootFromCreaturesPage()

    saveCreatureArticles(creaturesToUpdate)
    saveItemArticles(itemsToUpdate)
  }

  private fun saveCreatureArticles(creatures: Map<String, Creature>) {
    logger.info("If debug mode is disabled, ${creatures.size} creature articles are being edited NOW.")
    creatures
      .takeIf { DEBUG_MODE.not() }
      ?.forEach {
        creatureRepository.updateCreature(it.value, "[bot] adding missing item(s) to loot list.")
        pauseForABit()
      }
  }

  private fun saveItemArticles(items: Map<String, TibiaObject>) {
    logger.info("If debug mode is disabled, ${items.size} item articles are being edited NOW.")
    items
      .takeIf { DEBUG_MODE.not() }
      ?.forEach {
        itemRepository.updateItem(it.value, "[bot] adding missing creature(s) to droppedBy list.")
        pauseForABit()
      }
  }
}

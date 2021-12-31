package com.wikia.tibia.v2

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.objects.plus
import com.wikia.tibia.utils.pauseForABit
import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.adapters.item.ItemRepositoryImpl
import com.wikia.tibia.v2.domain.CreaturesService
import com.wikia.tibia.v2.domain.ItemsService
import com.wikia.tibia.v2.domain.LootStatisticsService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

object Main {

  private val logger = LoggerFactory.getLogger("Main")
  private const val DEBUG_MODE = false
  private val creatureRepository = CreatureRepositoryImpl() // define once for reuse
  private val itemRepository = ItemRepositoryImpl() // define once for reuse

  @JvmStatic
  fun main(args: Array<String>) {
    runBlocking {
      logger.info("Starting application")
      coroutineScope {
        val lootStatisticsResult = async {
          val lootStatisticsService = LootStatisticsService(creatureRepository = creatureRepository)
          lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
        }

        val creaturesResult = async {
          val creaturesService = CreaturesService(creatureRepository = creatureRepository, itemRepository = itemRepository)
          creaturesService.getItemsWithUpdatedLootFromCreaturesPage()
        }

        val itemsResult = async {
          val itemsService = ItemsService(creatureRepository = creatureRepository, itemRepository = itemRepository)
          itemsService.getCreaturesWithUpdatedDroppedByFromItemPage()
        }

        saveCreatureArticles(lootStatisticsResult.await() + itemsResult.await())
        saveItemArticles(creaturesResult.await())
      }
    }
  }

  private suspend fun saveCreatureArticles(creatures: Map<String, Creature>) {
    logger.info("If debug mode is disabled, ${creatures.size} creature articles are being edited NOW.")
    creatures
      .takeIf { DEBUG_MODE.not() }
      ?.forEach {
        creatureRepository.updateCreature(it.value, "[bot] adding missing item(s) to loot list.")
        pauseForABit()
      }
  }

  private suspend fun saveItemArticles(items: Map<String, TibiaObject>) {
    logger.info("If debug mode is disabled, ${items.size} item articles are being edited NOW.")
    items
      .takeIf { DEBUG_MODE.not() }
      ?.forEach {
        itemRepository.updateItem(it.value, "[bot] adding missing creature(s) to droppedBy list.")
        pauseForABit()
      }
  }
}

private operator fun Map<String, Creature>.plus(other: Map<String, Creature>): Map<String, Creature> {
  val result = LinkedHashMap<String, Creature>(this.size + other.size)
  result.putAll(this)
  other.forEach { (creatureName, creature) ->
    val existing = result[creatureName]

    if (existing == null) {
      // map entry does not exist yet, simply add the other map entry
      result[creatureName] = creature
    } else {
      // map entry already exists, merge current and other map entry
      result[creatureName] = existing + creature
    }
  }

  return result
}

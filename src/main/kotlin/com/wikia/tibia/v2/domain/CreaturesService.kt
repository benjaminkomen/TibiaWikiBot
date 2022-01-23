package com.wikia.tibia.v2.domain

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.objects.plus
import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.adapters.item.ItemRepositoryImpl
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import com.wikia.tibia.v2.domain.item.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class CreaturesService(
  val creatureRepository: CreatureRepository = CreatureRepositoryImpl(),
  val itemRepository: ItemRepository = ItemRepositoryImpl(),
) {

  suspend fun getItemsWithUpdatedLootFromCreaturesPage(): Map<String, TibiaObject> {
    logger.info("Starting to check all creature pages for new loot information and adding to item's droppedBy lists.")

    return coroutineScope {
      val items = getCreatures()
        .asSequence()
        .sortedBy { it.name }
        .filter { it.isActive(it.status) }
        .filter { it.loot != null && it.loot.isNotEmpty() }
        .onEach { logger.debug("Processing creature: ${it.name}") }
        .flatMap { creature ->
          creature.loot
            ?.map { lootItem ->
              async {
                withContext(Dispatchers.IO) {
                  getItem(lootItem.itemName, creature.name)?.let { addCreatureToDroppedByListOfItem(creature, it) }
                }
              }
            }
            ?: emptyList()
        }
        .toList()

      mergeItems(items.awaitAll())
    }
  }

  private fun mergeItems(items: List<TibiaObject?>): Map<String, TibiaObject> {
    val result = HashMap<String, TibiaObject>()

    items.filterNotNull().forEach { item ->
      if (result.containsKey(item.name).not()) {
        // item not already in result set, add it
        result[item.name] = item
      } else {
        // item already present in result set, get the existing item and add the new droppedBy items
        result[item.name] = result[item.name] + item
      }
    }
    return result
  }

  /**
   * If the creature is not already on the droppedby list of the item (compare ignoring case!) and the item is
   * eligible for adding, add it. Also sort it.
   */
  private fun addCreatureToDroppedByListOfItem(creature: Creature, item: TibiaObject): TibiaObject? {
    return item.droppedby
      ?.takeIf { it.contains(creature.name).not() && itemShouldBeAdded(creature.name, item.name) }
      ?.let {
        logger.info("Adding creature '${creature.name}' to droppedby list of item '${item.name}'.")

        val newDroppedByList = (it + listOf(creature.name)).sorted().toMutableList()
        item.copy(droppedby = newDroppedByList)
      }
  }

  private fun itemShouldBeAdded(creaturePageName: String, lootItemNamePrecise: String): Boolean {
    return when {
      "Snowball" == lootItemNamePrecise -> {
        NON_EVENT_CREATURES_DROPPING_SNOWBALLS.contains(creaturePageName)
      }
      ITEMS_WITH_NO_DROPPEDBY_LIST.contains(lootItemNamePrecise) -> {
        false
      }
      else -> EVENT_ITEMS.contains(lootItemNamePrecise).not()
    }
  }

  private suspend fun getCreatures(): List<Creature> {
    return try {
      creatureRepository.getCreatures()
    } catch (e: Exception) {
      logger.error("Failed to get a list of creatures")
      emptyList()
    }
  }

  // TODO check WikiObject pagename or Item.Actualname, not Item.Name
  private suspend fun getItem(itemName: String, creatureName: String): TibiaObject? {
    return getItems().firstOrNull { it.name.equals(itemName, ignoreCase = true) }
      ?: run {
        logger.error("Looking at loot list of creature $creatureName, but could not find item with name '$itemName' in collection of items.")
        null
      }
  }

  private suspend fun getItems(): List<TibiaObject> {
    return try {
      itemRepository.getItems()
    } catch (e: Exception) {
      logger.error("Failed to get a list of items")
      emptyList()
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("CreaturesService")
    private val ITEMS_WITH_NO_DROPPEDBY_LIST = listOf("Gold Coin", "Platinum Coin")
    private val EVENT_ITEMS = listOf(
      "Bunch of Winterberries",
      "Envelope from the Wizards",
      "Fireworks Rocket",
      "Old Rag",
      "Party Trumpet",
      "Party Hat",
      "Silver Raid Token",
      "Golden Raid Token"
    )
    private val NON_EVENT_CREATURES_DROPPING_SNOWBALLS = listOf("Yeti", "Grynch Clan Goblin")
  }
}

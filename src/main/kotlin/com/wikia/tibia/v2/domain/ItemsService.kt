package com.wikia.tibia.v2.domain

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootItem
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

class ItemsService(
  val creatureRepository: CreatureRepository = CreatureRepositoryImpl(),
  val itemRepository: ItemRepository = ItemRepositoryImpl(),
) {

  suspend fun getCreaturesWithUpdatedDroppedByFromItemPage(): Map<String, Creature> {
    logger.info("Starting to check all item pages for new loot information and adding to creature's loot lists.")

    return coroutineScope {
      val creatures = getItems()
        .asSequence()
        .sortedBy { it.name }
        .filter { it.isActive(it.status) }
        .filter { it.droppedby?.isNotEmpty() ?: false }
        .onEach { logger.debug("Processing item: ${it.name}") }
        .flatMap { item: TibiaObject ->
          item.droppedby
            ?.map { creatureName ->
              async {
                withContext(Dispatchers.IO) {
                  getCreature(creatureName)?.let { addItemToLootTableOfCreature(item, it) }
                }
              }
            }
            ?: emptyList()
        }
        .toList()

      mergeCreatures(creatures.awaitAll())
    }
  }

  private fun addItemToLootTableOfCreature(item: TibiaObject, creature: Creature): Creature? {
    return creature.loot
      ?.takeIf { it.contains(LootItem.fromName(item.name)).not() && itemShouldBeAdded(creature.name, item.name) }
      ?.let {
        logger.info("Adding item '${item.name}' to loot table of creature '${creature.name}'.")
        creature.copy(loot = (creature.loot + listOf(LootItem.fromName(item.name))).toMutableList())
      }
  }

  /**
   * Exclude certain items/creatures.
   */
  private fun itemShouldBeAdded(creaturePageName: String, lootItemNamePrecise: String): Boolean {
    return addOrcWarlordLootIfNotOrcRaidItem(creaturePageName, lootItemNamePrecise) &&
      addOldAndUsedBackpackIfNotOrc(creaturePageName, lootItemNamePrecise) &&
      addOrcTuskIfNotOrc(creaturePageName, lootItemNamePrecise) &&
      EVENT_ITEMS.contains(lootItemNamePrecise).not()
  }

  private fun addOrcWarlordLootIfNotOrcRaidItem(creaturePageName: String, lootItemNamePrecise: String): Boolean {
    return if ("Orc Warlord" == creaturePageName) {
      ORC_RAID_ITEMS.contains(lootItemNamePrecise).not()
    } else true
  }

  /**
   * Do not add this item to the loot table of Orcs, only a specific Orc in Orc Fortress drops this item
   */
  private fun addOldAndUsedBackpackIfNotOrc(creaturePageName: String, lootItemNamePrecise: String): Boolean {
    return if ("Old and Used Backpack" == lootItemNamePrecise) {
      creaturePageName != "Orc"
    } else true
  }

  private fun addOrcTuskIfNotOrc(creaturePageName: String, lootItemNamePrecise: String): Boolean {
    return if ("Orc Tusk" == lootItemNamePrecise) {
      creaturePageName.contains("Orc").not()
    } else true
  }

  private fun mergeCreatures(creaturesToUpdate: List<Creature?>): Map<String, Creature> {
    val result = HashMap<String, Creature>()

    creaturesToUpdate.filterNotNull().forEach { creature ->
      if (result.containsKey(creature.name).not()) {
        // creature not already in creaturePages cache, add it
        result[creature.name] = creature
      } else {
        // creature already present in creaturePages cache, get the existing creature and add the new loot item
        result[creature.name] = result[creature.name] + creature
      }
    }
    return result
  }

  private suspend fun getItems(): List<TibiaObject> {
    return try {
      itemRepository.getItems()
    } catch (e: Exception) {
      logger.error("Failed to get a list of items")
      emptyList()
    }
  }

  private suspend fun getCreature(creatureName: String): Creature? {
    return getCreatures().firstOrNull { it.name.equals(creatureName, ignoreCase = true) }
  }

  private suspend fun getCreatures(): List<Creature> {
    return try {
      creatureRepository.getCreatures()
    } catch (e: Exception) {
      logger.error("Failed to get a list of creatures")
      emptyList()
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("ItemsService")
    private val ORC_RAID_ITEMS = listOf("Amazon Armor", "Amazon Helmet", "Amazon Shield")
    private val EVENT_ITEMS = listOf("Old Rag")
  }
}

package com.wikia.tibia.usecases

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.ItemRepository
import com.wikia.tibia.utils.pauseForABit
import io.vavr.control.Try
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class FixItems(
  private val creatureRepository: CreatureRepository,
  private val itemRepository: ItemRepository,
  private var items: List<TibiaObject> = ArrayList(),
  private var creatures: List<Creature> = ArrayList(),
  private val creaturePagesToUpdate: MutableMap<String, Creature> = ConcurrentHashMap() // creature name, actual creature
) {

  /**
   * 1. Get a list of Items and sort it.
   * 2. Filter out deprecated or event items.
   * 3. Filter out items with no dropped by listed.
   * 4. For every item, go over all its droppedby creatures.
   * 5. For every creature, add a backreference to the lootItem, unless already present.
   * 6. Save all the changes.
   *
   *
   * The end result is a list of Creatures with their LootTable extended.
   */
  // usage of Stream.peek for debugging purposes is justified in this case
  fun checkItems(): Map<String, Creature> {
    getItems()
      .asSequence()
      .sortedBy { it.name }
      .filter { it.isActive(it.status) }
      .filter { it.droppedby?.isNotEmpty() ?: false }
      .onEach { logger.debug("Processing item: ${it.name}") }
      .forEach { item: TibiaObject ->
        item.droppedby
          ?.mapNotNull { creatureName ->
            val creature = getCreature(creatureName)
            if (creature == null) {
              logger.error("Could not find creature with name '$creatureName' from item '${item.name}' in collection of creatures.")
            }
            creature
          }
          ?.forEach { addItemToLootTableOfCreature(item, it) }
      }
    saveCreatureArticles()
    return creaturePagesToUpdate
  }

  private fun getCreatures(): List<Creature> {
    if (creatures.isEmpty()) {
      val tryList: Try<List<Creature>> = creatureRepository.getWikiObjects()
      creatures = if (tryList.isSuccess) {
        tryList.get() as List<Creature>
      } else {
        logger.error("Failed to get a list of creatures: ${tryList.cause}")
        emptyList()
      }
    }
    return creatures
  }

  private fun getCreature(creatureName: String): Creature? {
    return getCreatures().firstOrNull { it.name == creatureName }
  }

  private fun getItems(): List<TibiaObject> {
    if (items.isEmpty()) {
      val tryList = itemRepository.getWikiObjects()
      items = if (tryList.isSuccess) {
        tryList.get() as List<TibiaObject>
      } else {
        logger.error("Failed to get a list of items: ${tryList.cause}")
        emptyList()
      }
    }
    return items
  }

  /**
   * If the item is not already in the loot table of the creature and the creature is eligible for adding, add it.
   */
  private fun addItemToLootTableOfCreature(item: TibiaObject, creature: Creature) {
    if (creature.loot != null && !creature.loot.contains(LootItem.fromName(item.name)) && itemShouldBeAdded(creature.name, item.name)) {
      logger.info("Adding item '${item.name}' to loot table of creature '${creature.name}'.")
      if (!creaturePagesToUpdate.containsKey(creature.name)) {
        // creature not already in creaturePages cache, add it
        creature.loot.add(LootItem.fromName(item.name))
        creaturePagesToUpdate[creature.name] = creature
      } else {
        // creature already present in creaturePages cache, get the existing creature and add the new lootItem
        val existingCreature = creaturePagesToUpdate[creature.name]
        existingCreature?.loot?.add(LootItem.fromName(item.name))
      }
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
      !ORC_RAID_ITEMS.contains(lootItemNamePrecise)
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
      !creaturePageName.contains("Orc")
    } else true
  }

  private fun saveCreatureArticles() {
    logger.info("If debug mode is disabled, ${creaturePagesToUpdate.size} creature articles are being edited NOW.")
    creaturePagesToUpdate.forEach { (_: String?, value: Creature?) ->
      creatureRepository.saveWikiObject(value, "[bot] adding missing item(s) to loot list.", DEBUG_MODE)
      pauseForABit()
    }
  }

  companion object {
    private const val DEBUG_MODE = false
    private val ORC_RAID_ITEMS = listOf("Amazon Armor", "Amazon Helmet", "Amazon Shield")
    private val EVENT_ITEMS = listOf(
      "Old Rag",
    )
    private val logger = LoggerFactory.getLogger(FixItems::class.java)
  }
}

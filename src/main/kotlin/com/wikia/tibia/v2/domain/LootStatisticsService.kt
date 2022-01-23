package com.wikia.tibia.v2.domain

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.objects.plus
import com.wikia.tibia.v2.adapters.creature.CreatureRepositoryImpl
import com.wikia.tibia.v2.adapters.loot.LootRepositoryImpl
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import com.wikia.tibia.v2.domain.loot.LootRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class LootStatisticsService(
  val creatureRepository: CreatureRepository = CreatureRepositoryImpl(),
  private val lootRepository: LootRepository = LootRepositoryImpl(),
) {

  suspend fun getCreaturesWithUpdatedLootFromLootStatisticsPage(): Map<String, Creature> {
    logger.info("Starting to check all loot statistics pages for new loot information and adding to creature's loot lists.")

    return coroutineScope {
      val creatures = getLootList()
        .asSequence()
        .mapNotNull { it.getMergedLoot() }
        .filter { !it.isEmpty() }
        .sortedBy { it.name }
        .onEach { logger.debug("Processing loot statistics page of creature: ${it.name}") }
        .map { loot ->
          async {
            withContext(Dispatchers.IO) {
              getCreature(loot.name)
                ?.let { creature ->
                  loot.loot?.mapNotNull { addLootItemsToLootList(creature, it) }
                }
                ?: run {
                  logger.error("Could not find creature with pageName '${loot.pageName}' and name '${loot.name}' in collection of creatures.")
                  emptyList()
                }
            }
          }
        }
        .toList()

      mergeCreatures(creatures.awaitAll().flatten())
    }
  }

  private fun mergeCreatures(creaturesToUpdate: List<Creature>): Map<String, Creature> {
    val result = HashMap<String, Creature>()

    creaturesToUpdate.forEach { creature ->
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

  private fun addLootItemsToLootList(creature: Creature, lootStatisticsItem: LootStatisticsItem): Creature? {
    return creature.loot
      ?.any { it.itemName == replaceSomeNames(lootStatisticsItem.itemName) }
      ?.takeIf { lootStatisticsItemExistsInCreatureLootList -> lootStatisticsItemExistsInCreatureLootList.not() && shouldAddLootItemToCreature(lootStatisticsItem, creature) }
      ?.let {
        logger.info("Adding item '${lootStatisticsItem.itemName}' to loot list of creature '${creature.name}'.")
        val newLootItem = LootItem(
          itemName = lootStatisticsItem.itemName,
          amount = null,
          rarity = null,
        )
        creature.copy(loot = (creature.loot + listOf(newLootItem)).toMutableList())
      }
  }

  /**
   * Some items are actually named differently on the wiki, because of page names conflicts
   */
  private fun replaceSomeNames(itemName: String): String {
    return when {
      DIFFERENTLY_NAMED_ITEMS.containsKey(itemName) -> DIFFERENTLY_NAMED_ITEMS[itemName] ?: ""
      else -> itemName
    }
  }

  /**
   * There are a few reasons when NOT to add a loot item to a creature:
   * - the loot statistics item name is e.g. "Empty", that is not relevant on the creature's loot page
   * - the loot statistics data is incorrect, e.g. with Demon / Demon (Goblin)
   */
  private fun shouldAddLootItemToCreature(
    lootStatisticsItem: LootStatisticsItem,
    correspondingCreature: Creature
  ): Boolean {
    return lootStatisticsTableEntriesNotToBeUsedOnCreaturePage.contains(lootStatisticsItem.itemName)
      .not() && forbiddenCombinationOfLootAndCreature(lootStatisticsItem, correspondingCreature).not()
  }

  /**
   * Example: https://tibia.fandom.com/wiki/Loot_Statistics:Demon contains certain loot items
   * from https://tibia.fandom.com/wiki/Demon_(Goblin).
   * These loot items should not be added to the loot list of Demons, because they are wrong.
   */
  private fun forbiddenCombinationOfLootAndCreature(
    lootStatisticsItem: LootStatisticsItem,
    correspondingCreature: Creature
  ): Boolean {
    return forbiddenCreaturesAndLoot.entries
      .any {
        it.key == correspondingCreature.name && it.value.contains(lootStatisticsItem.itemName)
      }
  }

  private suspend fun getLootList(): List<LootWrapper> {
    return try {
      lootRepository.getLootList()
    } catch (e: Exception) {
      logger.error("Failed to get a list of lootPages")
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
    private val logger = LoggerFactory.getLogger("LootStatisticsService")
    private val DIFFERENTLY_NAMED_ITEMS = mapOf(
      "Skull" to "Skull (Item)",
      "Black Skull" to "Black Skull (Item)",
      "Dead Snake" to "Dead Snake (Item)",
      "Dead Frog" to "Dead Frog (Item)",
      "Clusters of Solace" to "Cluster of Solace",
      "Bag With Stolen Gold" to "Bag with Stolen Gold",
    )
    private val forbiddenCreaturesAndLoot = mapOf(
      "Demon" to listOf("Small Stone", "Bone", "Leather Armor", "Mouldy Cheese"),
      "Mountain Troll" to listOf("Bunch of Troll Hair"),
      "Minotaur Bruiser" to listOf("Minotaur Horn", "Minotaur Leather"),
      "Muglex Clan Footman" to listOf("Goblin Ear"),
      "Woodling" to listOf("Piece of Swampling Wood", "Swampling Moss"),
      "Meadow Strider" to listOf("Seeds", "Marsh Stalker Beak", "Marsh Stalker Feather"),
      "Dawnfly" to listOf("Damselfly Eye", "Damselfly Wing"),
      "Scar Tribe Shaman" to listOf("Shamanic Hood", "Orc Tooth", "Broken Shamanic Staff"),
      "Scar Tribe Warrior" to listOf("Skull Belt", "Orc Leather"),
      "Brittle Skeleton" to listOf("Pelvis Bone"),
      "Minotaur Poacher" to listOf("Broken Crossbow", "Minotaur Leather", "Minotaur Horn"),
      "Juvenile Cyclops" to listOf("Cyclops Toe"),
      "Minotaur Occultist" to listOf("Minotaur Horn", "Purple Robe"),
      "Lesser Fire Devil" to listOf("Small Pitchfork"),
      "Troll Marauder" to listOf("Bunch of Troll Hair", "Trollroot"),
      "The Percht Queen" to listOf("The Crown of the Percht Queen", "Giant Shimmering Pearl"),
      "Lady Tenebris" to listOf("Part of a Rune"),
      "Melting Frozen Horror" to listOf("Part of a Rune"),
      "Soul of Dragonking Zyrtarch" to listOf("Part of a Rune"),
      "The Time Guardian" to listOf("Part of a Rune"),
      "The Blazing Time Guardian" to listOf("Part of a Rune"),
      "The Freezing Time Guardian" to listOf("Part of a Rune"),
    )
    private val lootStatisticsTableEntriesNotToBeUsedOnCreaturePage = listOf(
      "Empty",
      "Music Sheet",
      "Old Rag",
      "Part of a Jester Doll",
      "Secret Instruction",
    )
  }
}

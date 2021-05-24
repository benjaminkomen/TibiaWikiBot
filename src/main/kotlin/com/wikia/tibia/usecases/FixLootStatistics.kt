package com.wikia.tibia.usecases

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.LootRepository
import com.wikia.tibia.utils.pauseForABit
import io.vavr.control.Try
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

class FixLootStatistics(
    private val creatureRepository: CreatureRepository,
    private val lootRepository: LootRepository,
    private var loot: List<LootWrapper> = ArrayList(),
    private var creatures: List<Creature> = ArrayList(),
    private val creaturePagesToUpdate: MutableMap<String, Creature> = ConcurrentHashMap() // creature name, actual creature
) {

    /**
     * Fix creature pages by adding loot from their loot statistics pages.
     * 1. Get a list of Loot Statistics pages and sort it.
     * 2. Filter out loot statistics pages with no loot.
     * 2. For every loot statistics page, go over all its loot items and add them to the corresponding creature pages' loot list, if not already present.
     */
    fun checkLootStatistics(): Map<String, Creature> {
        lootPages
            .asSequence()
            .mapNotNull { it.getMergedLoot() }
            .filter { !it.isEmpty() }
            .sortedBy { it.name }
            .onEach { logger.debug("Processing loot statistics page of creature: ${it.name}") }
            .forEach { (pageName, _, _, name, loot1) ->
                val correspondingCreature = getCreature(name)
                if (correspondingCreature == null) {
                    logger.error("Could not find creature with pageName '$pageName' and name '$name' in collection of creatures.")
                }
                if (correspondingCreature != null) {
                    loot1?.forEach { addLootItemsToLootList(correspondingCreature, it) }
                }
            }
        saveCreatureArticles()
        return creaturePagesToUpdate
    }

    private fun addLootItemsToLootList(correspondingCreature: Creature, lootStatisticsItem: LootStatisticsItem) {
        val lootStatisticsItemExistsInCreatureLootList = correspondingCreature.loot
            ?.any { lootItem -> lootItem.itemName == replaceSomeNames(lootStatisticsItem.itemName) }
            ?: false

        // loot item exists on loot statistics page, but not on creature page. Add it if applicable.
        if (!lootStatisticsItemExistsInCreatureLootList && shouldAddLootItemToCreature(
                lootStatisticsItem,
                correspondingCreature
            )
        ) {
            logger.info("Adding item '${lootStatisticsItem.itemName}' to loot list of creature '${correspondingCreature.name}'.")
            val newLootItem = LootItem(
                itemName = lootStatisticsItem.itemName,
                amount = null,
                rarity = null
            )
            if (!creaturePagesToUpdate.containsKey(correspondingCreature.name)) {
                // creature not already in creaturePages cache, add it
                correspondingCreature.loot?.add(newLootItem)
                creaturePagesToUpdate[correspondingCreature.name] = correspondingCreature
            } else {
                // creature already present in creaturePages cache, get the existing creature and add the new loot item
                val existingCreature = creaturePagesToUpdate[correspondingCreature.name]
                existingCreature?.loot?.add(newLootItem)
            }
        }
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
        return getCreatures().firstOrNull { it.name.equals(creatureName, ignoreCase = true) }
    }

    private val lootPages: List<LootWrapper>
        get() {
            if (loot.isEmpty()) {
                val tryList = lootRepository.getLoot()
                loot = if (tryList.isSuccess) {
                    tryList.get() as List<LootWrapper>
                } else {
                    logger.error("Failed to get a list of lootPages: ${tryList.cause}")
                    emptyList()
                }
            }
            return loot
        }

    private fun saveCreatureArticles() {
        logger.info("If debug mode is disabled, ${creaturePagesToUpdate.size} creature articles are being edited NOW.")
        creaturePagesToUpdate.forEach { (_: String?, value: Creature?) ->
            creatureRepository.saveWikiObject(value, "[bot] adding missing item(s) to loot list.", DEBUG_MODE)
            pauseForABit()
        }
    }

    /**
     * Some items are actually named differently on the wiki, because of page names conflicts
     */
    private fun replaceSomeNames(itemName: String): String {
        return if (DIFFERENTLY_NAMED_ITEMS.containsKey(itemName)) {
            DIFFERENTLY_NAMED_ITEMS[itemName] ?: ""
        } else itemName
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

    companion object {
        private val logger = LoggerFactory.getLogger(FixLootStatistics::class.java)
        private const val DEBUG_MODE = false
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
        )
        private val lootStatisticsTableEntriesNotToBeUsedOnCreaturePage = listOf(
            "Empty",
            "Old Rag",
            "Part of a Jester Doll",
            "Secret Instruction",
        )
    }
}

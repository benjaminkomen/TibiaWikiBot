package com.wikia.tibia.usecases

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Item
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.ItemRepository
import com.wikia.tibia.utils.pauseForABit
import io.vavr.control.Try
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class FixCreatures(
        private val creatureRepository: CreatureRepository,
        private val itemRepository: ItemRepository,
        private val itemPagesToUpdate: MutableMap<String, Item> = ConcurrentHashMap(), // item name, actual item (for easy lookup)
        private var items: List<Item> = ArrayList(),
        private var creatures: List<Creature> = ArrayList()
) {

    /**
     * 1. Get a list of Creatures and sort it.
     * 2. Filter out deprecated or event creatures.
     * 3. Filter out creatures with no loot listed.
     * 4. For every creature, go over all its loot items.
     * 5. For every loot item, add a backreference to the creature, unless already present.
     * 6. Save all the changes.
     *
     *
     * The end result is a list of Items with their droppedby list extended.
     */
    fun checkCreatures(): Map<String, Item> {
        getCreatures()
                .asSequence()
                .sortedBy { it.name }
                .filter { it.notDeprecatedOrEvent(it.status) }
                .filter { it.loot != null && it.loot.isNotEmpty() }
                .onEach { logger.debug("Processing creature: ${it.name}") }
                .forEach { creature: Creature ->
                    creature.loot
                            ?.mapNotNull{
                                val item = getItem(it.itemName)
                                if (item == null) {
                                    logger.error("Could not find loot item with name '${it.itemName}' from creature '${creature.name}' in collection of items.")
                                }
                                item
                            }
                            ?.forEach { addCreatureToDroppedByListOfItem(creature, it) }
                }
        saveItemArticles()
        return itemPagesToUpdate
    }

    private fun getCreatures(): List<Creature> {
        if (creatures.isEmpty()) {
            val tryList = creatureRepository.wikiObjects
            creatures = if (tryList.isSuccess) {
                tryList.get() as List<Creature>
            } else {
                logger.error("Failed to get a list of creatures: ${tryList.cause}")
                emptyList()
            }
        }
        return creatures
    }

    private fun getItems(): List<Item> {
        if (items.isEmpty()) {
            val tryList: Try<List<Item>> = itemRepository.wikiObjects
            items = if (tryList.isSuccess) {
                tryList.get() as List<Item>
            } else {
                logger.error("Failed to get a list of items: ${tryList.cause}")
                emptyList()
            }
        }
        return items
    }

    // TODO check WikiObject pagename or Item.Actualname, not Item.Name
    private fun getItem(itemName: String): Item? {
        return getItems().firstOrNull { i: Item -> i.name == itemName }
    }

    private fun itemShouldBeAdded(creaturePageName: String, lootItemNamePrecise: String): Boolean {
        if ("Snowball" == lootItemNamePrecise) {
            return NON_EVENT_CREATURES_DROPPING_SNOWBALLS.contains(creaturePageName)
        }
        if (ITEMS_WITH_NO_DROPPEDBY_LIST.contains(lootItemNamePrecise)) {
            return false
        }
        return !EVENT_ITEMS.contains(lootItemNamePrecise)
    }

    /**
     * If the creature is not already on the droppedby list of the item (compare ignoring case!) and the item is
     * eligible for adding, add it. Also sort it.
     */
    private fun addCreatureToDroppedByListOfItem(creature: Creature, item: Item) {
        if (item.droppedby != null && !item.droppedby.contains(creature.name) && itemShouldBeAdded(creature.name, item.name)) {
            logger.info("Adding creature '${creature.name}' to droppedby list of item '${item.name}'.")
            if (!itemPagesToUpdate.containsKey(item.name)) {
                // item not already in itemPages cache, add it
                item.droppedby.add(creature.name)
                item.droppedby.sort()
                itemPagesToUpdate[item.name] = item
            } else {
                // item already present in itemPages cache, get the existing item and add the new creature name
                val existingItem = itemPagesToUpdate[item.name]
                existingItem?.droppedby?.add(creature.name)
                existingItem?.droppedby?.sort()
            }
        }
    }

    private fun saveItemArticles() {
        logger.info("If debug mode is disabled, {} item articles are being edited NOW.", itemPagesToUpdate.size)
        itemPagesToUpdate.forEach { (_: String?, value: Item?) ->
            itemRepository.saveWikiObject(value, "[bot] adding missing creature(s) to droppedby list.", DEBUG_MODE)
            pauseForABit()
        }
    }

    companion object {
        private val ITEMS_WITH_NO_DROPPEDBY_LIST = listOf("Gold Coin", "Platinum Coin")
        private val EVENT_ITEMS = listOf(
                "Bunch of Winterberries",
                "Envelope from the Wizards",
                "Fireworks Rocket",
                "Party Trumpet",
                "Party Hat",
                "Silver Raid Token",
                "Golden Raid Token"
        )
        private val NON_EVENT_CREATURES_DROPPING_SNOWBALLS = listOf("Yeti", "Grynch Clan Goblin")
        private const val DEBUG_MODE = false
        private val logger = LoggerFactory.getLogger(FixCreatures::class.java)
    }
}
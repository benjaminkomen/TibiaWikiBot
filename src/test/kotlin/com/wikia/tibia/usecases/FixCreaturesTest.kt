package com.wikia.tibia.usecases

import com.wikia.tibia.enums.ItemClass
import com.wikia.tibia.enums.Rarity
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Item
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.WikiObject
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.ItemRepository
import io.vavr.control.Try
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import testutils.any

internal class FixCreaturesTest {
    private lateinit var target: FixCreatures
    private val creatureRat = makeRat()
    private val creatureBear = makeBear()
    private val creatureWasp = makeWasp()
    private val creatureCyclops = makeCyclops()
    private val itemCheese = makeCheese()
    private val itemHoneycomb = makeHoneycomb()
    private val itemOldRag = makeOldRag()
    private val itemMeat = makeMeat()
    private lateinit var mockCreatureRepository: CreatureRepository
    private lateinit var mockItemRepository: ItemRepository

    @BeforeEach
    fun setup() {
        mockCreatureRepository = mock(CreatureRepository::class.java)
        mockItemRepository = mock(ItemRepository::class.java)
        target = FixCreatures(mockCreatureRepository, mockItemRepository)
    }

    @Test
    fun `should fix creatures - do nothing`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureRat)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(itemCheese)))

        // when
        val result = target.checkCreatures()

        // then
        assertEquals(0, result.size)
    }

    @Test
    fun `should fix creatures - add bear to droppedby of honeycomb`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureBear)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(itemHoneycomb)))
        `when`(mockItemRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkCreatures()

        // then
        assertEquals(1, result.size)
        assertTrue(result.containsKey("Honeycomb"))
        assertEquals(5, (result["Honeycomb"] ?: error("")).droppedby?.size)
        assertTrue((result["Honeycomb"] ?: error("")).droppedby?.contains("Bear") ?: false)
        assertFalse((result["Honeycomb"] ?: error("")).droppedby?.contains("Wasp") ?: false)
    }

    @Test
    fun `should fix creatures - add bear and wasp to droppedby of honeycomb`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureBear, creatureWasp)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(itemHoneycomb)))
        `when`(mockItemRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkCreatures()

        // then
        assertEquals(1, result.size)
        assertTrue(result.containsKey("Honeycomb"))
        assertEquals(6, (result["Honeycomb"] ?: error("")).droppedby?.size)
        assertTrue((result["Honeycomb"] ?: error("")).droppedby?.contains("Bear") ?: false)
        assertTrue((result["Honeycomb"] ?: error("")).droppedby?.contains("Wasp") ?: false)
    }

    @Test
    fun `should fix creatures - not add old rag to loot list of cyclops`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureCyclops)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(itemOldRag, itemMeat)))
        `when`(mockItemRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkCreatures()

        // then
        assertEquals(1, result.size)
        assertTrue(result.containsKey("Meat"))
        assertFalse(result.containsKey("Old Rag"))
    }

    private fun makeRat(): Creature {
        return Creature(
            actualname = "Rat",
            name = "Rat",
            loot = mutableListOf(
                LootItem(itemName = "Gold Coin", amount = "0-4"),
                LootItem(itemName = "Cheese")
            )
        )
    }

    private fun makeBear(): Creature {
        return Creature(
            actualname = "Bear",
            name = "Bear",
            loot = mutableListOf(
                LootItem(itemName = "Meat", amount = "0-4"),
                LootItem(itemName = "Ham", amount = "0-3"),
                LootItem(itemName = "Bear Paw", rarity = Rarity.SEMI_RARE),
                LootItem(itemName = "Honeycomb", rarity = Rarity.RARE)
            )
        )
    }

    private fun makeWasp(): Creature {
        return Creature(
            actualname = "Wasp",
            name = "Wasp",
            loot = mutableListOf(
                LootItem(itemName = "Honeycomb", rarity = Rarity.SEMI_RARE)
            )
        )
    }

    private fun makeCyclops(): Creature {
        return Creature(
            actualname = "Cyclops",
            name = "Cyclops",
            loot = mutableListOf(
                LootItem(itemName = "Meat")
            )
        )
    }

    private fun makeCheese(): Item {
        return Item(
            actualname = "Cheese",
            name = "Cheese",
            itemclass = ItemClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK,
            droppedby = mutableListOf("Cave Rat", "Corym Charlatan", "Green Djinn", "Mutated Human", "Rat")
        )
    }

    private fun makeHoneycomb(): Item {
        return Item(
            actualname = "Honeycomb",
            name = "Honeycomb",
            itemclass = ItemClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK,
            droppedby = mutableListOf(
                "Grynch Clan Goblin",
                "Shadowpelt",
                "Werebear",
                "Willi Wasp"
            ) // Bear and Wasp are purposely missing
        )
    }

    private fun makeOldRag(): Item {
        return Item(
            actualname = "Old Rag",
            name = "Old Rag",
            itemclass = ItemClass.OTHER_ITEMS,
            droppedby = mutableListOf(
                "Cyclops",
                "Troll",
                "Wolf",
            )
        )
    }

    private fun makeMeat(): Item {
        return Item(
            actualname = "Meat",
            name = "Meat",
            itemclass = ItemClass.OTHER_ITEMS,
            droppedby = mutableListOf()
        )
    }
}

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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import testutils.any

class FixCreaturesTest {
    private lateinit var target: FixCreatures
    private val Rat = makeRat()
    private val Bear = makeBear()
    private val Wasp = makeWasp()
    private val Cheese = makeCheese()
    private val Honeycomb = makeHoneycomb()
    private lateinit var mockCreatureRepository: CreatureRepository
    private lateinit var mockItemRepository: ItemRepository

    @Before
    fun setup() {
        mockCreatureRepository = mock(CreatureRepository::class.java)
        mockItemRepository = mock(ItemRepository::class.java)
        target = FixCreatures(mockCreatureRepository, mockItemRepository)
    }

    @Test
    fun `should fix creatures - do nothing`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Rat)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Cheese)))

        // when
        val result = target.checkCreatures()

        // then
        assertThat(result.size, `is`(0))
    }

    @Test
    fun `should fix creatures - add bear to droppedby of honeycomb`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Bear)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Honeycomb)))
        `when`(mockItemRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkCreatures()

        // then
        assertThat(result.size, `is`(1))
        assertThat(result.containsKey("Honeycomb"), `is`(true))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.size, `is`(5))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.contains("Bear"), `is`(true))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.contains("Wasp"), `is`(false))
    }

    @Test
    fun `should fix creatures - add bear and wasp to droppedby of honeycomb`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Bear, Wasp)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Honeycomb)))
        `when`(mockItemRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkCreatures()

        // then
        assertThat(result.size, `is`(1))
        assertThat(result.containsKey("Honeycomb"), `is`(true))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.size, `is`(6))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.contains("Bear"), `is`(true))
        assertThat((result["Honeycomb"] ?: error("")).droppedby?.contains("Wasp"), `is`(true))
    }

    companion object {
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
                droppedby = mutableListOf("Grynch Clan Goblin", "Shadowpelt", "Werebear", "Willi Wasp") // Bear and Wasp are purposely missing
            )
        }
    }
}

package com.wikia.tibia.usecases

import com.wikia.tibia.enums.ObjectClass
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

class FixItemsTest {
    private lateinit var target: FixItems
    private val Rat = makeRat()
    private val Bear = makeBear()
    private val Wasp = makeWasp()
    private val Cheese = makeCheese()
    private val Honeycomb = makeHoneycomb()
    private val BearPaw = makeBearPaw()
    private lateinit var mockCreatureRepository: CreatureRepository
    private lateinit var mockItemRepository: ItemRepository

    @Before
    fun setup() {
        mockCreatureRepository = mock(CreatureRepository::class.java)
        mockItemRepository = mock(ItemRepository::class.java)
        target = FixItems(mockCreatureRepository, mockItemRepository)
    }

    @Test
    fun `should fix items - do nothing`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Rat)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Cheese)))

        // when
        val result = target.checkItems()

        // then
        assertThat(result.size, `is`(0))
    }

    @Test
    fun `should fix items - add honeycomb to loot of bear`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Bear)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Honeycomb)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkItems()

        // then
        assertThat(result.size, `is`(1))
        assertThat(result.containsKey("Bear"), `is`(true))
        assertThat((result["Bear"] ?: error("")).loot?.size, `is`(3))
        assertThat((result["Bear"] ?: error("")).loot?.contains(LootItem(itemName = "Honeycomb")), `is`(true))
    }

    @Test
    fun `should fix items - add honeycomb to loot of bear and wasp`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Bear, Wasp)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Honeycomb)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkItems()

        // then
        assertThat(result.size, `is`(2))
        assertThat(result.containsKey("Bear"), `is`(true))
        assertThat(result.containsKey("Wasp"), `is`(true))
        assertThat((result["Bear"] ?: error("")).loot?.size, `is`(3))
        assertThat((result["Bear"] ?: error("")).loot?.contains(LootItem(itemName = "Honeycomb")), `is`(true))
        assertThat((result["Wasp"] ?: error("")).loot?.size, `is`(1))
        assertThat((result["Wasp"] ?: error("")).loot?.contains(LootItem(itemName = "Honeycomb")), `is`(true))
    }

    @Test
    fun `should fix items - add honeycomb and bear paw to loot of bear`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(Bear)))
        `when`(mockItemRepository.getWikiObjects()).thenReturn(Try.success(listOf(Honeycomb, BearPaw)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkItems()

        // then
        assertThat(result.size, `is`(1))
        assertThat(result.containsKey("Bear"), `is`(true))
        assertThat((result["Bear"] ?: error("")).loot?.size, `is`(4))
        assertThat((result["Bear"] ?: error("")).loot?.contains(LootItem(itemName = "Honeycomb")), `is`(true))
        assertThat((result["Bear"] ?: error("")).loot?.contains(LootItem(itemName = "Bear Paw")), `is`(true))
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
                    LootItem(itemName = "Ham", amount = "0-3")
                )
            )
        }

        private fun makeWasp(): Creature {
            return Creature(
                actualname = "Wasp",
                name = "Wasp",
                loot = mutableListOf()
            )
        }

        private fun makeCheese(): Item {
            return Item(
                actualname = "Cheese",
                name = "Cheese",
                objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK,
                droppedby = mutableListOf("Cave Rat", "Corym Charlatan", "Green Djinn", "Mutated Human", "Rat")
            )
        }

        private fun makeHoneycomb(): Item {
            return Item(
                actualname = "Honeycomb",
                name = "Honeycomb",
                objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK,
                droppedby = mutableListOf("Bear", "Grynch Clan Goblin", "Shadowpelt", "Wasp", "Werebear", "Willi Wasp")
            )
        }

        private fun makeBearPaw(): Item {
            return Item(
                actualname = "Bear Paw",
                name = "Bear Paw",
                objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK,
                droppedby = mutableListOf("Bear", "Shadowpelt", "Werebear")
            )
        }
    }
}

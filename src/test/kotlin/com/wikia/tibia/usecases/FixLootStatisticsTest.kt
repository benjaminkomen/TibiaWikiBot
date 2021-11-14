package com.wikia.tibia.usecases

import com.wikia.tibia.enums.Rarity
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Loot
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.objects.WikiObject
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.LootRepository
import io.vavr.control.Try
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import testutils.any

internal class FixLootStatisticsTest {
    private lateinit var target: FixLootStatistics
    private lateinit var mockCreatureRepository: CreatureRepository
    private lateinit var mockLootRepository: LootRepository
    private val creatureRat = makeCreatureRat()
    private val creatureAmazon = makeCreatureAmazon()
    private val creatureDemon = makeCreatureDemon()
    private val creaturePriestessOfTheWildSun = makeCreaturePriestessOfTheWildSun()
    private val lootRat = makeLootRat()
    private val lootAmazon = makeLootAmazon()
    private val lootRatWithSword = makeLootRatWithSword()
    private val lootDemon = makeLootDemon()
    private val lootPriestessOfTheWildSun = makeLootPriestessOfTheWildSun()

    @BeforeEach
    fun setup() {
        mockCreatureRepository = mock(CreatureRepository::class.java)
        mockLootRepository = mock(LootRepository::class.java)
        target = FixLootStatistics(mockCreatureRepository, mockLootRepository)
    }

    /**
     * Do nothing because the loot list of Rat contains exactly the same as the loot statistics list of Rat.
     */
    @Test
    fun `should fix loot statistics - do nothing`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureRat)))
        `when`(mockLootRepository.getLoot()).thenReturn(Try.success(listOf(lootRat)))

        // when
        val result = target.checkLootStatistics()

        // then
        assertEquals(0, result.size)
    }

    /**
     * The loot list of a Rat contains some items. Its loot statistics page contains those items, and additionally a Sword.
     * This should be added.
     */
    @Test
    fun `should fix loot statistics - add sword to droppedby list of rat`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureRat)))
        `when`(mockLootRepository.getLoot()).thenReturn(Try.success(listOf(lootRatWithSword)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkLootStatistics()

        // then
        assertEquals(1, result.size)
        assertTrue(result.containsKey("Rat"))
        assertEquals(3, (result["Rat"] ?: error("")).loot?.size)
        assertTrue((result["Rat"] ?: error("")).loot?.any { (itemName) -> "Sword" == itemName } ?: false)
    }

    /**
     * The loot list of a Rat contains some items. Its loot statistics page contains those items, and additionally a Sword.
     * This should be added.
     */
    @Test
    fun `should fix loot statistics - do not add skull to loot list of amazon`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureAmazon)))
        `when`(mockLootRepository.getLoot()).thenReturn(Try.success(listOf(lootAmazon)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkLootStatistics()

        // then
        assertEquals(0, result.size)
    }

    /**
     * The loot list of a Demon contains some Demon (Goblin) items. These should not be added to a Demon's loot list.
     */
    @Test
    fun `should fix loot statistics - do not add demon goblin loot to demon`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creatureDemon)))
        `when`(mockLootRepository.getLoot()).thenReturn(Try.success(listOf(lootDemon)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkLootStatistics()

        // then
        assertEquals(1, result.size)
        assertEquals("Demonrage Sword", (result["Demon"] ?: error("")).loot!![1].itemName)
    }

    /**
     * The loot list of a Priestess of the Wild Sun contains the item Secret Instruction.
     * But it should not be added to the creatures' loot list.
     */
    @Test
    fun `should fix loot statistics - do not add Secret Instruction item to any creature page`() {
        // given
        `when`(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(listOf(creaturePriestessOfTheWildSun)))
        `when`(mockLootRepository.getLoot()).thenReturn(Try.success(listOf(lootPriestessOfTheWildSun)))
        `when`(mockCreatureRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean()))
            .thenReturn(Try.success("success"))

        // when
        val result = target.checkLootStatistics()

        // then
        val lootList = result["Priestess of the Wild Sun"]?.loot!!
        assertEquals(1, result.size)
        assertTrue(lootList.contains(LootItem("Secret Instruction (Silver)")))
        assertFalse(lootList.contains(LootItem("Secret Instruction")))
    }

    private fun makeCreatureAmazon(): Creature {
        return Creature(
            actualname = "Amazon",
            name = "Amazon",
            loot = mutableListOf(
                LootItem(itemName = "Gold Coin", amount = "0-20"),
                LootItem(itemName = "Skull (Item)", amount = "0-2"),
                LootItem(itemName = "Dagger"),
                LootItem(itemName = "Brown Bread"),
                LootItem(itemName = "Sabre"),
                LootItem(itemName = "Girlish Hair Decoration"),
                LootItem(itemName = "Protective Charm"),
                LootItem(itemName = "Torch", rarity = Rarity.RARE),
                LootItem(itemName = "Crystal Necklace", rarity = Rarity.VERY_RARE),
                LootItem(itemName = "Small Ruby", rarity = Rarity.VERY_RARE)
            )
        )
    }

    private fun makeCreatureDemon(): Creature {
        return Creature(
            actualname = "Demon",
            name = "Demon",
            loot = mutableListOf(LootItem(itemName = "Magic Plate Armor"))
        )
    }

    private fun makeCreaturePriestessOfTheWildSun(): Creature {
        return Creature(
            actualname = "Priestess of the Wild Sun",
            name = "Priestess of the Wild Sun",
            loot = mutableListOf(
                LootItem(itemName = "Secret Instruction (Gryphon)"),
                LootItem(itemName = "Secret Instruction (Mirror)"),
                LootItem(itemName = "Secret Instruction (Silver)"),
            )
        )
    }

    private fun makeLootAmazon(): LootWrapper {
        return LootWrapper(
            loot2 = Loot(
                kills = "21983",
                pageName = "Amazon",
                name = "Amazon",
                loot = mutableListOf(
                    LootStatisticsItem(itemName = "Empty", times = "253"),
                    LootStatisticsItem(itemName = "Dagger", times = "17606", amount = "1", total = "17606"),
                    LootStatisticsItem(itemName = "Skull", times = "17581", amount = "1-2", total = "26316"),
                    LootStatisticsItem(itemName = "Gold Coin", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Brown Bread", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Sabre", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Girlish Hair Decoration", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Protective Charm", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Torch", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Crystal Necklace", times = "1", amount = "1", total = "2"),
                    LootStatisticsItem(itemName = "Small Ruby", times = "1", amount = "1", total = "1")
                ),
                version = "8.6"
            )
        )
    }

    private fun makeLootDemon(): LootWrapper {
        return LootWrapper(
            loot2 = Loot(
                kills = "34026",
                pageName = "Demon",
                name = "Demon",
                loot = mutableListOf(
                    LootStatisticsItem(itemName = "Small Stone", times = "21", amount = "1-3", total = "38"),
                    LootStatisticsItem(itemName = "Mouldy Cheese", times = "10", amount = "1", total = "10"),
                    LootStatisticsItem(itemName = "Leather Armor", times = "8", amount = "1", total = "8"),
                    LootStatisticsItem(itemName = "Bone", times = "7", amount = "1", total = "7"),
                    LootStatisticsItem(itemName = "Demonrage Sword", times = "20", amount = "1", total = "20")
                ),
                version = "10.37"
            )
        )
    }

    private fun makeLootPriestessOfTheWildSun(): LootWrapper {
        return LootWrapper(
            loot2 = Loot(
                kills = "2073",
                pageName = "Priestess of the Wild Sun",
                name = "Priestess of the Wild Sun",
                loot = mutableListOf(
                    LootStatisticsItem(itemName = "Platinum Coin", times = "2073", amount = "1-3", total = "4185"),
                    LootStatisticsItem(itemName = "Fafnar Symbol", times = "185", amount = "1", total = "185"),
                    LootStatisticsItem(itemName = "Secret Instruction", times = "144", amount = "1", total = "144"),
                ),
                version = "12.31.9580"
            )
        )
    }

    private fun makeCreatureRat(): Creature {
        return Creature(
            actualname = "Rat",
            name = "Rat",
            loot = mutableListOf(
                LootItem(itemName = "Gold Coin", amount = "0-4"),
                LootItem(itemName = "Cheese")
            )
        )
    }

    private fun makeLootRat(): LootWrapper {
        return LootWrapper(
            loot2 = Loot(
                kills = "14605",
                pageName = "Rat",
                name = "Rat",
                loot = mutableListOf(
                    LootStatisticsItem(itemName = "Empty", times = "108"),
                    LootStatisticsItem(itemName = "Cheese", times = "5741"),
                    LootStatisticsItem(itemName = "Gold Coin", times = "14477", amount = "1", total = "20591")
                ),
                version = "9.63"
            )
        )
    }

    private fun makeLootRatWithSword(): LootWrapper {
        return LootWrapper(
            loot2 = Loot(
                kills = "14605",
                pageName = "Rat",
                name = "Rat",
                loot = mutableListOf(
                    LootStatisticsItem(itemName = "Empty", times = "108"),
                    LootStatisticsItem(itemName = "Cheese", times = "5741"),
                    LootStatisticsItem(itemName = "Gold Coin", times = "14477", amount = "1", total = "20591"),
                    LootStatisticsItem(itemName = "Sword", times = "1")
                ),
                version = "9.63"
            )
        )
    }
}

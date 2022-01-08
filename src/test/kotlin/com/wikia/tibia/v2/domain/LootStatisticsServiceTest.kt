package com.wikia.tibia.v2.domain

import com.wikia.tibia.enums.Rarity
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Loot
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.shouldBe
import com.wikia.tibia.shouldNotBe
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import com.wikia.tibia.v2.domain.loot.LootRepository
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LootStatisticsServiceTest {

  private val creatureRepository = mockkClass(CreatureRepository::class)
  private val lootRepository = mockkClass(LootRepository::class)
  private val lootStatisticsService = LootStatisticsService(creatureRepository, lootRepository)

  @Test
  fun `should do nothing`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootRat()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreatureRat()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()

      result.size shouldBe 0
    }
  }

  @Test
  fun `should add Sword to loot of Rat`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootRatWithSword()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreatureRat()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()

      result.size shouldBe 1
      result["Rat"] shouldNotBe null
      result["Rat"]?.loot?.size shouldBe 3
      result["Rat"]?.loot?.any { it.itemName == "Sword" } shouldBe true
    }
  }

  @Test
  fun `should not add skull to loot list of amazon`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootAmazon()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreatureAmazon()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 0
    }
  }

  @Test
  fun `should not add demon goblin loot to demon`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootDemon()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreatureDemon()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 1
      result["Demon"]?.loot?.any { it.itemName == "Demonrage Sword" } shouldBe true
      result["Demon"]?.loot?.none { it.itemName == "Mouldy Cheese" } shouldBe true
    }
  }

  @Test
  fun `should not add Secret Instruction item to any creature page`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootPriestessOfTheWildSun()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreaturePriestessOfTheWildSun()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 1
      result["Priestess of the Wild Sun"]?.loot?.any { it.itemName == "Secret Instruction (Silver)" } shouldBe true
      result["Priestess of the Wild Sun"]?.loot?.none { it.itemName == "Secret Instruction" } shouldBe true
    }
  }

  @Test
  fun `should not add certain items to The Percht Queen creature page`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootThePerchtQueen()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCreatureThePerchtQueen()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 1
      result["The Percht Queen"]?.loot?.any { it.itemName == "The Crown of the Percht Queen (Fire)" } shouldBe true
      result["The Percht Queen"]?.loot?.none { it.itemName == "The Crown of the Percht Queen" } shouldBe true
      result["The Percht Queen"]?.loot?.any { it.itemName == "Giant Shimmering Pearl (Brown)" } shouldBe true
      result["The Percht Queen"]?.loot?.none { it.itemName == "Giant Shimmering Pearl" } shouldBe true
    }
  }

  private fun createCreatureRat(): Creature {
    return Creature(
      actualname = "Rat",
      name = "Rat",
      loot = mutableListOf(
        LootItem(itemName = "Gold Coin", amount = "0-4"),
        LootItem(itemName = "Cheese")
      )
    )
  }

  private fun createLootRat(): LootWrapper {
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

  private fun createLootRatWithSword(): LootWrapper {
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

  private fun createLootAmazon(): LootWrapper {
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

  private fun createCreatureAmazon(): Creature {
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

  private fun createCreatureDemon(): Creature {
    return Creature(
      actualname = "Demon",
      name = "Demon",
      loot = mutableListOf(LootItem(itemName = "Magic Plate Armor"))
    )
  }

  private fun createLootDemon(): LootWrapper {
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

  private fun createCreaturePriestessOfTheWildSun(): Creature {
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

  private fun createLootPriestessOfTheWildSun(): LootWrapper {
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

  private fun createCreatureThePerchtQueen(): Creature {
    return Creature(
      actualname = "The Percht Queen",
      name = "The Percht Queen",
      loot = mutableListOf(
        LootItem(itemName = "Giant Shimmering Pearl (Brown)"),
        LootItem(itemName = "Giant Shimmering Pearl (Green)"),
        LootItem(itemName = "The Crown of the Percht Queen (Ice)"),
        LootItem(itemName = "The Crown of the Percht Queen (Fire)"),
      )
    )
  }

  private fun createLootThePerchtQueen(): LootWrapper {
    return LootWrapper(
      loot2 = Loot(
        kills = "109",
        pageName = "The Percht Queen",
        name = "The Percht Queen",
        loot = mutableListOf(
          LootStatisticsItem(itemName = "Giant Shimmering Pearl", times = "13", amount = "1", total = "13"),
          LootStatisticsItem(itemName = "The Crown of the Percht Queen", times = "1"),
          LootStatisticsItem(itemName = "Horseshoe", times = "1", amount = "1", total = "1"),
        ),
        version = "12.03"
      )
    )
  }
}

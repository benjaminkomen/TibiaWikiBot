package com.wikia.tibia.v2.domain

import com.wikia.tibia.createAmazon
import com.wikia.tibia.createDemon
import com.wikia.tibia.createLootAmazon
import com.wikia.tibia.createLootDemon
import com.wikia.tibia.createLootPriestessOfTheWildSun
import com.wikia.tibia.createLootRat
import com.wikia.tibia.createLootRatWithSword
import com.wikia.tibia.createLootThePerchtQueen
import com.wikia.tibia.createPriestessOfTheWildSun
import com.wikia.tibia.createRat
import com.wikia.tibia.createThePerchtQueen
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
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createRat()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()

      result.size shouldBe 0
    }
  }

  @Test
  fun `should add Sword to loot of Rat`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootRatWithSword()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createRat()) }

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
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createAmazon()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 0
    }
  }

  @Test
  fun `should not add demon goblin loot to demon`() {
    coEvery { lootRepository.getLootList() } coAnswers { listOf(createLootDemon()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createDemon()) }

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
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createPriestessOfTheWildSun()) }

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
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createThePerchtQueen()) }

    runTest {
      val result = lootStatisticsService.getCreaturesWithUpdatedLootFromLootStatisticsPage()
      result.size shouldBe 1
      result["The Percht Queen"]?.loot?.any { it.itemName == "The Crown of the Percht Queen (Fire)" } shouldBe true
      result["The Percht Queen"]?.loot?.none { it.itemName == "The Crown of the Percht Queen" } shouldBe true
      result["The Percht Queen"]?.loot?.any { it.itemName == "Giant Shimmering Pearl (Brown)" } shouldBe true
      result["The Percht Queen"]?.loot?.none { it.itemName == "Giant Shimmering Pearl" } shouldBe true
    }
  }
}

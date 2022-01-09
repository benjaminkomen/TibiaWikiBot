package com.wikia.tibia.v2.domain

import com.wikia.tibia.createBear
import com.wikia.tibia.createCheese
import com.wikia.tibia.createCyclops
import com.wikia.tibia.createHoneycomb
import com.wikia.tibia.createMeat
import com.wikia.tibia.createOldRag
import com.wikia.tibia.createRat
import com.wikia.tibia.createWasp
import com.wikia.tibia.shouldBe
import com.wikia.tibia.shouldNotBe
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import com.wikia.tibia.v2.domain.item.ItemRepository
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CreaturesServiceTest {

  private val creatureRepository = mockkClass(CreatureRepository::class)
  private val itemRepository = mockkClass(ItemRepository::class)
  private val creaturesService = CreaturesService(creatureRepository, itemRepository)

  @Test
  fun `should do nothing`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createCheese()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createRat()) }

    runTest {
      val result = creaturesService.getItemsWithUpdatedLootFromCreaturesPage()

      result.size shouldBe 0
    }
  }

  @Test
  fun `should add bear but not wasp to droppedBy of honeycomb`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createHoneycomb()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createBear()) }

    runTest {
      val result = creaturesService.getItemsWithUpdatedLootFromCreaturesPage()

      result.size shouldBe 1
      result["Honeycomb"] shouldNotBe null
      result["Honeycomb"]?.droppedby?.size shouldBe 5
      result["Honeycomb"]?.droppedby?.contains("Bear") shouldBe true
      result["Honeycomb"]?.droppedby?.contains("Wasp") shouldBe false
    }
  }

  @Test
  fun `should add both bear and wasp to droppedBy of honeycomb`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createHoneycomb()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createBear(), createWasp()) }

    runTest {
      val result = creaturesService.getItemsWithUpdatedLootFromCreaturesPage()

      result.size shouldBe 1
      result["Honeycomb"] shouldNotBe null
      result["Honeycomb"]?.droppedby?.size shouldBe 6
      result["Honeycomb"]?.droppedby?.contains("Bear") shouldBe true
      result["Honeycomb"]?.droppedby?.contains("Wasp") shouldBe true
    }
  }

  @Test
  fun `should add meat but not add old rag to loot list of cyclops`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createOldRag(), createMeat()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createCyclops()) }

    runTest {
      val result = creaturesService.getItemsWithUpdatedLootFromCreaturesPage()

      result.size shouldBe 1
      result["Meat"] shouldNotBe null
      result["Old Rag"] shouldBe null
    }
  }
}

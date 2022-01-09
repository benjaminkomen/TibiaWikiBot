package com.wikia.tibia.v2.domain

import com.wikia.tibia.createBear
import com.wikia.tibia.createBearPaw
import com.wikia.tibia.createCheese
import com.wikia.tibia.createHoneycomb
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
internal class ItemsServiceTest {

  private val creatureRepository = mockkClass(CreatureRepository::class)
  private val itemRepository = mockkClass(ItemRepository::class)
  private val itemsService = ItemsService(creatureRepository, itemRepository)

  @Test
  fun `should do nothing`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createCheese()) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createRat()) }

    runTest {
      val result = itemsService.getCreaturesWithUpdatedDroppedByFromItemPage()

      result.size shouldBe 0
    }
  }

  @Test
  fun `should add honeycomb to loot of bear`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createHoneycomb().copy(droppedby = mutableListOf("Bear"))) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createBear().copy(loot = mutableListOf())) }

    runTest {
      val result = itemsService.getCreaturesWithUpdatedDroppedByFromItemPage()

      result.size shouldBe 1
      result["Bear"] shouldNotBe null
      result["Bear"]?.loot?.size shouldBe 1
      result["Bear"]?.loot?.any { it.itemName == "Honeycomb" } shouldBe true
    }
  }

  @Test
  fun `should add honeycomb to loot of bear and wasp`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createHoneycomb().copy(droppedby = mutableListOf("Bear", "Wasp"))) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createBear().copy(loot = mutableListOf()), createWasp().copy(loot = mutableListOf())) }

    runTest {
      val result = itemsService.getCreaturesWithUpdatedDroppedByFromItemPage()

      result.size shouldBe 2
      result["Bear"] shouldNotBe null
      result["Bear"]?.loot?.size shouldBe 1
      result["Bear"]?.loot?.any { it.itemName == "Honeycomb" } shouldBe true

      result["Wasp"] shouldNotBe null
      result["Wasp"]?.loot?.size shouldBe 1
      result["Wasp"]?.loot?.any { it.itemName == "Honeycomb" } shouldBe true
    }
  }

  @Test
  fun `should add honeycomb and bear paw to loot of bear`() {
    coEvery { itemRepository.getItems() } coAnswers { listOf(createHoneycomb().copy(droppedby = mutableListOf("Bear")), createBearPaw().copy(droppedby = mutableListOf("Bear"))) }
    coEvery { creatureRepository.getCreatures() } coAnswers { listOf(createBear().copy(loot = mutableListOf())) }

    runTest {
      val result = itemsService.getCreaturesWithUpdatedDroppedByFromItemPage()

      result.size shouldBe 1
      result["Bear"] shouldNotBe null
      result["Bear"]?.loot?.size shouldBe 2
      result["Bear"]?.loot?.any { it.itemName == "Honeycomb" } shouldBe true
      result["Bear"]?.loot?.any { it.itemName == "Bear Paw" } shouldBe true
    }
  }
}

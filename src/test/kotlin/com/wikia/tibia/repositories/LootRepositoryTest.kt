package com.wikia.tibia.repositories

import com.wikia.tibia.exceptions.JacksonParsingException
import com.wikia.tibia.gateways.LootGateway
import com.wikia.tibia.objects.Loot
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import io.vavr.control.Try
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

internal class LootRepositoryTest {

  @Mock
  private lateinit var lootGateway: LootGateway
  private lateinit var lootRepository: LootRepository

  @Before
  fun setup() {
    lootGateway = mock(LootGateway::class.java)
    lootRepository = LootRepository(lootGateway)
  }

  @Test
  fun `should get empty list of LootWrapper objects`() {
    val someJson =
      """[]"""
    `when`(lootGateway.getLoot(true)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot()

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get().size, `is`(0))
  }

  @Test
  fun `should get list of LootWrapper objects successfully`() {
    val someJson =
      """[
  {
    "loot2": {
      "kills": "2",
      "name": "Dreadmaw",
      "loot": [
        {
          "itemName": "Gold Coin",
          "times": "2",
          "amount": "67-69",
          "total": "136"
        },
        {
          "itemName": "Ham",
          "times": "1"
        }
      ],
      "version": "8.6",
      "pageName": "Loot Statistics:Dreadmaw"
    }
  },
  {
    "loot2_rc": {
      "kills": "25",
      "name": "Duke Krule",
      "loot": [
        {
          "itemName": "Collar of Green Plasma",
          "times": "4",
          "amount": "1",
          "total": "4"
        },
        {
          "itemName": "Crystal Coin",
          "times": "6",
          "amount": "1-2",
          "total": "10"
        }
      ],
      "pageName": "Loot Statistics:Duke Krule",
      "version": "8.6"
    }
  },
  {
    "loot2": {
      "kills": "93",
      "name": "Rift Brood",
      "loot": [
        {
          "itemName": "Empty",
          "times": "93"
        }
      ],
      "version": "8.6",
      "pageName": "Loot Statistics:Rift Brood"
    }
  }
]""".trimMargin()
    `when`(lootGateway.getLoot(true)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot()

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get().size, `is`(3))

    val expectedDreadmaw = Loot(
      pageName = "Loot Statistics:Dreadmaw",
      version = "8.6",
      kills = "2",
      name = "Dreadmaw",
      loot = listOf(
        LootStatisticsItem(
          itemName = "Gold Coin",
          times = "2",
          amount = "67 - 69",
          total = "136"
        ),
        LootStatisticsItem(
          itemName = "Ham",
          times = "1"
        )
      )
    )
    assertThat(result.get()[0].loot2, `is`(expectedDreadmaw))
    assertThat(result.get()[0].loot2Rc, `is`(nullValue()))
    assertThat(result.get()[0].getMergedLoot(), `is`(expectedDreadmaw))

    val expectedDukeKrule = Loot(
      pageName = "Loot Statistics:Duke Krule",
      version = "8.6",
      kills = "25",
      name = "Duke Krule",
      loot = listOf(
        LootStatisticsItem(
          itemName = "Collar of Green Plasma",
          times = "4",
          amount = "1",
          total = "4"
        ),
        LootStatisticsItem(
          itemName = "Crystal Coin",
          times = "6",
          amount = "1-2",
          total = "10"
        )
      )
    )
    assertThat(result.get()[1].loot2, `is`(nullValue()))
    assertThat(result.get()[1].loot2Rc, `is`(expectedDukeKrule))
    assertThat(result.get()[1].getMergedLoot(), `is`(expectedDukeKrule))

    val expectedRiftBrood = Loot(
      pageName = "Loot Statistics:Rift Brood",
      version = "8.6",
      kills = "93",
      name = "Rift Brood",
      loot = listOf(
        LootStatisticsItem(
          itemName = "Empty",
          times = "93"
        )
      )
    )
    assertThat(result.get()[2].loot2, `is`(expectedRiftBrood))
    assertThat(result.get()[2].loot2Rc, `is`(nullValue()))
    assertThat(result.get()[2].getMergedLoot(), `is`(expectedRiftBrood))
  }

  @Test
  fun `should should return success when getting list of LootWrapper objects, even when part of the JSON fails to parse`() {
    val someJson =
      """[
  {
    "loot2": {
      "kills": "93",
      "name": "Rift Brood",
      "loot": [
        {
          "itemName": "Empty",
          "times": "93"
        }
      ],
      "version": "8.6",
      "pageName": "Loot Statistics:Rift Brood"
    }
  },
  {
    "loot2": {
      "foo": "2",
      "name": "Dreadmaw"
    }
  }
]"""
    `when`(lootGateway.getLoot(true)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot()

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get().size, `is`(1))
  }

  @Test
  fun `should get empty LootWrapper object because of null input`() {
    val someJson = ""
    `when`(lootGateway.getLoot("Loot Statistics:Rift Brood")).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot("Loot Statistics:Rift Brood")

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get(), `is`(nullValue()))
  }

  @Test
  fun `should get LootWrapper object successfully`() {
    val someJson =
      """{
    "loot2": {
      "kills": "93",
      "name": "Rift Brood",
      "loot": [
        {
          "itemName": "Empty",
          "times": "93"
        }
      ],
      "version": "8.6",
      "pageName": "Loot Statistics:Rift Brood"
    }
  }"""
    `when`(lootGateway.getLoot("Loot Statistics:Rift Brood")).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot("Loot Statistics:Rift Brood")

    val expectedRiftBrood = LootWrapper(
      loot2 = Loot(
        pageName = "Loot Statistics:Rift Brood",
        version = "8.6",
        kills = "93",
        name = "Rift Brood",
        loot = listOf(
          LootStatisticsItem(
            itemName = "Empty",
            times = "93"
          )
        )
      )
    )
    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get(), `is`(expectedRiftBrood))
  }

  @Test
  fun `should fail getting LootWrapper object because of exception`() {
    val someJson =
      """{
    "loot3": {
      "foo": "93",
      "name": "Rift Brood"
    }
  }"""
    `when`(lootGateway.getLoot("Loot Statistics:Rift Brood")).thenReturn(Try.success(someJson))

    val result = lootRepository.getLoot("Loot Statistics:Rift Brood")

    assertThat(result.isFailure, `is`(true))
    assertThat(result.cause, instanceOf(JacksonParsingException::class.java))
  }

  @Test
  fun `should get list of loot names successfully`() {
    val someJson =
      """[
"Loot Statistics:Abyssador",
"Loot Statistics:Abyssal Calamary",
"Loot Statistics:Achad"
]"""
    `when`(lootGateway.getLoot(false)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLootList()

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get().size, `is`(3))
  }

  @Test
  fun `should get empty list of loot names`() {
    val someJson =
      """[]"""
    `when`(lootGateway.getLoot(false)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLootList()

    assertThat(result.isSuccess, `is`(true))
    assertThat(result.get().size, `is`(0))
  }

  @Test
  fun `should return failure when getting list of loot names because of JacksonParsingException`() {
    val someJson =
      """["foo,:1']"""
    `when`(lootGateway.getLoot(false)).thenReturn(Try.success(someJson))

    val result = lootRepository.getLootList()

    assertThat(result.isFailure, `is`(true))
    assertThat(result.cause, instanceOf(JacksonParsingException::class.java))
  }
}

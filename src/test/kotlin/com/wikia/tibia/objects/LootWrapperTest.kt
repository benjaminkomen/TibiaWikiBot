package com.wikia.tibia.objects

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class LootWrapperTest {

    @Test
    fun `get merged loot - if only loot2 exists`() {
        val loot2 = Loot(
                pageName = "Loot Statistics:Bear",
                version = "8.6",
                kills = "15",
                name = "Bear",
                loot = listOf(
                        LootStatisticsItem(
                                itemName = "foo",
                                times = "25",
                                amount = "1-10",
                                total = "25")
                )
        )
        val input = LootWrapper(
                loot2 = loot2
        )

        assertThat(input.getMergedLoot(), `is`(loot2))
    }

    @Test
    fun `get merged loot - if only loot2_rc exists`() {
        val loot2rc = Loot(
                pageName = "Loot Statistics:Bear",
                version = "8.6",
                kills = "15",
                name = "Bear",
                loot = listOf(
                        LootStatisticsItem(
                                itemName = "foo",
                                times = "25",
                                amount = "1-10",
                                total = "25")
                )
        )
        val input = LootWrapper(
                loot2Rc = loot2rc
        )

        assertThat(input.getMergedLoot(), `is`(loot2rc))
    }

    @Test
    fun `get merged loot - if both loot2 and loot2rc exist`() {
        val loot2 = Loot(
                pageName = "Loot Statistics:Bear",
                version = "8.6",
                kills = "15",
                name = "Bear",
                loot = listOf(
                        LootStatisticsItem(
                                itemName = "Meat",
                                times = "25",
                                amount = "1-10",
                                total = "25")
                )
        )

        val loot2rc = Loot(
                pageName = "Loot Statistics:Bear",
                version = "8.6",
                kills = "4",
                name = "Bear",
                loot = listOf(
                        LootStatisticsItem(
                                itemName = "Meat",
                                times = "5",
                                amount = "2",
                                total = "10"),
                        LootStatisticsItem(
                                itemName = "Bear Paw",
                                times = "10",
                                amount = "1",
                                total = "10")
                )
        )

        val expectedMergedLoot = Loot(
                pageName = "Loot Statistics:Bear",
                version = "8.6",
                kills = "19",
                name = "Bear",
                loot = listOf(
                        LootStatisticsItem(
                                itemName = "Bear Paw",
                                times = "10",
                                amount = "1",
                                total = "10"),
                        LootStatisticsItem(
                                itemName = "Meat",
                                times = "30",
                                amount = "1-10",
                                total = "35")
                )
        )

        val input = LootWrapper(
                loot2 = loot2,
                loot2Rc = loot2rc
        )

        assertThat(input.getMergedLoot(), `is`(expectedMergedLoot))
    }

}
package com.wikia.tibia.objects

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class LootStatisticsItemTest {

    @Test
    fun `should be same if itemName is the same`() {
        val item1 = LootStatisticsItem(
                itemName = "foo",
                times = "1",
                amount = "33"
        )

        val item2 = LootStatisticsItem(
                itemName = "foo",
                times = "333"
        )

        assertThat(item1 == item2, `is`(true))
    }

    @Test
    fun `should be different if itemName is different`() {
        val item1 = LootStatisticsItem(
                itemName = "foo",
                times = "1",
                amount = "33"
        )

        val item2 = LootStatisticsItem(
                itemName = "bar",
                times = "1"
        )

        assertThat(item1 == item2, `is`(false))
    }

    @Test
    fun `should add every property of two items with equal single number amount`() {
        val item1 = LootStatisticsItem(
                itemName = "foo",
                times = "5",
                amount = "1",
                total = "5"
        )

        val item2 = LootStatisticsItem(
                itemName = "foo",
                times = "25",
                amount = "1",
                total = "25"
        )

        val expectedSum = LootStatisticsItem(
                itemName = "foo",
                times = "30",
                amount = "1",
                total = "30"
        )

        assertThat(item1.add(item2), `is`(expectedSum))
    }

    @Test
    fun `should add every property of two items even if half is missing`() {
        val item1 = LootStatisticsItem(
                itemName = "foo",
                times = "5"
        )

        val item2 = LootStatisticsItem(
                itemName = "foo",
                times = "25",
                amount = "1-10",
                total = "25"
        )

        val expectedSum = LootStatisticsItem(
                itemName = "foo",
                times = "30",
                amount = "1-10",
                total = "25"
        )

        assertThat(item1.add(item2), `is`(expectedSum))
    }

    @Test
    fun `should compute max of two numbers`() {
        assertThat(LootStatisticsItem.`compute max of two numbers`("1", "99"), `is`("99"))
        assertThat(LootStatisticsItem.`compute max of two numbers`("0", "1"), `is`("1"))
        assertThat(LootStatisticsItem.`compute max of two numbers`("23", "5"), `is`("23"))
    }

    @Test
    fun `should compute lowest to highest of two ranges`() {
        assertThat(LootStatisticsItem.`compute lowest to highest of two ranges`("1-2", "1-5"), `is`("1-5"))
        assertThat(LootStatisticsItem.`compute lowest to highest of two ranges`("1-9", "3-6"), `is`("1-9"))
        assertThat(LootStatisticsItem.`compute lowest to highest of two ranges`("1-5", "4-25"), `is`("1-25"))
        assertThat(LootStatisticsItem.`compute lowest to highest of two ranges`("6-10", "1-6"), `is`("1-10"))
    }

    @Test
    fun `should compute with mix of range and number`() {
        assertThat(LootStatisticsItem.`compute with mix of range and number`("1-2", "5"), `is`("1-5"))
        assertThat(LootStatisticsItem.`compute with mix of range and number`("9", "3-6"), `is`("3-9"))
        assertThat(LootStatisticsItem.`compute with mix of range and number`("1-5", "4"), `is`("1-5"))
        assertThat(LootStatisticsItem.`compute with mix of range and number`("10", "1-6"), `is`("1-10"))
    }

}
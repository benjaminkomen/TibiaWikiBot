package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class LootWrapper(
        val loot2: Loot? = null,
        @JsonProperty("loot2_rc") val loot2Rc: Loot? = null,
        @JsonIgnore private var mergedLoot: Loot? = null
) {

    fun getMergedLoot(): Loot? {
        if (mergedLoot == null) {
            mergedLoot = computeMergedLoot()
        }
        return mergedLoot
    }

    private fun computeMergedLoot(): Loot? {
        return if (loot2 == null && loot2Rc == null) {
            null
        } else if (loot2 == null) {
            loot2Rc
        } else if (loot2Rc == null) {
            loot2
        } else {
            // they are both non-null
            Loot(
                    name = loot2.name, // assume loot2Rc does not have a different name
                    pageName = loot2.pageName, // assume they both have the same pagename
                    version = loot2.version, // just take the version of loot2, it doesn't really matter
                    kills = sumKills(),
                    loot = sumLoot(loot2.loot, loot2Rc.loot)
            )
        }
    }

    private fun sumKills(): String {
        return loot2Rc?.kills?.toInt()
                ?.let { loot2?.kills?.toInt()?.plus(it).toString() }
                ?: ""
    }

    private fun sumLoot(first: List<LootStatisticsItem>?, second: List<LootStatisticsItem>?): List<LootStatisticsItem> {
        val result: MutableSet<LootStatisticsItem> = HashSet()

        first?.forEach { addOrMergeItem(result, it) }
        second?.forEach { addOrMergeItem(result, it) }

        return result.toList()
    }

    private fun addOrMergeItem(result: MutableSet<LootStatisticsItem>, newItem: LootStatisticsItem) {
        val existingItem = findItem(result, newItem.itemName)
        if (existingItem == null) {
            // item not in list yet, add it
            result.add(newItem)
        } else {
            // item already in list, merge it
            val mergedItem = existingItem.add(newItem)
            result.remove(existingItem)
            result.add(mergedItem)
        }
    }

    private fun findItem(items: Set<LootStatisticsItem?>, itemName: String): LootStatisticsItem? {
        return items.firstOrNull { item -> item?.itemName == itemName }
    }
}
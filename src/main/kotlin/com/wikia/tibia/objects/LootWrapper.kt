package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import java.util.function.Consumer

data class LootWrapper(
        private val loot2: Loot?,
        @JsonProperty("loot2_rc") private val loot2Rc: Loot?,
        @JsonIgnore val mergedLoot: Loot
) {

    private fun getMergedLoot() {
        if (field == null) {
            field = computeMergedLoot()
        }
        return field
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
        first.forEach(Consumer { newItem: LootStatisticsItem ->
            val existingItem = findItem(result, newItem.itemName)
            if (existingItem.isEmpty) {
                // item not in list yet, add it
                result.add(newItem)
            } else {
                // item already in list, merge it
                val mergedItem = existingItem.get().add(newItem)
                result.remove(existingItem.get())
                result.add(mergedItem)
            }
        })
        second.forEach(Consumer { newItem: LootStatisticsItem ->
            val existingItem = findItem(result, newItem.itemName)
            if (existingItem.isEmpty) {
                // item not in list yet, add it
                result.add(newItem)
            } else {
                // item already in list, merge it
                val mergedItem = existingItem.get().add(newItem)
                result.remove(existingItem.get())
                result.add(mergedItem)
            }
        })
        return listOf(result)
    }

    private fun findItem(items: Set<LootStatisticsItem?>, itemName: String): LootStatisticsItem? {
        return items
                .filter { item -> item?.itemName == itemName }
                .firstOrNull()
    }
}
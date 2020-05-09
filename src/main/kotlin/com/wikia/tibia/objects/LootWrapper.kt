package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*
import java.util.*
import java.util.function.Consumer

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
class LootWrapper {
    private val loot2: Loot? = null

    @JsonProperty("loot2_rc")
    private val loot2Rc: Loot? = null

    @JsonIgnore
    var mergedLoot: Loot? = null
        get() {
            if (field == null) {
                field = computeMergedLoot()
            }
            return field
        }
        private set

    private fun computeMergedLoot(): Loot? {
        return if (loot2 == null && loot2Rc == null) {
            null
        } else if (loot2 == null) {
            loot2Rc
        } else if (loot2Rc == null) {
            loot2
        } else {
            // they are both non-null
            Loot.builder()
                    .name(loot2.name) // assume loot2Rc does not have a different name
                    .pageName(loot2.pageName) // assume they both have the same pagename
                    .version(loot2.version) // just take the version of loot2, it doesn't really matter
                    .kills(sumKills())
                    .loot(sumLoot(loot2.loot, loot2Rc.loot))
                    .build()
        }
    }

    private fun sumKills(): String {
        return (loot2.getKills().toInt() + loot2Rc.getKills().toInt()).toString()
    }

    private fun sumLoot(first: List<LootStatisticsItem>, second: List<LootStatisticsItem>): List<LootStatisticsItem?> {
        val result: MutableSet<LootStatisticsItem?> = HashSet()
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
        return ArrayList(result)
    }

    private fun findItem(items: Set<LootStatisticsItem?>, itemName: String): Optional<LootStatisticsItem?> {
        return items.stream()
                .filter { i: LootStatisticsItem? -> i.getItemName() == itemName }
                .findAny()
    }
}
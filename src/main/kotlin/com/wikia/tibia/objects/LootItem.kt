package com.wikia.tibia.objects

import com.wikia.tibia.enums.Rarity
import java.util.*

data class LootItem(
        val itemName: String,
        private val amount: String?,
        private val rarity: Rarity?
) {

    override fun equals(other: Any?): Boolean {
        return other is LootItem &&
                itemName == other.itemName
    }

    override fun hashCode(): Int {
        return Objects.hash(itemName)
    }

    companion object {

        fun fromName(itemName: String): LootItem {
            return LootItem(
                    itemName = itemName,
                    amount = null,
                    rarity = null
            )
        }
    }
}
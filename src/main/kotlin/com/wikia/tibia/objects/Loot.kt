package com.wikia.tibia.objects

data class Loot(
        private val pageName: String,
        private val version: String,
        private val kills: String,
        private val name: String,
        private val loot: List<LootStatisticsItem>?,
        private val isEmpty: Boolean
) {
    fun isEmpty() = loot == null ||
            loot.isEmpty() || loot.size == 1 && "Empty" == loot[0].itemName
}
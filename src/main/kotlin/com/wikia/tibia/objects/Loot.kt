package com.wikia.tibia.objects

data class Loot(
        val pageName: String,
        val version: String,
        val kills: String,
        val name: String,
        val loot: List<LootStatisticsItem>?
) {
    fun isEmpty() = loot == null ||
            loot.isEmpty() ||
            loot.size == 1 && "Empty" == loot[0].itemName
}
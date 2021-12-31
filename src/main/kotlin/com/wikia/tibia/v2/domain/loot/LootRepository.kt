package com.wikia.tibia.v2.domain.loot

import com.wikia.tibia.objects.LootWrapper

interface LootRepository {

  suspend fun getLootList(): List<LootWrapper>

  suspend fun getLootNames(): List<String>

  suspend fun getLoot(name: String): LootWrapper
}

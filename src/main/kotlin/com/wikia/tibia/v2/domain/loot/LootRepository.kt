package com.wikia.tibia.v2.domain.loot

import com.wikia.tibia.objects.LootWrapper

interface LootRepository {

  fun getLootList(): List<LootWrapper>

  fun getLootNames(): List<String>

  fun getLoot(name: String): LootWrapper
}

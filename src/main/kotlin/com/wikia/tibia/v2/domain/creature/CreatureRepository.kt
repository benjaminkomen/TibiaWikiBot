package com.wikia.tibia.v2.domain.creature

import com.wikia.tibia.objects.Creature

interface CreatureRepository {

  suspend fun getCreatures(): List<Creature>

  suspend fun getCreatureNames(): List<String>

  suspend fun getCreature(name: String): Creature?

  suspend fun updateCreature(creature: Creature, editSummary: String? = ""): Creature
}

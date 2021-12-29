package com.wikia.tibia.v2.domain.creature

import com.wikia.tibia.objects.Creature

interface CreatureRepository {

  fun getCreatures(): List<Creature>

  fun getCreatureNames(): List<String>

  fun getCreature(name: String): Creature

  fun updateCreature(creature: Creature, editSummary: String? = ""): Creature
}

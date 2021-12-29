package com.wikia.tibia.v2.adapters.creature

import com.google.common.net.UrlEscapers
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import org.slf4j.LoggerFactory

class CreatureRepositoryImpl : CreatureRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }

  override fun getCreatures(): List<Creature> {
    return client.getCreatures().execute()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of creatures")
        throw RuntimeException("Could not get list of creatures")
      }
  }

  override fun getCreatureNames(): List<String> {
    return client.getCreatureNames().execute()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of creature names")
        throw RuntimeException("Could not get list of creature names")
      }
  }

  override fun getCreature(name: String): Creature {
    return client.getCreature(UrlEscapers.urlFragmentEscaper().escape(name)).execute()
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not get creature $name")
        throw RuntimeException("Could not get creature $name")
      }
  }

  override fun updateCreature(creature: Creature, editSummary: String?): Creature {
    return client.updateCreature(editSummary, creature).execute()
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not update creature ${creature.name}")
        throw RuntimeException("Could not update creature ${creature.name}")
      }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("CreatureRepository")
  }
}

package com.wikia.tibia.v2.adapters.creature

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class CreatureRepositoryImpl : CreatureRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }

  private val creaturesCache: LoadingCache<String, List<Creature>?> by lazy {
    Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(1)
      .build(this::getCreaturesInternal)
  }

  private val creatureCache: LoadingCache<String, Creature?> by lazy {
    Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(10_000)
      .build(this::getCreatureInternal)
  }

  override fun getCreatures(): List<Creature> {
    return creaturesCache.get("all") ?: emptyList()
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

  override fun getCreature(name: String): Creature? {
    return creatureCache.get(name)
  }

  override fun updateCreature(creature: Creature, editSummary: String?): Creature {
    return client.updateCreature(editSummary, creature).execute()
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not update creature ${creature.name}")
        throw RuntimeException("Could not update creature ${creature.name}")
      }
  }

  private fun getCreaturesInternal(key: String): List<Creature> {
    return try {
      val response = client.getCreatures().execute()
      if (response.isSuccessful) {
        response.body() ?: emptyList()
      } else {
        logger.error("Could not get list of creatures for key $key because: ${response.errorBody() ?: response.message()}")
        emptyList()
      }
    } catch (e: Exception) {
      logger.error("Could not get list of creatures because of an error", e)
      emptyList()
    }
  }

  private fun getCreatureInternal(name: String): Creature? {
    return try {
      val response = client.getCreature(name).execute()
      if (response.isSuccessful) {
        response.body()
      } else {
        logger.error("Could not get creature $name because: ${response.errorBody() ?: response.message()}")
        null
      }
    } catch (e: Exception) {
      logger.error("Could not get creature $name because of an error", e)
      null
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("CreatureRepository")
  }
}

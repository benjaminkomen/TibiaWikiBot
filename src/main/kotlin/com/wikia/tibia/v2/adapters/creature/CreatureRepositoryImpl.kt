package com.wikia.tibia.v2.adapters.creature

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.wikia.tibia.jackson.Parser
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.creature.CreatureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.future.future
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class CreatureRepositoryImpl : CreatureRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }
  private val creaturesCache: AsyncLoadingCache<String, List<Creature>?>
  private val creatureCache: AsyncLoadingCache<String, Creature?>

  init {
    creaturesCache = Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(1)
      .buildAsync { key, executor ->
        CoroutineScope(executor.asCoroutineDispatcher()).future {
          getCreaturesInternal(key)
        }
      }

    creatureCache = Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(10_000)
      .buildAsync { key, executor ->
        CoroutineScope(executor.asCoroutineDispatcher()).future {
          getCreatureInternal(key)
        }
      }
  }

  override suspend fun getCreatures(): List<Creature> {
    return creaturesCache.get("all").join() ?: emptyList()
  }

  override suspend fun getCreatureNames(): List<String> {
    logger.info("Getting all creature names")
    return client.getCreatureNames()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of creature names")
        throw RuntimeException("Could not get list of creature names")
      }
  }

  override suspend fun getCreature(name: String): Creature? {
    return creatureCache.get(name).join()
  }

  override suspend fun updateCreature(creature: Creature, editSummary: String?): Creature {
    return client.updateCreature(editSummary, creature)
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not update creature ${creature.name}")
        throw RuntimeException("Could not update creature ${creature.name}")
      }
  }

  private suspend fun getCreaturesInternal(key: String): List<Creature> {
    return try {
      logger.info("Getting all creatures")
      val response = client.getCreatures()
      if (response.isSuccessful) {
        response.body()
          ?.mapNotNull { Parser.mapToType(type = Creature::class.java, json = it) }
          ?: emptyList()
      } else {
        logger.error("Could not get list of creatures for key $key because: ${response.errorBody() ?: response.message()}")
        emptyList()
      }
    } catch (e: Exception) {
      logger.error("Could not get list of creatures because of an error", e)
      emptyList()
    }
  }

  private suspend fun getCreatureInternal(name: String): Creature? {
    return try {
      logger.info("Getting creature $name")
      val response = client.getCreature(name)
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

package com.wikia.tibia.v2.adapters.loot

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.loot.LootRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.future.future
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class LootRepositoryImpl : LootRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }
  private val lootPagesCache: AsyncLoadingCache<String, List<LootWrapper>?>

  init {
    lootPagesCache = Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(1)
      .buildAsync { key, executor ->
        CoroutineScope(executor.asCoroutineDispatcher()).future {
          getLootPagesInternal(key)
        }
      }
  }

  override suspend fun getLootList(): List<LootWrapper> {
    return lootPagesCache.get("any").join() ?: emptyList()
  }

  override suspend fun getLootNames(): List<String> {
    logger.info("Getting all loot page names")
    return client.getLootListNames()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of loot names")
        throw RuntimeException("Could not get list of loot names")
      }
  }

  override suspend fun getLoot(name: String): LootWrapper {
    logger.info("Getting loot page $name")
    return client.getLoot(name)
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not get loot $name")
        throw RuntimeException("Could not get loot $name")
      }
  }

  private suspend fun getLootPagesInternal(key: String): List<LootWrapper> {
    logger.info("Getting all loot pages")
    return client.getLootList()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of loot for key: $key")
        throw RuntimeException("Could not get list of loot for key: $key")
      }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("LootRepository")
  }
}

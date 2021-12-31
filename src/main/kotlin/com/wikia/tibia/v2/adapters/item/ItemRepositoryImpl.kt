package com.wikia.tibia.v2.adapters.item

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.item.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.future.future
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class ItemRepositoryImpl : ItemRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }
  private val itemsCache: AsyncLoadingCache<String, List<TibiaObject>?>
  private val itemCache: AsyncLoadingCache<String, TibiaObject?>

  init {
    itemsCache = Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(1)
      .buildAsync { key, executor ->
        CoroutineScope(executor.asCoroutineDispatcher()).future {
          getItemsInternal(key)
        }
      }

    itemCache = Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(10_000)
      .buildAsync { key, executor ->
        CoroutineScope(executor.asCoroutineDispatcher()).future {
          getItemInternal(key)
        }
      }
  }

  override suspend fun getItems(): List<TibiaObject> {
    return itemsCache.get("all").join() ?: emptyList()
  }

  override suspend fun getItemNames(): List<String> {
    logger.info("Getting all item names")
    return client.getItemNames()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of item names")
        throw RuntimeException("Could not get list of item names")
      }
  }

  override suspend fun getItem(name: String): TibiaObject? {
    return itemCache.get(name).join()
  }

  override suspend fun updateItem(item: TibiaObject, editSummary: String?): TibiaObject {
    return client.updateItems(editSummary, item)
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not update item ${item.name}")
        throw RuntimeException("Could not update item ${item.name}")
      }
  }

  private suspend fun getItemsInternal(key: String): List<TibiaObject> {
    return try {
      logger.info("Getting all items")
      val response = client.getItems()
      if (response.isSuccessful) {
        response.body() ?: emptyList()
      } else {
        logger.error("Could not get list of items for key $key because: ${response.errorBody() ?: response.message()}")
        emptyList()
      }
    } catch (e: Exception) {
      logger.error("Could not get list of items because of an error", e)
      emptyList()
    }
  }

  private suspend fun getItemInternal(name: String): TibiaObject? {
    return try {
      logger.info("Getting item $name")
      val response = client.getItem(name)
      if (response.isSuccessful) {
        response.body()
      } else {
        logger.error("Could not get item $name because: ${response.errorBody() ?: response.message()}")
        null
      }
    } catch (e: Exception) {
      logger.error("Could not get item $name because of an error", e)
      null
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("ItemRepository")
  }
}

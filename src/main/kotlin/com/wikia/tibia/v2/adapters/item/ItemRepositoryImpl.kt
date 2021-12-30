package com.wikia.tibia.v2.adapters.item

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.item.ItemRepository
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class ItemRepositoryImpl : ItemRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }

  private val itemsCache: LoadingCache<String, List<TibiaObject>?> by lazy {
    Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(1)
      .build(this::getItemsInternal)
  }

  private val itemCache: LoadingCache<String, TibiaObject?> by lazy {
    Caffeine.newBuilder()
      .expireAfterWrite(15, TimeUnit.MINUTES)
      .maximumSize(10_000)
      .build(this::getItemInternal)
  }

  override fun getItems(): List<TibiaObject> {
    return itemsCache.get("all") ?: emptyList()
  }

  override fun getItemNames(): List<String> {
    return client.getItemNames().execute()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of item names")
        throw RuntimeException("Could not get list of item names")
      }
  }

  override fun getItem(name: String): TibiaObject? {
    return itemCache.get(name)
  }

  override fun updateItem(item: TibiaObject, editSummary: String?): TibiaObject {
    return client.updateItems(editSummary, item).execute()
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not update item ${item.name}")
        throw RuntimeException("Could not update item ${item.name}")
      }
  }

  private fun getItemsInternal(key: String): List<TibiaObject> {
    return try {
      val response = client.getItems().execute()
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

  private fun getItemInternal(name: String): TibiaObject? {
    return try {
      val response = client.getItem(name).execute()
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

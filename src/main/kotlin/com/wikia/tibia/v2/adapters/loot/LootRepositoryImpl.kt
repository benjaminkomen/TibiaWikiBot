package com.wikia.tibia.v2.adapters.loot

import com.google.common.net.UrlEscapers
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.v2.adapters.tibiawiki.TibiaWikiApiClientFactory
import com.wikia.tibia.v2.domain.loot.LootRepository
import org.slf4j.LoggerFactory

class LootRepositoryImpl : LootRepository {

  private val client by lazy { TibiaWikiApiClientFactory.createClient() }

  override fun getLootList(): List<LootWrapper> {
    return client.getLootList().execute()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of loot")
        throw RuntimeException("Could not get list of loot")
      }
  }

  override fun getLootNames(): List<String> {
    return client.getLootListNames().execute()
      .takeIf { it.isSuccessful }
      ?.let { it.body() ?: emptyList() }
      ?: run {
        logger.error("Could not get list of loot names")
        throw RuntimeException("Could not get list of loot names")
      }
  }

  override fun getLoot(name: String): LootWrapper {
    return client.getLoot(UrlEscapers.urlFragmentEscaper().escape(name)).execute()
      .takeIf { it.isSuccessful }?.body()
      ?: run {
        logger.error("Could not get loot $name")
        throw RuntimeException("Could not get loot $name")
      }
  }

  companion object {
    private val logger = LoggerFactory.getLogger("LootRepository")
  }
}

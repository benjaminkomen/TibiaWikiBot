package com.wikia.tibia.v2.adapters.tibiawiki

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.objects.TibiaObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TibiaWikiApiClient {

  @GET("/api/creatures")
  suspend fun getCreatures(@Query("expand") expand: Boolean? = true): Response<List<Any>>

  @GET("/api/creatures")
  suspend fun getCreatureNames(@Query("expand") expand: Boolean? = false): Response<List<String>>

  @GET("/api/creatures/{name}")
  suspend fun getCreature(@Path("name") name: String): Response<Creature?>

  @PUT("/api/creatures")
  suspend fun updateCreature(@Header("X-WIKI-Edit-Summary") editSummary: String?, @Body newCreature: Creature): Response<Creature?>

  @GET("/api/items")
  suspend fun getItems(@Query("expand") expand: Boolean? = true): Response<List<Any>>

  @GET("/api/items")
  suspend fun getItemNames(@Query("expand") expand: Boolean? = false): Response<List<String>>

  @GET("/api/items/{name}")
  suspend fun getItem(@Path("name") name: String): Response<TibiaObject?>

  @PUT("/api/items")
  suspend fun updateItems(@Header("X-WIKI-Edit-Summary") editSummary: String?, @Body newItem: TibiaObject): Response<TibiaObject?>

  @GET("/api/v2/loot")
  suspend fun getLootList(@Query("expand") expand: Boolean? = true): Response<List<LootWrapper>>

  @GET("/api/v2/loot")
  suspend fun getLootListNames(@Query("expand") expand: Boolean? = false): Response<List<String>>

  @GET("/api/v2/loot/{name}")
  suspend fun getLoot(@Path("name") name: String): Response<LootWrapper?>
}

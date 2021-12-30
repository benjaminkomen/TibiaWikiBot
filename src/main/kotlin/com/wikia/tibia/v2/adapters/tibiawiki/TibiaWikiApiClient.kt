package com.wikia.tibia.v2.adapters.tibiawiki

import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.objects.TibiaObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TibiaWikiApiClient {

  @GET("/api/creatures")
  fun getCreatures(@Query("expand") expand: Boolean? = true): Call<List<Creature>>

  @GET("/api/creatures")
  fun getCreatureNames(@Query("expand") expand: Boolean? = false): Call<List<String>>

  @GET("/api/creatures/{name}")
  fun getCreature(@Path("name") name: String): Call<Creature?>

  @PUT("/api/creatures")
  fun updateCreature(@Header("X-WIKI-Edit-Summary") editSummary: String?, @Body newCreature: Creature): Call<Creature?>

  @GET("/api/items")
  fun getItems(@Query("expand") expand: Boolean? = true): Call<List<TibiaObject>>

  @GET("/api/items")
  fun getItemNames(@Query("expand") expand: Boolean? = false): Call<List<String>>

  @GET("/api/items/{name}")
  fun getItem(@Path("name") name: String): Call<TibiaObject?>

  @PUT("/api/items")
  fun updateItems(@Header("X-WIKI-Edit-Summary") editSummary: String?, @Body newItem: TibiaObject): Call<TibiaObject?>

  @GET("/api/v2/loot")
  fun getLootList(@Query("expand") expand: Boolean? = true): Call<List<LootWrapper>>

  @GET("/api/v2/loot")
  fun getLootListNames(@Query("expand") expand: Boolean? = false): Call<List<String>>

  @GET("/api/v2/loot/{name}")
  fun getLoot(@Path("name") name: String): Call<LootWrapper?>
}

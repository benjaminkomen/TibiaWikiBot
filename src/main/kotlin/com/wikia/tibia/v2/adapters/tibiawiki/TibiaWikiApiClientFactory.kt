package com.wikia.tibia.v2.adapters.tibiawiki

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object TibiaWikiApiClientFactory {

  private val defaultOkHttpClient = OkHttpClient().newBuilder()
    .readTimeout(2, TimeUnit.MINUTES)
    .connectTimeout(1, TimeUnit.MINUTES)
    .build()
  private val defaultObjectMapper = objectMapper()

  fun createClient(): TibiaWikiApiClient {
    return Retrofit.Builder()
//      .baseUrl("http://localhost:8080")
      .baseUrl("https://tibiawiki.dev")
      .client(defaultOkHttpClient)
      .addConverterFactory(JacksonConverterFactory.create(defaultObjectMapper))
      .build()
      .create(TibiaWikiApiClient::class.java)
  }

  private fun objectMapper(): ObjectMapper {
    return ObjectMapper()
      .findAndRegisterModules()
      .registerModule(KotlinModule.Builder().build())
      .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
      .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
  }
}

package com.wikia.tibia.jackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.wikia.tibia.exceptions.JacksonParsingException
import org.slf4j.LoggerFactory
import java.io.IOException

object Parser {

    private val logger = LoggerFactory.getLogger(Parser::class.java)
    private val defaultObjectMapper = jacksonObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    fun <T> parse(type: Class<T>, json: String?): T? {
        return parse(type, defaultObjectMapper, json)
    }

    fun <T> parse(type: Class<T>, mapper: ObjectMapper, json: String?): T? {
        if (json == null || json == "") {
            return null
        }
        val javaType = mapper.typeFactory.constructType(type)
        return try {
            mapper.readValue(json, javaType)
        } catch (e: IOException) {
            throw JacksonParsingException(e)
        }
    }

    fun <T> list(type: Class<T>, json: String?): List<T> {
        return list(type, defaultObjectMapper, json)
    }

    fun <T> list(type: Class<T>, mapper: ObjectMapper, json: String?): List<T> {
        if (json == null || "" == json) {
            return emptyList()
        }
        val collectionType = mapper.typeFactory.constructCollectionType(MutableList::class.java, type)
        return try {
            mapper.readValue(json, collectionType)
        } catch (e: IOException) {
            throw JacksonParsingException(e)
        }
    }

    /**
     * Alternative implementation of list() where the json list is read one-by-one, which makes
     * it more robust against jsonparsingexceptions
     */
    fun <T : Any> listOneByOne(type: Class<T>, mapper: ObjectMapper = defaultObjectMapper, json: String?, limit: Int = Int.MAX_VALUE): List<T> {
        if (json == null || "" == json) {
            return emptyList()
        }
        return try {
            val jsonAsNode: JsonNode = mapper.readTree(json)
            if (jsonAsNode.isArray) {
                (0 until jsonAsNode.size()).asSequence()
                        .map { jsonAsNode.get(it) }
                        .mapNotNull { parseJsonToWikiObject(mapper, it, type) }
                        .take(limit)
                        .toList()
            } else {
                logger.error("Retrieved json is not a json array, cannot parse to list.")
                emptyList()
            }
        } catch (e: IOException) {
            throw JacksonParsingException(e)
        }
    }

    private fun <T : Any> parseJsonToWikiObject(mapper: ObjectMapper, it: JsonNode?, type: Class<T>): T? {
        return try {
            mapper.treeToValue(it, type)
        } catch (e: JsonProcessingException) {
            logger.error("Unable to construct object of type '$type' from the following json: '$it' because of error: $e")
            null
        }
    }

    fun <T> json(someObject: T, mapper: ObjectMapper = defaultObjectMapper): String {
        return try {
            mapper.writeValueAsString(someObject)
        } catch (e: JsonProcessingException) {
            logger.error("Unable to serialise object to json", e)
            ""
        }
    }
}
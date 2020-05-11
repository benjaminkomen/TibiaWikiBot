package com.wikia.tibia.jackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.wikia.tibia.exceptions.JacksonParsingException
import com.wikia.tibia.objects.WikiObject
import org.json.JSONObject
import org.slf4j.LoggerFactory
import java.io.IOException

object Parser {

    private val logger = LoggerFactory.getLogger(Parser::class.java)
    private val defaultObjectMapper: ObjectMapper = 
            ObjectMapper()
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
    // TODO fix this
    fun <T> listOneByOne(type: Class<T>, json: String?, limit: Int?): List<T> {
//        return listOneByOne(type, defaultObjectMapper, json, limit)
        return list(type, json)
    }

    // TODO fix this
//    private fun <T> listOneByOne(type: Class<T>, mapper: ObjectMapper, json: String?, limit: Int?): List<T> {
//        if (json == null || "" == json) {
//            return emptyList()
//        }
//        return try {
//            val jsonAsNode = mapper.readTree(json)
//            if (jsonAsNode.isArray) {
//                (0 until jsonAsNode.size()).toList()
//                        .map { jsonAsNode[it] }
//                        .mapNotNull { jn: JsonNode? -> return parseJsonToWrappedWikiObject(mapper, jn, type) }
//                        .toList()
////                        .take{limit ?: Int.MAX_VALUE}
//            } else {
//                logger.error("Retrieved json is not a json array, cannot parse to list.")
//                emptyList()
//            }
//        } catch (e: IOException) {
//            throw JacksonParsingException(e)
//        }
//    }

    fun <T> json(someObject: T, mapper: ObjectMapper? = defaultObjectMapper): String {
        return try {
            mapper!!.writeValueAsString(someObject)
        } catch (e: JsonProcessingException) {
            logger.error("Unable to serialise object to json", e)
            ""
        }
    }
}
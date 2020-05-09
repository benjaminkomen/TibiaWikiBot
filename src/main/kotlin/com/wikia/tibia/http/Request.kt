package com.wikia.tibia.http

import com.wikia.tibia.exceptions.ResponseException
import io.vavr.control.Try
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Request {

    private val httpClient: HttpClient = HttpClient.newHttpClient()

    private fun invoke(request: HttpRequest): HttpResponse<String>? {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        } catch (e: IOException) {
            logger.error("Error while invoking requested URI", e)
        } catch (e: InterruptedException) {
            logger.error("Error while invoking requested URI", e)
        }
        return null
    }

    fun get(location: String): Try<String> {
        return get(URI.create(location))
    }

    fun get(location: URI): Try<String> {
        val request = HttpRequest.newBuilder()
                .uri(location)
                .GET()
                .build()
        val response = invoke(request)
        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(ResponseException(response.body()))
        }
        return if (response != null && response.body() != null) {
            Try.success(response.body())
        } else {
            Try.success("")
        }
    }

    fun put(location: String, jsonBody: String, header: Header?, dryRun: Boolean): Try<String> {
        return this.put(URI.create(location), jsonBody, header, dryRun)
    }

    fun put(location: URI, jsonBody: String, header: Header?, dryRun: Boolean): Try<String> {
        val requestBuilder = HttpRequest.newBuilder()
                .uri(location)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
        if (header != null) {
            requestBuilder.header(header.name, header.value)
        }
        val request = requestBuilder.build()
        if (dryRun) {
            logger.info("Not actually doing request due to dry run.")
            return Try.success("")
        }
        val response = invoke(request)
        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(ResponseException(response.body()))
        }
        return if (response != null && response.body() != null) {
            Try.success(response.body())
        } else {
            Try.success("")
        }
    }

    fun post(location: URI, jsonBody: String): Try<String> {
        val request = HttpRequest.newBuilder()
                .uri(location)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build()
        val response = invoke(request)
        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(ResponseException(response.body()))
        }
        return if (response != null && response.body() != null) {
            Try.success(response.body())
        } else {
            Try.success("")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Request::class.java)
    }
}
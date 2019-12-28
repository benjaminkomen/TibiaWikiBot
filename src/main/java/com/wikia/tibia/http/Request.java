package com.wikia.tibia.http;

import com.wikia.tibia.exceptions.ResponseException;
import com.wikia.tibia.jackson.Parser;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {

    private static final Logger LOG = LoggerFactory.getLogger(Request.class);
    private HttpClient httpClient;

    public Request() {
        httpClient = HttpClient.newHttpClient();
    }

    private HttpResponse<String> invoke(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOG.error("Error while invoking requested URI", e);
        }
        return null;
    }

    public Try<String> get(String location) {
        return get(URI.create(location));
    }

    public Try<String> get(URI location) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .GET()
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(new ResponseException(response.body()));
        }

        if (response != null && response.body() != null) {
            return Try.success(response.body());
        } else {
            return Try.success("");
        }
    }

    public Try<String> put(String location, Object body, Header header, boolean dryRun) {
        return this.put(location, Parser.json(body), header, dryRun);
    }

    public Try<String> put(String location, Object body, boolean dryRun) {
        return this.put(location, Parser.json(body), null, dryRun);
    }

    public Try<String> put(String location, String jsonBody, Header header, boolean dryRun) {
        return this.put(URI.create(location), jsonBody, header, dryRun);
    }

    public Try<String> put(URI location, String jsonBody, Header header, boolean dryRun) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(location)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json");

        if (header != null) {
            requestBuilder.header(header.getName(), header.getValue());
        }

        final HttpRequest request = requestBuilder.build();

        if (dryRun) {
            LOG.info("Not actually doing request due to dry run.");
            return Try.success("");
        }

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(new ResponseException(response.body()));
        }

        if (response != null && response.body() != null) {
            return Try.success(response.body());
        } else {
            return Try.success("");
        }
    }

    public Try<String> post(URI location, String jsonBody) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(new ResponseException(response.body()));
        }

        if (response != null && response.body() != null) {
            return Try.success(response.body());
        } else {
            return Try.success("");
        }
    }

    public Try<String> delete(URI location) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .DELETE()
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.statusCode() >= 400) {
            // we receive an error response
            return Try.failure(new ResponseException(response.body()));
        }

        if (response != null && response.body() != null) {
            return Try.success(response.body());
        } else {
            return Try.success("");
        }
    }
}

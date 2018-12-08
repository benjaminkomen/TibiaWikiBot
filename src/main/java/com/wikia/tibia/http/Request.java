package com.wikia.tibia.http;

import com.wikia.tibia.jackson.Parser;
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

    public String get(String location) {
        return get(URI.create(location));
    }

    public String get(URI location) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .GET()
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.body() != null) {
            return response.body();
        } else {
            return "";
        }
    }

    public String put(String location, Object body, Header header) {
        return this.put(location, Parser.json(body), header);
    }

    public String put(String location, Object body) {
        return this.put(location, Parser.json(body), null);
    }

    public String put(String location, String jsonBody, Header header) {
        return this.put(URI.create(location), jsonBody, header);
    }

    public String put(URI location, String jsonBody, Header header) {
        final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(location)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json");

        if (header != null) {
            requestBuilder.header(header.getName(), header.getValue());
        }

        final HttpRequest request = requestBuilder.build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.body() != null) {
            return response.body();
        } else {
            return "";
        }
    }

    public String post(URI location, String jsonBody) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.body() != null) {
            return response.body();
        } else {
            return "";
        }
    }

    public String delete(URI location) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .DELETE()
                .build();

        final HttpResponse<String> response = invoke(request);

        if (response != null && response.body() != null) {
            return response.body();
        } else {
            return "";
        }
    }
}

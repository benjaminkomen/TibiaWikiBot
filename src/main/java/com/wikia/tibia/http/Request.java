package com.wikia.tibia.http;

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

    public void put(URI location, Object body) {
//        final HttpRequest request = HttpRequest.newBuilder()
//                .uri(location)
//                .PUT(HttpRequest.BodyPublisher)
//                .build();
    }

    public void put(URI location, String jsonBody) {
        //
    }

    public String post(URI location, String jsonBody) {
        return "";
    }

    public void delete(URI location) {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(location)
                .DELETE()
                .build();
    }
}

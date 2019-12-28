package com.wikia.tibia.exceptions;

public class ResponseException extends RuntimeException {

    public ResponseException(String message) {
        super(message);
    }
}

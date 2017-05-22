package com.wikia.tibia.objects;

public class Sound {

    private String value;

    public Sound(String value) {
        this.value = sanitize(value);
    }

    // TODO, sanitize input from quotes and commas or something
    private String sanitize(String value) {
        return value;
    }

    @Override
    public String toString() {
        // TODO, return value as it was again
        return value;
    }
}

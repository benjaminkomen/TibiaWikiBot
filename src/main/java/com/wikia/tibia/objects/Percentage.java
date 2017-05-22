package com.wikia.tibia.objects;

public class Percentage {

    private int value;

    public Percentage(String value) {
        this.value = sanitize(value);
    }

    // TODO sanitize input, strip question marks and/or percent singes
    private int sanitize(String value) {
        return new Integer(value);
    }
}

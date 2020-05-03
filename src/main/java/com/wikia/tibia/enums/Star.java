package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Star {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int number;

    Star(int number) {
        this.number = number;
    }

    @JsonValue
    public int getNumber() {
        return number;
    }
}
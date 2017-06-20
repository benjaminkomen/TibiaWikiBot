package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum City {

    AB_DENDRIEL("Ab'Dendriel"),
    ANKRAHMUN("Ankrahmun"),
    CARLIN("Carlin"),
    CORMAYA("Cormaya"),
    DARASHIA("Darashia"),
    DAWNPORT("Dawnport"),
    EDRON("Edron"),
    FARMINE("Farmine"),
    GRAY_BEACH("Gray Beach"),
    KAZORDOON("Kazordoon"),
    LIBERTY_BAY("Liberty Bay"),
    PORT_HOPE("Port Hope"),
    RATHLETON("Rathleton"),
    ROOKGAARD("Rookgaard"),
    SVARGROND("Svargrond"),
    THAIS("Thais"),
    VENORE("Venore"),
    YALAHAR("Yalahar");

    private String description;

    City(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}

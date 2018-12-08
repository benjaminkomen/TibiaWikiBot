package com.wikia.tibia.objects;

import com.wikia.tibia.enums.City;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Street extends WikiObject {

    private final City city;
    private final City city2;
    private final String map;
    private final String floor;

    private Street() {
        this.city = null;
        this.city2 = null;
        this.map = null;
        this.floor = null;
    }

    @Builder
    private Street(String name, String implemented, String notes, City city, City city2, String map, String floor) {
        super(name, null, null, null, implemented, notes, null, null);
        this.city = city;
        this.city2 = city2;
        this.map = map;
        this.floor = floor;
    }
}
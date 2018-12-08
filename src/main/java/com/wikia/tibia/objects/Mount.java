package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Mount extends WikiObject {

    private final Integer speed;
    private final String tamingMethod;
    private final YesNo bought;
    private final Integer price; // unit is Tibia Coins
    private final String achievement; // this could link to Achievement
    private final Integer lightradius;
    private final Integer lightcolor;
    private final String artwork;

    private Mount() {
        this.speed = null;
        this.tamingMethod = null;
        this.bought = null;
        this.price = null;
        this.achievement = null;
        this.lightradius = null;
        this.lightcolor = null;
        this.artwork = null;
    }

    @Builder
    private Mount(String name, String implemented, String notes, String history, Status status, Integer speed,
                  String tamingMethod, YesNo bought, Integer price, String achievement, Integer lightradius,
                  Integer lightcolor, String artwork) {
        super(name, null, null, null, implemented, notes, history, status);
        this.speed = speed;
        this.tamingMethod = tamingMethod;
        this.bought = bought;
        this.price = price;
        this.achievement = achievement;
        this.lightradius = lightradius;
        this.lightcolor = lightcolor;
        this.artwork = artwork;
    }

    @JsonGetter("taming_method")
    private String getTamingMethod() {
        return tamingMethod;
    }
}
package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mount extends WikiObject {

    private Integer speed;
    private String tamingMethod;
    private YesNo bought;
    private Integer price; // unit is Tibia Coins
    private String achievement; // this could link to Achievement
    private Integer lightradius;
    private Integer lightcolor;
    private String artwork;

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

    @Override
    public void setDefaultValues() {
        // TODO implement this method
    }
}
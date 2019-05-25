package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.enums.Star;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HuntingPlace extends WikiObject {

    private String image;
    private City city;
    private String location;
    private String vocation;
    private String lvlknights; // Integer?
    private String lvlpaladins; // Integer?
    private String lvlmages; // Integer?
    private String skknights; // Integer?
    private String skpaladins; // Integer?
    private String skmages; // Integer?
    private String defknights; // Integer?
    private String defpaladins; // Integer?
    private String defmages; // Integer?
    @JsonManagedReference
    private List<HuntingPlaceSkills> lowerlevels;
    private String loot;
    private Star lootstar;
    private String exp;
    private Star expstar;
    private String bestloot;
    private String bestloot2;
    private String bestloot3;
    private String bestloot4;
    private String bestloot5;
    private String map;
    private String map2;
    private String map3;
    private String map4;

    @Builder
    private HuntingPlace(String name, String implemented, String image, City city, String location, String vocation,
                         String lvlknights, String lvlpaladins, String lvlmages, String skknights, String skpaladins,
                         String skmages, String defknights, String defpaladins, String defmages,
                         List<HuntingPlaceSkills> lowerlevels, String loot, Star lootstar, String exp, Star expstar,
                         String bestloot, String bestloot2, String bestloot3, String bestloot4, String bestloot5,
                         String map, String map2, String map3, String map4) {
        super(name, null, null, null, implemented, null, null, null);
        this.image = image;
        this.city = city;
        this.location = location;
        this.vocation = vocation;
        this.lvlknights = lvlknights;
        this.lvlpaladins = lvlpaladins;
        this.lvlmages = lvlmages;
        this.skknights = skknights;
        this.skpaladins = skpaladins;
        this.skmages = skmages;
        this.defknights = defknights;
        this.defpaladins = defpaladins;
        this.defmages = defmages;
        this.lowerlevels = lowerlevels;
        this.loot = loot;
        this.lootstar = lootstar;
        this.exp = exp;
        this.expstar = expstar;
        this.bestloot = bestloot;
        this.bestloot2 = bestloot2;
        this.bestloot3 = bestloot3;
        this.bestloot4 = bestloot4;
        this.bestloot5 = bestloot5;
        this.map = map;
        this.map2 = map2;
        this.map3 = map3;
        this.map4 = map4;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(image)) {
            image = "None";
        }

        if (ObjectUtils.isEmpty(location)) {
            location = "Unknown";
        }

        if (ObjectUtils.isEmpty(lvlknights)) {
            lvlknights = "?";
        }

        if (ObjectUtils.isEmpty(lvlpaladins)) {
            lvlpaladins = "?";
        }

        if (ObjectUtils.isEmpty(lvlmages)) {
            lvlmages = "?";
        }

        if (ObjectUtils.isEmpty(exp)) {
            exp = "Unknown";
        }

        if (ObjectUtils.isEmpty(loot)) {
            loot = "Unknown";
        }
    }
}

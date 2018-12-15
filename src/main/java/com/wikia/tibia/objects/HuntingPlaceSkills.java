package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HuntingPlaceSkills {

    private final String areaname;
    private final String lvlknights;
    private final String lvlpaladins;
    private final String lvlmages;
    private final String skknights;
    private final String skpaladins;
    private final String skmages;
    private final String defknights;
    private final String defpaladins;
    private final String defmages;
    @JsonBackReference
    private HuntingPlace huntingPlace;

    private HuntingPlaceSkills() {
        this.areaname = null;
        this.lvlknights = null;
        this.lvlpaladins = null;
        this.lvlmages = null;
        this.skknights = null;
        this.skpaladins = null;
        this.skmages = null;
        this.defknights = null;
        this.defpaladins = null;
        this.defmages = null;
        this.huntingPlace = null;
    }
}

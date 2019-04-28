package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HuntingPlaceSkills {

    private String areaname;
    private String lvlknights;
    private String lvlpaladins;
    private String lvlmages;
    private String skknights;
    private String skpaladins;
    private String skmages;
    private String defknights;
    private String defpaladins;
    private String defmages;
    @JsonBackReference
    private HuntingPlace huntingPlace;
}

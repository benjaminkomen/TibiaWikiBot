package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.enums.Gender;
import com.wikia.tibia.enums.YesNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties({"templateType"})
@Getter
@Setter
@NoArgsConstructor
public class NPC extends WikiObject {

    private String job;
    private String job2;
    private String job3;
    private String job4;
    private String job5;
    private String job6;
    private String location;
    private City city;
    private City city2;
    private String street;
    private Double posx;
    private Double posy;
    private Integer posz;
    private Double posx2;
    private Double posy2;
    private Integer posz2;
    private Double posx3;
    private Double posy3;
    private Integer posz3;
    private Double posx4;
    private Double posy4;
    private Integer posz4;
    private Double posx5;
    private Double posy5;
    private Integer posz5;
    private Gender gender;
    private String race;
    private YesNo buysell;
    private String buys;
    private String sells;
    private List<String> sounds;

    @Override
    public List<String> fieldOrder() {
        return Collections.emptyList();
    }
}
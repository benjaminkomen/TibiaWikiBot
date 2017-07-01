package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.enums.Gender;
import com.wikia.tibia.enums.YesNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties({ "objectType" })
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
    private String posx;
    private String posy;
    private String posz;
    private String posx2;
    private String posy2;
    private String posz2;
    private String posx3;
    private String posy3;
    private String posz3;
    private String posx4;
    private String posy4;
    private String posz4;
    private String posx5;
    private String posy5;
    private String posz5;
    private Gender gender;
    private String race;
    private YesNo buysell;
    private String buys;
    private String sells;
    private List<String> sounds;
}
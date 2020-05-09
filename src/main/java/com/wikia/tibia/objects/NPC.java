package com.wikia.tibia.objects;

import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
    private BigDecimal posx;
    private BigDecimal posy;
    private Integer posz;
    private BigDecimal posx2;
    private BigDecimal posy2;
    private Integer posz2;
    private BigDecimal posx3;
    private BigDecimal posy3;
    private Integer posz3;
    private BigDecimal posx4;
    private BigDecimal posy4;
    private Integer posz4;
    private BigDecimal posx5;
    private BigDecimal posy5;
    private Integer posz5;
    private Gender gender;
    private String race;
    private YesNo buysell;
    private String buys;
    private String sells;
    private List<String> sounds;

    @Builder
    private NPC(String name, String actualname, String implemented, String notes, String history, Status status,
                String job, String job2, String job3, String job4, String job5, String job6, String location, City city,
                City city2, String street, BigDecimal posx, BigDecimal posy, Integer posz, BigDecimal posx2, BigDecimal posy2,
                Integer posz2, BigDecimal posx3, BigDecimal posy3, Integer posz3, BigDecimal posx4, BigDecimal posy4, Integer posz4,
                BigDecimal posx5, BigDecimal posy5, Integer posz5, Gender gender, String race, YesNo buysell, String buys,
                String sells, List<String> sounds) {
        super(name, null, actualname, null, implemented, notes, history, status);
        this.job = job;
        this.job2 = job2;
        this.job3 = job3;
        this.job4 = job4;
        this.job5 = job5;
        this.job6 = job6;
        this.location = location;
        this.city = city;
        this.city2 = city2;
        this.street = street;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
        this.posx2 = posx2;
        this.posy2 = posy2;
        this.posz2 = posz2;
        this.posx3 = posx3;
        this.posy3 = posy3;
        this.posz3 = posz3;
        this.posx4 = posx4;
        this.posy4 = posy4;
        this.posz4 = posz4;
        this.posx5 = posx5;
        this.posy5 = posy5;
        this.posz5 = posz5;
        this.gender = gender;
        this.race = race;
        this.buysell = buysell;
        this.buys = buys;
        this.sells = sells;
        this.sounds = sounds;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(location)) {
            location = "?";
        }

        if (ObjectUtils.isEmpty(race)) {
            race = "?";
        }

        if (ObjectUtils.isEmpty(buysell)) {
            buysell = YesNo.UNKNOWN;
        }
    }
}
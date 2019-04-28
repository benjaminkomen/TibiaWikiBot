package com.wikia.tibia.objects;

import com.wikia.tibia.enums.BuildingType;
import com.wikia.tibia.enums.City;
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
public class Building extends WikiObject {

    private BuildingType type;
    private String location;
    private String posx;
    private String posy;
    private String posz;
    private String street;
    private String street2;
    private String street3;
    private String street4;
    private String street5;
    private Integer houseid;
    private Integer size;
    private Integer beds;
    private Integer rent;
    private YesNo ownable;
    private City city;
    private Integer openwindows;
    private Integer floors;
    private Integer rooms;
    private String furnishings;
    private String image;

    @Builder
    private Building(String name, String implemented, String notes, String history, Status status, BuildingType type, String location,
                     String posx, String posy, String posz, String street, String street2, String street3, String street4,
                     String street5, Integer houseid, Integer size, Integer beds, Integer rent, YesNo ownable, City city,
                     Integer openwindows, Integer floors, Integer rooms, String furnishings, String image) {
        super(name, null, null, null, implemented, notes, history, status);
        this.type = type;
        this.location = location;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
        this.street = street;
        this.street2 = street2;
        this.street3 = street3;
        this.street4 = street4;
        this.street5 = street5;
        this.houseid = houseid;
        this.size = size;
        this.beds = beds;
        this.rent = rent;
        this.ownable = ownable;
        this.city = city;
        this.openwindows = openwindows;
        this.floors = floors;
        this.rooms = rooms;
        this.furnishings = furnishings;
        this.image = image;
    }

    @Override
    public void setDefaultValues() {
        // TODO implement this method
    }
}
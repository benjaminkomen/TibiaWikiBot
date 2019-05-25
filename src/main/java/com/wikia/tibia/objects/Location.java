package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Location extends WikiObject {

    private String ruler;
    private String population;
    private String near;
    private String organization;
    private String map;
    private String map2;
    private String map3;
    private String map4;
    private String map5;
    private String map6;
    private YesNo links;

    @Builder
    private Location(String name, String implemented, Status status, String ruler, String population, String near,
                     String organization, String map, String map2, String map3, String map4, String map5, String map6,
                     YesNo links) {
        super(name, null, null, null, implemented, null, null, status);
        this.ruler = ruler;
        this.population = population;
        this.near = near;
        this.organization = organization;
        this.map = map;
        this.map2 = map2;
        this.map3 = map3;
        this.map4 = map4;
        this.map5 = map5;
        this.map6 = map6;
        this.links = links;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(near)) {
            near = "?";
        }

        if (ObjectUtils.isEmpty(links)) {
            links = YesNo.UNKNOWN;
        }
    }
}

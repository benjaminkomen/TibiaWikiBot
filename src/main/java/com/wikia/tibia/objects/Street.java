package com.wikia.tibia.objects;

import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Street extends WikiObject {

    private City city;
    private City city2;
    private String map;
    private String floor;

    @Builder
    private Street(String name, String implemented, String notes, City city, City city2, String map, String floor) {
        super(name, null, null, null, implemented, notes, null, null);
        this.city = city;
        this.city2 = city2;
        this.map = map;
        this.floor = floor;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }
    }
}
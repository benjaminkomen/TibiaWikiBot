package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.HuntingPlaceGateway;
import com.wikia.tibia.objects.HuntingPlace;

public class HuntingPlaceRepository extends WikiObjectRepository {

    public HuntingPlaceRepository() {
        super(HuntingPlace.class, new HuntingPlaceGateway());
    }
}

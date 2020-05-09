package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.HuntingPlace;

public class HuntingPlaceGateway extends WikiObjectGateway<HuntingPlace> {

    public HuntingPlaceGateway() {
        super(Contracts.HUNTING_PLACES);
    }
}

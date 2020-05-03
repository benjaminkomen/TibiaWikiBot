package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Location;

public class LocationGateway extends WikiObjectGateway<Location> {

    public LocationGateway() {
        super(Contracts.LOCATIONS);
    }
}

package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.LocationGateway;
import com.wikia.tibia.objects.Location;

public class LocationRepository extends WikiObjectRepository {

    public LocationRepository() {
        super(Location.class, new LocationGateway());
    }
}

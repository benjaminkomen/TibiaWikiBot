package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.BuildingGateway;
import com.wikia.tibia.objects.Building;

public class BuildingRepository extends WikiObjectRepository {

    public BuildingRepository() {
        super(Building.class, new BuildingGateway());
    }
}

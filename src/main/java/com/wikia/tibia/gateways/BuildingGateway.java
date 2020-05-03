package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Building;

public class BuildingGateway extends WikiObjectGateway<Building> {

    public BuildingGateway() {
        super(Contracts.BUILDINGS);
    }
}

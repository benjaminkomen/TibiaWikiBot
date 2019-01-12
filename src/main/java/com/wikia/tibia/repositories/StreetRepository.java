package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.StreetGateway;
import com.wikia.tibia.objects.Street;

public class StreetRepository extends WikiObjectRepository {

    public StreetRepository() {
        super(Street.class, new StreetGateway());
    }
}

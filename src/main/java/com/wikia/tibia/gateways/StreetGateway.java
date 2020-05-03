package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Street;

public class StreetGateway extends WikiObjectGateway<Street> {

    public StreetGateway() {
        super(Contracts.STREETS);
    }
}

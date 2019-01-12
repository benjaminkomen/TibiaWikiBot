package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.OutfitGateway;
import com.wikia.tibia.objects.Outfit;

public class OutfitRepository extends WikiObjectRepository {

    public OutfitRepository() {
        super(Outfit.class, new OutfitGateway());
    }
}

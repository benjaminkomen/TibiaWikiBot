package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.LootGateway;
import com.wikia.tibia.objects.LootWrapper;

public class LootRepository extends WikiObjectRepository {

    public LootRepository() {
        super(LootWrapper.class, new LootGateway());
    }
}

package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.LootGateway;
import com.wikia.tibia.objects.Loot;

public class LootRepository extends WikiObjectRepository {

    public LootRepository() {
        super(Loot.class, new LootGateway());
    }
}

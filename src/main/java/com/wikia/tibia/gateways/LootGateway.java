package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;

public class LootGateway extends WikiObjectGateway {

    public LootGateway() {
        super(Contracts.LOOT_STATISTICS_V2);
    }
}
package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;

// TODO does this even need to extend WikiObjectGateway, as Loot does not extend WikiObject ?
public class LootGateway extends WikiObjectGateway {

    public LootGateway() {
        super(Contracts.LOOT_STATISTICS_V2);
    }
}
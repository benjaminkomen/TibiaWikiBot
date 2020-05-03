package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Corpse;

public class CorpseGateway extends WikiObjectGateway<Corpse> {

    public CorpseGateway() {
        super(Contracts.CORPSES);
    }
}

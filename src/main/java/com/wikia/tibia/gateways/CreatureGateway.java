package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Creature;

public class CreatureGateway extends WikiObjectGateway<Creature> {

    public CreatureGateway() {
        super(Contracts.CREATURES);
    }
}
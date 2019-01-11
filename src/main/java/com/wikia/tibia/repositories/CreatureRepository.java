package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.CreatureGateway;
import com.wikia.tibia.objects.Creature;

public class CreatureRepository extends WikiObjectRepository {

    public CreatureRepository() {
        super(Creature.class, new CreatureGateway());
    }
}

package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.CorpseGateway;
import com.wikia.tibia.objects.Corpse;

public class CorpseRepository extends WikiObjectRepository {

    public CorpseRepository() {
        super(Corpse.class, new CorpseGateway());
    }
}

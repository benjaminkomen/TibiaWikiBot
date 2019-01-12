package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.MissileGateway;
import com.wikia.tibia.objects.Missile;

public class MissileRepository extends WikiObjectRepository {

    public MissileRepository() {
        super(Missile.class, new MissileGateway());
    }
}

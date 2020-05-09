package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Missile;

public class MissileGateway extends WikiObjectGateway<Missile> {

    public MissileGateway() {
        super(Contracts.MISSILES);
    }
}

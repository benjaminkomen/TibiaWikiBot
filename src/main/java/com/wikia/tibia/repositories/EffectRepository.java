package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.EffectGateway;
import com.wikia.tibia.objects.Effect;

public class EffectRepository extends WikiObjectRepository {

    public EffectRepository() {
        super(Effect.class, new EffectGateway());
    }
}

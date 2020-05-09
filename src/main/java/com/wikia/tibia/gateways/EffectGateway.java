package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Effect;

public class EffectGateway extends WikiObjectGateway<Effect> {

    public EffectGateway() {
        super(Contracts.EFFECTS);
    }
}

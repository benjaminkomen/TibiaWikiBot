package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Spell;

public class SpellGateway extends WikiObjectGateway<Spell> {

    public SpellGateway() {
        super(Contracts.SPELLS);
    }
}

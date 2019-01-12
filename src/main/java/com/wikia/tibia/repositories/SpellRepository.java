package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.SpellGateway;
import com.wikia.tibia.objects.Spell;

public class SpellRepository extends WikiObjectRepository {

    public SpellRepository() {
        super(Spell.class, new SpellGateway());
    }
}

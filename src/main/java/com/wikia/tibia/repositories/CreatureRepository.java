package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.CreatureGateway;
import com.wikia.tibia.jackson.Parser;
import com.wikia.tibia.objects.Creature;

import java.util.List;

public class CreatureRepository {

    private CreatureGateway creatureGateway;

    public CreatureRepository() {
        creatureGateway = new CreatureGateway();
    }

    public List<Creature> getCreatures() {
        return Parser.listOneByOne(Creature.class, creatureGateway.getCreatures(true));
    }

    public List<String> getCreaturesList() {
        return Parser.list(String.class, creatureGateway.getCreatures(false));
    }
}

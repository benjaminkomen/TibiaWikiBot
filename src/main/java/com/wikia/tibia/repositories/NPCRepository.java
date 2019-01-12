package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.NPCGateway;
import com.wikia.tibia.objects.NPC;

public class NPCRepository extends WikiObjectRepository {

    public NPCRepository() {
        super(NPC.class, new NPCGateway());
    }
}

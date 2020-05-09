package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.NPC;

public class NPCGateway extends WikiObjectGateway<NPC> {

    public NPCGateway() {
        super(Contracts.NPCS);
    }
}

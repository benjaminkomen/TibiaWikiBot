package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.NPC

class NPCRepository : WikiObjectRepository<NPC>(
    wikiObjectClass = NPC::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.NPCS)
)

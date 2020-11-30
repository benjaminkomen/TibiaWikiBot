package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Creature

class CreatureRepository : WikiObjectRepository<Creature>(
    wikiObjectClass = Creature::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.CREATURES)
)

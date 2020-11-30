package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Spell

class SpellRepository : WikiObjectRepository<Spell>(
    wikiObjectClass = Spell::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.SPELLS)
)

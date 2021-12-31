package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Corpse

class CorpseRepository : WikiObjectRepository<Corpse>(
  wikiObjectClass = Corpse::class.java,
  wikiObjectGateway = WikiObjectGateway(Contract.CORPSES)
)

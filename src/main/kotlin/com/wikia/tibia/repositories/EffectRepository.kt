package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Effect

class EffectRepository : WikiObjectRepository<Effect>(
  wikiObjectClass = Effect::class.java,
  wikiObjectGateway = WikiObjectGateway(Contract.EFFECTS)
)

package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Missile

class MissileRepository : WikiObjectRepository<Missile>(
    wikiObjectClass = Missile::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.MISSILES)
)

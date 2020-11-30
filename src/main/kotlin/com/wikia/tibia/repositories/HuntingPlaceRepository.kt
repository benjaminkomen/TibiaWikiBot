package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.HuntingPlace

class HuntingPlaceRepository : WikiObjectRepository<HuntingPlace>(
    wikiObjectClass = HuntingPlace::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.HUNTING_PLACES)
)

package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Street

class StreetRepository : WikiObjectRepository<Street>(
        wikiObjectClass = Street::class.java,
        wikiObjectGateway = WikiObjectGateway(Contract.STREETS)
)
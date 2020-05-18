package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Location

class LocationRepository : WikiObjectRepository<Location>(
        wikiObjectClass = Location::class.java,
        wikiObjectGateway = WikiObjectGateway(Contract.LOCATIONS)
)
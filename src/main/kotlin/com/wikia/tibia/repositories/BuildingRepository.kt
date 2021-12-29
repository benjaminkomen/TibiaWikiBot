package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Building

class BuildingRepository : WikiObjectRepository<Building>(
  wikiObjectClass = Building::class.java,
  wikiObjectGateway = WikiObjectGateway(Contract.BUILDINGS)
)

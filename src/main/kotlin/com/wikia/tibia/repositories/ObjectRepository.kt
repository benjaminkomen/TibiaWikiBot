package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.TibiaObject

class ObjectRepository : WikiObjectRepository<TibiaObject>(
        wikiObjectClass = TibiaObject::class.java,
        wikiObjectGateway = WikiObjectGateway(Contract.OBJECTS)
)
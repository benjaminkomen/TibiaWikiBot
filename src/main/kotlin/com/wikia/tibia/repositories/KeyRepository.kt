package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Key

class KeyRepository : WikiObjectRepository<Key>(
    wikiObjectClass = Key::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.KEYS)
)

package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Item

class ItemRepository : WikiObjectRepository<Item>(
    wikiObjectClass = Item::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.ITEMS)
)

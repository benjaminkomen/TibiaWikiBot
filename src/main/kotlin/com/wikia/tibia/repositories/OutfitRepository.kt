package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Outfit

class OutfitRepository : WikiObjectRepository<Outfit>(
    wikiObjectClass = Outfit::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.OUTFITS)
)

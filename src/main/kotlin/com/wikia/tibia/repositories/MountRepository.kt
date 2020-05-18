package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Mount

class MountRepository : WikiObjectRepository<Mount>(
        wikiObjectClass = Mount::class.java,
        wikiObjectGateway = WikiObjectGateway(Contract.MOUNTS)
)
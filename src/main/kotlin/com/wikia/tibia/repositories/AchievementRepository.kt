package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Achievement

class AchievementRepository : WikiObjectRepository<Achievement>(
    wikiObjectClass = Achievement::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.ACHIEVEMENTS)
)

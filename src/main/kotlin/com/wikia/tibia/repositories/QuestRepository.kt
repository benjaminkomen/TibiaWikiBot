package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Quest

class QuestRepository : WikiObjectRepository<Quest>(
  wikiObjectClass = Quest::class.java,
  wikiObjectGateway = WikiObjectGateway(Contract.QUESTS)
)

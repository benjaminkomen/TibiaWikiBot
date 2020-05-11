package com.wikia.tibia.gateways

import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.objects.Quest

class QuestGateway : WikiObjectGateway<Quest?>(Contracts.QUESTS)
package com.wikia.tibia.gateways

import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.objects.Creature

class CreatureGateway : WikiObjectGateway<Creature?>(Contracts.CREATURES)
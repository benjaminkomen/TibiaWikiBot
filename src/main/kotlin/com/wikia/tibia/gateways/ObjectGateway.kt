package com.wikia.tibia.gateways

import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.objects.TibiaObject

class ObjectGateway : WikiObjectGateway<TibiaObject?>(Contracts.OBJECTS)
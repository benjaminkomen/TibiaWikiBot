package com.wikia.tibia.gateways

import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.objects.Item

class ItemGateway : WikiObjectGateway<Item?>(Contracts.ITEMS)
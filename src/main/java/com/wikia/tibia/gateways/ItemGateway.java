package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Item;

public class ItemGateway extends WikiObjectGateway<Item> {

    public ItemGateway() {
        super(Contracts.ITEMS);
    }
}
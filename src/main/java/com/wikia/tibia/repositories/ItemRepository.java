package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.ItemGateway;
import com.wikia.tibia.objects.Item;

public class ItemRepository extends WikiObjectRepository {

    public ItemRepository() {
        super(Item.class, new ItemGateway());
    }
}

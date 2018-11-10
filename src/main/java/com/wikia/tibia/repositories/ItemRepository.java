package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.ItemGateway;
import com.wikia.tibia.jackson.Parser;
import com.wikia.tibia.objects.Item;

import java.util.List;

public class ItemRepository {

    private ItemGateway itemGateway;

    public ItemRepository() {
        itemGateway = new ItemGateway();
    }

    public List<Item> getItems() {
        return Parser.listOneByOne(Item.class, itemGateway.getItems(true));
    }

    public List<String> getItemsList() {
        return Parser.list(String.class, itemGateway.getItems(false));
    }
}

package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.http.Request;

public class ItemGateway {

    private Request request;

    public ItemGateway() {
        request = new Request();
    }

    public String getItems() {
        return getItems(false);
    }

    public String getItems(boolean expand) {
        return request.get(Contracts.ITEMS.getDescription() + "?expand=" + expand);
    }
}

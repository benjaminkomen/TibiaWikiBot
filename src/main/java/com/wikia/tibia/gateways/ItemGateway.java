package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.http.Header;
import com.wikia.tibia.http.Request;
import com.wikia.tibia.objects.Item;

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

    public String saveItem(Item item, String editSummary) {
        final Header header = Header.builder()
                .name("X-WIKI-Edit-Summary")
                .value(editSummary)
                .build();

        return request.put(Contracts.ITEMS.getDescription(), item, header);
    }
}

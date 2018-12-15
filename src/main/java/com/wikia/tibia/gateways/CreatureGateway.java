package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.http.Header;
import com.wikia.tibia.http.Request;
import com.wikia.tibia.objects.Creature;

public class CreatureGateway {

    private Request request;

    public CreatureGateway() {
        request = new Request();
    }

    public String getCreatures() {
        return getCreatures(false);
    }

    public String getCreatures(boolean expand) {
        return request.get(Contracts.CREATURES.getDescription() + "?expand=" + expand);
    }

    public String saveCreature(Creature creature, String editSummary) {
        final Header header = Header.builder()
                .name("X-WIKI-Edit-Summary")
                .value(editSummary)
                .build();

        return request.put(Contracts.CREATURES.getDescription(), creature, header);
    }
}

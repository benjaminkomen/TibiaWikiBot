package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.http.Request;

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
}

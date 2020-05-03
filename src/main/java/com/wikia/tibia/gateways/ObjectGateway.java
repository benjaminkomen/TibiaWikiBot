package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.TibiaObject;

public class ObjectGateway extends WikiObjectGateway<TibiaObject> {

    public ObjectGateway() {
        super(Contracts.OBJECTS);
    }
}

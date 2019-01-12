package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.ObjectGateway;
import com.wikia.tibia.objects.TibiaObject;

public class ObjectRepository extends WikiObjectRepository {

    public ObjectRepository() {
        super(TibiaObject.class, new ObjectGateway());
    }
}

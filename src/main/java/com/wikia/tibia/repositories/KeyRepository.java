package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.KeyGateway;
import com.wikia.tibia.objects.Key;

public class KeyRepository extends WikiObjectRepository {

    public KeyRepository() {
        super(Key.class, new KeyGateway());
    }
}

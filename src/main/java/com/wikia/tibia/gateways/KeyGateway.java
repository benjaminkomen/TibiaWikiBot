package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Key;

public class KeyGateway extends WikiObjectGateway<Key> {

    public KeyGateway() {
        super(Contracts.KEYS);
    }
}

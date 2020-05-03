package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Mount;

public class MountGateway extends WikiObjectGateway<Mount> {

    public MountGateway() {
        super(Contracts.MOUNTS);
    }
}

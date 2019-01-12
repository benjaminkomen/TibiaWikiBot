package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.MountGateway;
import com.wikia.tibia.objects.Mount;

public class MountRepository extends WikiObjectRepository {

    public MountRepository() {
        super(Mount.class, new MountGateway());
    }
}

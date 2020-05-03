package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Outfit;

public class OutfitGateway extends WikiObjectGateway<Outfit> {

    public OutfitGateway() {
        super(Contracts.OUTFITS);
    }
}

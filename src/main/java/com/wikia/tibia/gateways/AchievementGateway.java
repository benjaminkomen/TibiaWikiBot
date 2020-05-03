package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.objects.Achievement;

public class AchievementGateway extends WikiObjectGateway<Achievement> {

    public AchievementGateway() {
        super(Contracts.ACHIEVEMENTS);
    }
}

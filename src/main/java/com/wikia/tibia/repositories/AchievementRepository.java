package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.AchievementGateway;
import com.wikia.tibia.objects.Achievement;

public class AchievementRepository extends WikiObjectRepository {

    public AchievementRepository() {
        super(Achievement.class, new AchievementGateway());
    }
}

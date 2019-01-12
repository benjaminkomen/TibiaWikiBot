package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.QuestGateway;
import com.wikia.tibia.objects.Quest;

public class QuestRepository extends WikiObjectRepository {

    public QuestRepository() {
        super(Quest.class, new QuestGateway());
    }
}

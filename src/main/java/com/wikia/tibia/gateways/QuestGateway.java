package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Quest;

public class QuestGateway extends WikiObjectGateway<Quest> {

    public QuestGateway() {
        super(Contracts.QUESTS);
    }
}

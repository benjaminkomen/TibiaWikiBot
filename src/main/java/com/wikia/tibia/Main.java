package com.wikia.tibia;

import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.usecases.FixCreatures;
import com.wikia.tibia.usecases.FixItems;

public class Main {

    private Main() {
        // this class should not be constructed
    }

    public static void main(String[] args) {

        // initialize bot and login
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();

        // first usecase
        FixCreatures fixCreatures = new FixCreatures();
        fixCreatures.checkCreatures();

        // second usecase
        FixItems fixItems = new FixItems(tibiaWikiBot);
        fixItems.checkItems();
    }
}
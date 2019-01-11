package com.wikia.tibia;

import com.wikia.tibia.usecases.FixCreatures;
import com.wikia.tibia.usecases.FixItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private Main() {
        // this class should not be constructed
    }

    public static void main(String[] args) {

        FixCreatures fixCreatures = new FixCreatures();
        LOG.debug("Starting usecase FixCreatures");
        fixCreatures.checkCreatures();

        FixItems fixItems = new FixItems();
        LOG.debug("Starting usecase FixItems");
        fixItems.checkItems();
    }
}
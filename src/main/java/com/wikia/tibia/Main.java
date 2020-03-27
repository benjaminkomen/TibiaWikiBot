package com.wikia.tibia;

import com.wikia.tibia.usecases.FixCreatures;
import com.wikia.tibia.usecases.FixItems;
import com.wikia.tibia.usecases.FixLootStatistics;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {

    public static void main(String[] args) {

        FixLootStatistics fixLootStatistics = new FixLootStatistics();
        log.debug("Starting usecase FixLootStatistics");
        fixLootStatistics.checkLootStatistics();

        FixCreatures fixCreatures = new FixCreatures();
        log.debug("Starting usecase FixCreatures");
        fixCreatures.checkCreatures();

        FixItems fixItems = new FixItems();
        log.debug("Starting usecase FixItems");
        fixItems.checkItems();
    }
}
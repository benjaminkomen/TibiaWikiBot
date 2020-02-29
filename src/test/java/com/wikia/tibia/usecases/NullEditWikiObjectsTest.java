package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//@Ignore
public class NullEditWikiObjectsTest {

    private NullEditWikiObjects target;

    @Before
    public void setup() {
        target = new NullEditWikiObjects();
    }

    /**
     * Last partial run: February 29, 2020
     */
    @Test
    public void testCheckAchievements() {
        target.checkAchievements();
    }

    /**
     * Last successful run: May 4, 2019
     */
    @Test
    public void testCheckBooks() {
        target.checkBooks();
    }

    /**
     * Last successful run: May 4, 2019
     */
    @Test
    public void testCheckBuildings() {
        target.checkBuildings();
    }

    /**
     * Last successful run: May 12, 2019
     */
    @Test
    public void testCheckCorpses() {
        target.checkCorpses();
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    public void testCheckCreatures() {
        target.checkCreatures();
    }

    /**
     * Last successful run: May 12, 2019
     */
    @Test
    public void testCheckEffects() {
        target.checkEffects();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckHuntingPlaces() {
        target.checkHuntingPlaces();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckItems() {
        target.checkItems();
    }

    /**
     * Last successful run: never
     */
    @Test
    public void testCheckKeys() {
        target.checkKeys();
    }

    /**
     * Last successful run: not yet
     */
    @Test
    public void testCheckLocations() {
        target.checkLocations();
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    public void testCheckMissiles() {
        target.checkMissiles();
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    public void testCheckMounts() {
        target.checkMounts();
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    public void testCheckNPCs() {
        target.checkNPCs();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckObjects() {
        target.checkObjects();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckOutfits() {
        target.checkOutfits();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckQuests() {
        target.checkQuests();
    }

    /**
     * Last successful run:
     */
    @Test
    public void testCheckSpells() {
        target.checkSpells();
    }

    /**
     * Last successful run: February 29, 2020
     */
    @Test
    public void testCheckStreets() {
        target.checkStreets();
    }
}
package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class NullEditWikiObjectsTest {

    private NullEditWikiObjects target;

    @Before
    public void setup() {
        target = new NullEditWikiObjects();
    }

    @Test
    public void testCheckAchievements() {
        target.checkAchievements();
    }

    @Test
    public void testCheckBooks() {
        target.checkBooks();
    }

    @Test
    public void testCheckBuildings() {
        target.checkBuildings();
    }

    @Test
    public void testCheckCorpses() {
        target.checkCorpses();
    }

    @Test
    public void testCheckCreatures() {
        target.checkCreatures();
    }

    @Test
    public void testCheckEffects() {
        target.checkEffects();
    }

    @Test
    public void testCheckHuntingPlaces() {
        target.checkHuntingPlaces();
    }

    @Test
    public void testCheckItems() {
        target.checkItems();
    }

    @Test
    public void testCheckKeys() {
        target.checkKeys();
    }

    @Test
    public void testCheckLocations() {
        target.checkLocations();
    }

    @Test
    public void testCheckMissiles() {
        target.checkMissiles();
    }

    @Test
    public void testCheckMounts() {
        target.checkMounts();
    }

    @Test
    public void testCheckNPCs() {
        target.checkNPCs();
    }

    @Test
    public void testCheckObjects() {
        target.checkObjects();
    }

    @Test
    public void testCheckOutfits() {
        target.checkOutfits();
    }

    @Test
    public void testCheckQuests() {
        target.checkQuests();
    }

    @Test
    public void testCheckSpells() {
        target.checkSpells();
    }

    @Test
    public void testCheckStreets() {
        target.checkStreets();
    }
}
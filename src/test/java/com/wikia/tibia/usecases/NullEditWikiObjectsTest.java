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
    public void testCheckCreatures() {
        target.checkCreatures();
    }

    @Test
    public void testCheckItems() {
        target.checkItems();
    }

}
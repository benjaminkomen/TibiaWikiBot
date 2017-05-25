package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.TibiaWikiBot;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FixCreaturesTest {

    private FixCreatures target;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new FixCreatures(tibiaWikiBot);
    }

    @Ignore
    @Test
    public void testFixCreatures() {
        // given
        // when
        target.checkCreatures();
        // then
    }
}
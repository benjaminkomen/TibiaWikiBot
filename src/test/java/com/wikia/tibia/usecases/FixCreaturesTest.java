package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.repositories.WikiArticleRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FixCreaturesTest {

    private FixCreatures target;
    private WikiArticleRepository repository;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new FixCreatures(tibiaWikiBot);
        this.repository = new WikiArticleRepository(tibiaWikiBot);
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
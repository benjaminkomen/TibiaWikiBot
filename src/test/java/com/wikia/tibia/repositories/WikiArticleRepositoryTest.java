package com.wikia.tibia.repositories;

import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.objects.WikiObject;
import org.junit.Before;
import org.junit.Test;

public class WikiArticleRepositoryTest {

    private static final String CREATURE_PAGE_NAME = "Demon";
    private WikiArticleRepository target;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new WikiArticleRepository(tibiaWikiBot);
    }

    @Test
    public void testGetWikiObject() {
        // given
        // when
        WikiObject result = target.getWikiObject(CREATURE_PAGE_NAME);
        // then
    }

}
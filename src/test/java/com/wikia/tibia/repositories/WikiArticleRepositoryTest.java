package com.wikia.tibia.repositories;

import com.wikia.tibia.objects.TibiaWikiBot;
import org.junit.Before;
import org.junit.Test;

public class WikiArticleRepositoryTest {

    private static final String CREATURE_PAGE_NAME = "Poison Storm";
    private WikiArticleRepository target;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new WikiArticleRepository(tibiaWikiBot);
    }

    @Test
    public void testGetArticleJson() {
        // given
        // when
        String result = target.getArticleJson(CREATURE_PAGE_NAME);
        // then
    }

}
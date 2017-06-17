package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.repositories.WikiArticleRepository;
import org.junit.Before;


public class FixItemsTest {

    private FixItems target;
    private WikiArticleRepository repository;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new FixItems(tibiaWikiBot);
        this.repository = new WikiArticleRepository(tibiaWikiBot);
    }


}
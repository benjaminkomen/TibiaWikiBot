package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testFixCreatures() {
        // given
        // when
        target.checkCreatures();
        // then
    }

    @Test
    public void testGetAllCreatures() {
        List<Creature> creatures = new ArrayList<>();
        CategoryMembersSimple categoryMembers = repository.getMembersFromCategory("Creatures");

        for (String creaturePageName : categoryMembers) {
            Creature creature = (Creature) repository.getWikiObject(creaturePageName);
            creatures.add(creature);
        }
        int size = creatures.size();
    }
}
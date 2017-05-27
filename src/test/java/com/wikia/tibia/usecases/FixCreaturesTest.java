package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        CategoryMembersSimple pagesInCreaturesCategory = repository.getMembersFromCategory("Creatures");
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory("Lists");

        List<String> creaturesCategory = new ArrayList<>();
        for (String pageName : pagesInCreaturesCategory) {
            creaturesCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInCreaturesCategoryButNotLists = creaturesCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Creature> creatures = pagesInCreaturesCategoryButNotLists.stream()
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .collect(Collectors.toList());

        int size = creatures.size();

        // then
        assertThat(creatures.size(), is(pagesInCreaturesCategoryButNotLists.size()));
    }
}
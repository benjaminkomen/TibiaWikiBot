package com.wikia.tibia.repositories;

import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.objects.WikiObject;
import net.sourceforge.jwbf.core.contentRep.Article;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Ignore
public class WikiArticleRepositoryTest {

    private static final String PAGE_NAME = "Dragon";
    private static final List<String> PAGE_NAMES = Arrays.asList("Dragon", "Bear", "Ferumbras", "Santa Claus");
    private WikiArticleRepository target;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new WikiArticleRepository(tibiaWikiBot);
    }

    @Test
    public void testGetArticle() {
        Article result = target.getArticle(PAGE_NAME);

        assertThat(result.getTitle(), is(PAGE_NAME));
    }

    @Test
    public void testGetArticles() {
        List<String> PAGE_NAMES = Arrays.asList("Dragon", "Bear", "Ferumbras", "Santa Claus");
        List<Article> result = target.getArticles(PAGE_NAMES);

        assertThat(result, hasSize(PAGE_NAMES.size()));
    }

    @Test
    public void testGetWikiObject() {
        WikiObject result = target.getWikiObject(PAGE_NAME);

        assertThat(result.getName(), is(PAGE_NAME));
    }

    @Test
    public void testGetWikiObjects() {
        List<WikiObject> result = target.getWikiObjects(PAGE_NAMES);

        assertThat(result, hasSize(PAGE_NAMES.size()));
    }
}
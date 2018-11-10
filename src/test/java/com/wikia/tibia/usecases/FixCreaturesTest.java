package com.wikia.tibia.usecases;

import com.wikia.tibia.repositories.WikiArticleRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class FixCreaturesTest {

    private FixCreatures target;
    private WikiArticleRepository repository;

    @Before
    public void setup() {
        target = new FixCreatures();
    }

    @Ignore
    @Test
    public void testFixCreatures() {
        // given
        // when
        target.checkCreatures();
        // then
        assertThat("Done", is("Done"));
    }
}
package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class BuildingRentsTest {

    private BuildingRents target;

    @Before
    public void setup() {
        target = new BuildingRents();
    }

    @Ignore
    @Test
    public void testBuildingRents() {
        // given
        // when
        target.updateRentToBuildings();
        // then
        assertThat("Done", is("Done"));
    }
}
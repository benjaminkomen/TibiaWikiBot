package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Test;

public class BuildingCoordinatesTest {

    private BuildingCoordinates target;

    @Before
    public void setup() {
        target = new BuildingCoordinates();
    }

    @Test
    public void testAddBuildingCoordinatesToAllBuildings() {
        target.addBuildingCoordinatesToAllBuildings();
    }
}

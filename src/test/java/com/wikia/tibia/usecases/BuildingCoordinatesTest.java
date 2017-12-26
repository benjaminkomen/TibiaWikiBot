package com.wikia.tibia.usecases;

import com.wikia.tibia.enums.BuildingType;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.objects.Building;
import org.json.JSONObject;
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

    @Test
    public void testMapObjectToJson() {
        Building building = makeBuilding();

        JSONObject result = target.mapToJson(building);
    }

    @Test
    public void testMapToJson() {
        Building building = makeBuilding();

        JSONObject result = target.mapToJson(building);
    }

    private Building makeBuilding() {
        Building building = new Building();
        building.setName("MyHouse");
        building.setLocation("South of town");
        building.setBeds(2);
        building.setCity(City.ANKRAHMUN);
        building.setNotes("Lorem ipsum dolar ...");
        building.setFloors(1);
        building.setFurnishings("A chair and a lamp.\n Some additional info.");
        building.setType(BuildingType.House);
        building.setHouseid(1009);
        building.setPosx("124.01");
        building.setPosy("109.12");
        building.setPosz("7");
        building.setStreet("Main Street");
        return building;
    }
}

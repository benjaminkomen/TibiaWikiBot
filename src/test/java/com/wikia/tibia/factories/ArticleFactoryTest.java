package com.wikia.tibia.factories;

import com.wikia.tibia.enums.BuildingType;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.objects.Building;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class ArticleFactoryTest {

    private static final String BUILDING_COASTWOOD_4 =
            "{{Infobox Building|List={{{1|}}}|GetValue={{{GetValue|}}}\n" +
                    "| name          = Coastwood 4\n" +
                    "| implemented   = 6.2\n" +
                    "| type          = House\n" +
                    "| location      = Northern part of city, one floor above ground.\n" +
                    "| posx          = 127.159\n" +
                    "| posy          = 123.124\n" +
                    "| posz          = 6\n" +
                    "| street        = Coastwood\n" +
                    "| houseid       = 40504\n" +
                    "| size          = 19\n" +
                    "| beds          = 2\n" +
                    "| rent          = 1145\n" +
                    "| city          = Ab'Dendriel\n" +
                    "| openwindows   = 3\n" +
                    "| floors        = 1\n" +
                    "| rooms         = 1\n" +
                    "| furnishings   = 1 [[Wall Lamp]].\n" +
                    "| notes         = It's located above the [[Food]] Shop. The right house.\n" +
                    "| image         = [[File:Coastwood4.jpg|200px]]\n" +
                    "}}\n";

    @Test
    public void testCreateArticleText() {
        Building building = makeBuilding();
        String result = ArticleFactory.createArticleText(building);

        assertThat(result, is(BUILDING_COASTWOOD_4));
    }

    private Building makeBuilding() {
        Building building = new Building();
        building.setName("Coastwood 4");
        building.setImplemented("6.2");
        building.setType(BuildingType.House);
        building.setLocation("Northern part of city, one floor above ground.");
        building.setPosx("127.159");
        building.setPosy("123.124");
        building.setPosz("6");
        building.setStreet("Coastwood");
        building.setHouseid(40504);
        building.setSize(19);
        building.setBeds(2);
        building.setRent(1145);
        building.setCity(City.AB_DENDRIEL);
        building.setOpenwindows(3);
        building.setFloors(1);
        building.setRooms(1);
        building.setFurnishings("1 [[Wall Lamp]].");
        building.setNotes("It's located above the [[Food]] Shop. The right house.");
        building.setImage("[[File:Coastwood4.jpg|200px]]");
        return building;
    }
}

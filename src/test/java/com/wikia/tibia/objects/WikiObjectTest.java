package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.BuildingType;
import com.wikia.tibia.enums.City;
import com.wikia.tibia.enums.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class WikiObjectTest {

    private WikiObject target;

    @Before
    public void setup() {

    }

    @Test
    public void testGetFields_WikiObject() {
        target = makeWikiObject();

        List<String> result = target.getFieldNames();

        assertThat(result, hasSize(3));
    }

    @Test
    public void testGetFields_Building() {
        target = makeBuilding();

        List<String> result = target.getFieldNames();

        assertThat(result, hasSize(13));
    }

    @Test
    public void testGetValue() {
        target = makeBuilding();

        Object result1 = target.getValue("name");
        Object result2 = target.getValue("beds");

        assertThat(result1, is("MyHouse"));
        assertThat(result2, is(2));
    }

    @Test
    public void testMaxFieldSize() {
        target = makeBuilding();

        int result = target.maxFieldSize();

        assertThat(result, is(11));
    }

    private WikiObject makeWikiObject() {
        WikiObject wikiObject = new WikiObject.WikiObjectImpl();
        wikiObject.setActualname("bear");
        wikiObject.setArticle(Article.A);
        wikiObject.setStatus(Status.ACTIVE);
        return wikiObject;
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

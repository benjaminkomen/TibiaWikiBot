package com.wikia.tibia.usecases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wikia.tibia.objects.Building;
import com.wikia.tibia.repositories.InputRepository;
import one.util.streamex.StreamEx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Update all building pages with their coordinates.
 */
public class BuildingCoordinates {

    private InputRepository inputRepository = new InputRepository();
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String CATEGORY_LISTS = "Lists";
    private static final String CATEGORY_BUILDINGS = "Player-Ownable Buildings";

    public void addBuildingCoordinatesToAllBuildings() {
//        wikiArticleRepository = new WikiArticleRepository();
        List<Building> buildings = getAllBuildings();

        String json = inputRepository.getInput("json/house_positions.json");
        Map<String, Object> map = mapJsonToObject(json);

        map.forEach((key, value) -> {
            Building building = findBuildingByName(buildings, key);
            modifyBuilding(building, value);
        });

        // now all the buildings are modified, we can convert them back to Articles and save them
        buildings.stream()
//                .map(building -> ArticleFactory.createArticle(tibiaWikiBot, building))
                .peek(a -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
//                .forEach(a -> a.save("[bot] Removing mapper link from location and" +
//                        " adding posx, posy, posz attributes."));
    }

    @SuppressWarnings("unchecked")
    private void modifyBuilding(Building building, Object mapValue) {

        if (mapValue instanceof Map) {
            Map<String, String> map = (Map) mapValue;
//            building.toBuilder()
//                    .posx(map.get("posx"))
//                    .posy(map.get("posy"))
//                    .posz(map.get("posz"))
//                    .build();

            removeMapperLink(building);
        } else {
            throw new IllegalStateException("MapValue " + mapValue + "is not an instance of Map!");
        }
    }

    private void removeMapperLink(Building building) {
        if (building.getLocation() != null && !"".equals(building.getLocation())) {
            String locationWithoutMapperLink = building.getLocation()
                    .replaceAll("(, |)\\[http:(.*?) here]", "");
//            building.toBuilder().location(locationWithoutMapperLink);
        }
    }

    private Map<String, Object> mapJsonToObject(String json) {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };

        try {
            return objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            // blah
        }
        return Collections.emptyMap();
    }

    public List<Building> getAllBuildings() {
//        CategoryMembersSimple pagesInBuildingsCategory = wikiArticleRepository.getMembersFromCategory(CATEGORY_BUILDINGS);
//        CategoryMembersSimple pagesInListsCategory = wikiArticleRepository.getMembersFromCategory(CATEGORY_LISTS);

        long startTime = System.nanoTime();
        List<String> buildingsCategory = new ArrayList<>();
//        for (String pageName : pagesInBuildingsCategory) {
//            buildingsCategory.add(pageName);
//        }

        List<String> listsCategory = new ArrayList<>();
//        for (String pageName : pagesInListsCategory) {
//            listsCategory.add(pageName);
//        }

        List<String> pagesInBuildingsCategoryButNotLists = buildingsCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        try {
            return StreamEx.ofSubLists(pagesInBuildingsCategoryButNotLists, 250)
//                    .flatMap(pageNames -> wikiArticleRepository.getWikiObjects(pageNames).stream())
                    .filter(Building.class::isInstance)
                    .map(Building.class::cast)
                    .collect(Collectors.toList());
        } finally {
            long difference = System.nanoTime() - startTime;
            System.out.println(String.format("%s buildings were obtained in %d seconds.",
                    pagesInBuildingsCategoryButNotLists.size(), TimeUnit.NANOSECONDS.toSeconds(difference)));
        }
    }

    private Building findBuildingByName(List<Building> buildings, String buildingName) {
        return buildings.stream()
                .filter(b -> buildingName.equals(b.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Unknown building" + buildingName));
    }
}

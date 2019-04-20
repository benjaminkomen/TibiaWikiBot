package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Building;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.objects.csv.HouseRent;
import com.wikia.tibia.repositories.BuildingRepository;
import com.wikia.tibia.repositories.InputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Update all building pages one time with their new rent.
 */
public class BuildingRents {

    private static final Logger LOG = LoggerFactory.getLogger(BuildingRents.class);
    private static final boolean DEBUG_MODE = false;

    private InputRepository inputRepository = new InputRepository();
    private BuildingRepository buildingRepository;
    private List<Building> buildings = new ArrayList<>();

    public BuildingRents() {
        buildingRepository = new BuildingRepository();
    }

    public void updateRentToBuildings() {

        List<HouseRent> houseRents = inputRepository.getCSVFile("data/house_new_rents.csv", HouseRent.class);
        LOG.info("Reading house_new_rents.csv");

        getBuildings().forEach(building -> {
            final Optional<Integer> newHouseRent = getHouseRentByHouseId(houseRents, building.getHouseid()).map(HouseRent::getNewRentInGps).map(Integer::valueOf);

            if (newHouseRent.isEmpty()) {
                LOG.error("Could not find building with name {} in list of new house rents.", building.getName());
            }

            final Integer oldRent = building.getRent();
            final String oldHistory = building.getHistory();
            if (newHouseRent.isPresent() && !Objects.equals(oldRent, newHouseRent.get())) {

                building.withRent(newHouseRent.get());
                building.withHistory(updatedHistory(building, oldHistory, oldRent));

                final String editSummary = String.format("Updating rent for building %s from %s to %s.", building.getName(), oldRent, building.getRent());
                LOG.info(editSummary);
                saveBuildingArticle(building, editSummary);
            }
        });

        LOG.info("Finished updating rent for all Buildings.");
    }

    private String updatedHistory(Building building, String oldHistory, Integer oldRent) {
        final String addedHistoryText = String.format("Before {{OfficialNewsArchive|4984|April 15, 2019}}, this house's rent was %s gold per month.", oldRent);
        return oldHistory == null || "".equals(oldHistory)
                ? addedHistoryText
                : building.getHistory() + "\n" + addedHistoryText;
    }

    private Optional<HouseRent> getHouseRentByHouseId(List<HouseRent> houseRents, Integer houseId) {
        return houseRents.stream()
                .filter(hr -> Objects.equals(Integer.valueOf(hr.getHouseId()), houseId))
                .findAny();
    }

    private List<Building> getBuildings() {
        if (buildings == null || buildings.isEmpty()) {
            buildings = buildingRepository.getWikiObjects();
        }
        return buildings;
    }

    private void saveBuildingArticle(WikiObject wikiObject, String editSummary) {
        buildingRepository.saveWikiObject(wikiObject, editSummary, DEBUG_MODE);
        pauseForABit();
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

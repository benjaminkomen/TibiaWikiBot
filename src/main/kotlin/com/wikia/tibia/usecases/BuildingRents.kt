package com.wikia.tibia.usecases

import com.wikia.tibia.objects.Building
import com.wikia.tibia.objects.WikiObject
import com.wikia.tibia.objects.csv.HouseRent
import com.wikia.tibia.repositories.BuildingRepository
import com.wikia.tibia.repositories.InputRepository
import com.wikia.tibia.utils.pauseForABit
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Update all building pages one time with their new rent.
 */
class BuildingRents(
        private val buildingRepository: BuildingRepository,
        private var buildings: List<Building> = ArrayList()
) {

    fun updateRentToBuildings() {
        val houseRents = InputRepository.getCSVFile("data/house_new_rents.csv", HouseRent::class.java)
        logger.info("Reading house_new_rents.csv")
        getBuildings()
                .forEach { building ->
                    val newHouseRent = getHouseRentByHouseId(houseRents, building.houseid)?.newRentInGps?.toIntOrNull()

                    if (newHouseRent == null) {
                        logger.error("Could not find building with name ${building.name} in list of new house rents.")
                    }

                    val oldRent = building.rent
                    val oldHistory = building.history

                    if (newHouseRent != null && oldRent != newHouseRent) {
                        building.copy(
                                rent = newHouseRent,
                                history = updatedHistory(building, oldHistory, oldRent)
                        )
                        val editSummary = "Updating rent for building ${building.name} from $oldRent to ${building.rent}."
                        logger.info(editSummary)
                        saveBuildingArticle(building, editSummary)
                    }
                }

        logger.info("Finished updating rent for all Buildings.")
    }

    private fun updatedHistory(building: Building, oldHistory: String?, oldRent: Int): String {
        val addedHistoryText = "Before {{OfficialNewsArchive|4984|April 15, 2019}}, this house's rent was $oldRent gold per month."
        return if (oldHistory == null || "" == oldHistory) {
            addedHistoryText
        } else {
            "${building.history}\n$addedHistoryText}"
        }
    }

    private fun getHouseRentByHouseId(houseRents: List<HouseRent>, houseId: Int): HouseRent? {
        return houseRents.firstOrNull { it.houseId?.toIntOrNull() == houseId }
    }

    private fun getBuildings(): List<Building> {
        if (buildings.isEmpty()) {
            val tryList = buildingRepository.getWikiObjects()
            buildings = if (tryList.isSuccess) {
                tryList.get() as List<Building>
            } else {
                logger.error("Failed to get a list of buildings: ${tryList.cause}")
                emptyList()
            }
        }
        return buildings
    }

    private fun saveBuildingArticle(wikiObject: WikiObject, editSummary: String) {
        buildingRepository.saveWikiObject(wikiObject, editSummary, DEBUG_MODE)
        pauseForABit()
    }

    companion object {
        private const val DEBUG_MODE = false
        private val logger = LoggerFactory.getLogger(BuildingRents::class.java)
    }
}
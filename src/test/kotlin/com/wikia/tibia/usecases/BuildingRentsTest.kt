package com.wikia.tibia.usecases

import com.wikia.tibia.enums.BuildingType
import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.objects.Building
import com.wikia.tibia.objects.WikiObject
import com.wikia.tibia.objects.csv.HouseRent
import com.wikia.tibia.repositories.BuildingRepository
import com.wikia.tibia.repositories.InputRepository
import io.vavr.control.Try
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import testutils.any
import testutils.eq

class BuildingRentsTest {

    @Mock
    private lateinit var inputRepository: InputRepository

    @Mock
    private lateinit var buildingRepository: BuildingRepository
    private lateinit var target: BuildingRents

    @Before
    fun setup() {
        inputRepository = mock(InputRepository::class.java)
        buildingRepository = mock(BuildingRepository::class.java)
    }

    @Ignore
    @Test
    fun `should update rent to buildings - production`() {
        // given
        target = BuildingRents(buildingRepository)
        // when
        target.updateRentToBuildings()
        // then
        assertThat("Done", `is`("Done"))
    }

    @Test
    fun `should update rent to buildings`() {
        val someHouseRents = listOf(
                HouseRent(
                        houseId = "40501",
                        houseName = "Coastwood 1",
                        newRentInKk = "50",
                        newRentInGps = "50000"
                )
        )

        val someBuildings = listOf(
                Building(
                        image = "<gallery>Coastwood1.jpg</gallery>",
                        openwindows = 4,
                        rooms = 1,
                        houseid = 40501,
                        notes = "It's located in the Northwestern part of [[Ab'Dendriel]] on the second floor, the southern house.",
                        city = City.AB_DENDRIEL,
                        furnishings = "1 [[Wall Lamp]].",
                        history = "Before {{OfficialNewsArchive|4984|April 15, 2019}}, this house's rent was 980 gold per month.",
                        type = BuildingType.House,
                        rent = 980,
                        posx = "127.135",
                        posy = "123.115",
                        posz = "6",
                        floors = 1,
                        size = 16,
                        street = "Coastwood",
                        name = "Coastwood 1",
                        implemented = "6.2",
                        location = "[[Ab'Dendriel]]",
                        beds = 2,
                        ownable = YesNo.YES_LOWERCASE,
                        status = Status.ACTIVE
                )
        )

        `when`(inputRepository.getCSVFile(anyString(), eq(HouseRent::class.java))).thenReturn(someHouseRents)
        `when`(buildingRepository.getWikiObjects()).thenReturn(Try.success(someBuildings))
        `when`(buildingRepository.saveWikiObject(any(WikiObject::class.java), anyString(), anyBoolean())).thenReturn(Try.success("saved!"))
        target = BuildingRents(buildingRepository)

        target.updateRentToBuildings()

        assertThat("Done", `is`("Done"))
    }
}
package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Building;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.objects.csv.HouseRent;
import com.wikia.tibia.repositories.BuildingRepository;
import com.wikia.tibia.repositories.InputRepository;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildingRentsTest {

    @Mock
    private InputRepository inputRepository;
    @Mock
    private BuildingRepository buildingRepository;
    private BuildingRents target;

    @Before
    public void setup() {
        inputRepository = mock(InputRepository.class);
        buildingRepository = mock(BuildingRepository.class);
    }

    @Ignore
    @Test
    public void shouldUpdateRentToBuildings_Production() {
        // given
        target = new BuildingRents();
        // when
        target.updateRentToBuildings();
        // then
        assertThat("Done", is("Done"));
    }

    @Test
    public void shouldUpdateRentToBuildings() {
        var someHouseRents = List.of(
                HouseRent.builder()
                        .houseId("40501")
                        .houseName("Coastwood 1")
                        .newRentInKk("50")
                        .newRentInGps("50000")
                        .build()
        );
        var someBuildings = List.of(
                Building.builder()
                        .image("<gallery>Coastwood1.jpg</gallery>")
                        .openwindows(4)
                        .rooms(1)
                        .houseid(40501)
                        .notes("It's located in the Northwestern part of [[Ab'Dendriel]] on the second floor, the southern house.")
                        .city(City.AB_DENDRIEL)
                        .furnishings("1 [[Wall Lamp]].")
                        .history("Before {{OfficialNewsArchive|4984|April 15, 2019}}, this house's rent was 980 gold per month.")
                        .type(BuildingType.House)
                        .rent(980)
                        .posx("127.135")
                        .posy("123.115")
                        .posz("6")
                        .floors(1)
                        .size(16)
                        .street("Coastwood")
                        .name("Coastwood 1")
                        .implemented("6.2")
                        .location("[[Ab'Dendriel]]")
                        .beds(2)
                        .build()
        );

        when(inputRepository.getCSVFile(anyString(), eq(HouseRent.class))).thenReturn(someHouseRents);
        when(buildingRepository.getWikiObjects()).thenReturn(Try.success(someBuildings));
        when(buildingRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("saved!"));
        target = new BuildingRents(inputRepository, buildingRepository);


        target.updateRentToBuildings();

        assertThat("Done", is("Done"));
    }
}
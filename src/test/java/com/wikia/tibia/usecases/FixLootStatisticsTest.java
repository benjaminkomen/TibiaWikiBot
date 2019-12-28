package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.*;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.LootRepository;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FixLootStatisticsTest {

    private FixLootStatistics target;
    private CreatureRepository mockCreatureRepository;
    private LootRepository mockLootRepository;
    private Creature CreatureRat = makeCreatureRat();
    private Loot LootRat = makeLootRat();
    private Loot LootRatWithSword = makeLootRatWithSword();

    @Before
    public void setup() {
        mockCreatureRepository = mock(CreatureRepository.class);
        mockLootRepository = mock(LootRepository.class);
        target = new FixLootStatistics(mockCreatureRepository, mockLootRepository);
    }

    /**
     * Do nothing because the loot list of Rat contains exactly the same as the loot statistics list of Rat.
     */
    @Test
    public void shouldFixLootStatistics_DoNothing() {
        // given
        when(mockCreatureRepository.getWikiObject("Rat")).thenReturn(Try.success(CreatureRat));
        when(mockLootRepository.getWikiObjects()).thenReturn(Try.success(List.of(LootRat)));

        // when
        final Map<String, Creature> result = target.checkLootStatistics();
        // then
        assertThat(result.size(), is(0));
    }

    /**
     * The loot list of a Rat contains some items. Its loot statistics page contains those items, and additionally a Sword.
     * This should be added.
     */
    @Test
    public void shouldFixLootStatistics_AddSwordToDroppedByListOfRat() {
        // given
        when(mockCreatureRepository.getWikiObject("Rat")).thenReturn(Try.success(CreatureRat));
        when(mockLootRepository.getWikiObjects()).thenReturn(Try.success(List.of(LootRatWithSword)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkLootStatistics();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.containsKey("Rat"), is(true));
        assertThat(result.get("Rat").getLoot().size(), is(3));
        assertThat(result.get("Rat").getLoot().stream().anyMatch(lootItem -> "Sword".equals(lootItem.getItemName())), is(true));
    }


    private static Creature makeCreatureRat() {
        return Creature.builder()
                .actualname("Rat")
                .name("Rat")
                .loot(new ArrayList<>(Arrays.asList(
                        LootItem.builder().itemName("Gold Coin").amount("0-4").build(),
                        LootItem.builder().itemName("Cheese").build()
                        ))
                )
                .build();
    }

    private static Loot makeLootRat() {
        return Loot.builder()
                .kills("14605")
                .name("Rat")
                .loot(new ArrayList<>(Arrays.asList(
                        LootStatisticsItem.builder().itemName("Empty").times("108").build(),
                        LootStatisticsItem.builder().itemName("Cheese").times("5741").build(),
                        LootStatisticsItem.builder().itemName("Gold Coin").times("14477").amount("1").total("20591").build()
                )))
                .version("9.63")
                .build();
    }

    private static Loot makeLootRatWithSword() {
        return Loot.builder()
                .kills("14605")
                .name("Rat")
                .loot(new ArrayList<>(Arrays.asList(
                        LootStatisticsItem.builder().itemName("Empty").times("108").build(),
                        LootStatisticsItem.builder().itemName("Cheese").times("5741").build(),
                        LootStatisticsItem.builder().itemName("Gold Coin").times("14477").amount("1").total("20591").build(),
                        LootStatisticsItem.builder().itemName("Sword").times("1").build()
                )))
                .version("9.63")
                .build();
    }
}

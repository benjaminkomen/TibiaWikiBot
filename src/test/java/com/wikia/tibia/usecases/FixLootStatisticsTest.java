package com.wikia.tibia.usecases;

import com.wikia.tibia.enums.Rarity;
import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Loot;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.objects.LootStatisticsItem;
import com.wikia.tibia.objects.LootWrapper;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.LootRepository;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private final Creature CreatureRat = makeCreatureRat();
    private final Creature CreatureAmazon = makeCreatureAmazon();
    private final Creature CreatureDemon = makeCreatureDemon();

    private final LootWrapper LootRat = makeLootRat();
    private final LootWrapper LootAmazon = makeLootAmazon();
    private final LootWrapper LootRatWithSword = makeLootRatWithSword();
    private final LootWrapper LootDemon = makeLootDemon();

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
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(CreatureRat)));
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
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(CreatureRat)));
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

    /**
     * The loot list of a Rat contains some items. Its loot statistics page contains those items, and additionally a Sword.
     * This should be added.
     */
    @Test
    public void shouldFixLootStatistics_DoNotAddSkullToLootListOfAmazon() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(CreatureAmazon)));
        when(mockLootRepository.getWikiObjects()).thenReturn(Try.success(List.of(LootAmazon)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkLootStatistics();
        // then
        assertThat(result.size(), is(0));
    }

    /**
     * The loot list of a Demon contains some Demon (Goblin) items. These should not be added to a Demon's loot list.
     */
    @Test
    public void shouldFixLootStatistics_DoNotAddDemonGoblinLootToDemon() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(CreatureDemon)));
        when(mockLootRepository.getWikiObjects()).thenReturn(Try.success(List.of(LootDemon)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkLootStatistics();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.get("Demon").getLoot().get(1).getItemName(), is("Demonrage Sword"));
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

    private Creature makeCreatureAmazon() {
        return Creature.builder()
                .actualname("Amazon")
                .name("Amazon")
                .loot(new ArrayList<>(Arrays.asList(
                        LootItem.builder().itemName("Gold Coin").amount("0-20").build(),
                        LootItem.builder().itemName("Skull (Item)").amount("0-2").build(),
                        LootItem.builder().itemName("Dagger").build(),
                        LootItem.builder().itemName("Brown Bread").build(),
                        LootItem.builder().itemName("Sabre").build(),
                        LootItem.builder().itemName("Girlish Hair Decoration").build(),
                        LootItem.builder().itemName("Protective Charm").build(),
                        LootItem.builder().itemName("Torch").rarity(Rarity.RARE).build(),
                        LootItem.builder().itemName("Crystal Necklace").rarity(Rarity.VERY_RARE).build(),
                        LootItem.builder().itemName("Small Ruby").rarity(Rarity.VERY_RARE).build()
                        ))
                )
                .build();
    }

    private Creature makeCreatureDemon() {
        return Creature.builder()
                .actualname("Demon")
                .name("Demon")
                .loot(new ArrayList<>(Collections.singletonList(
                        LootItem.builder().itemName("Magic Plate Armor").build()
                        ))
                )
                .build();
    }

    private static LootWrapper makeLootRat() {
        return LootWrapper.builder()
                .loot2(Loot.builder()
                        .kills("14605")
                        .name("Rat")
                        .loot(new ArrayList<>(Arrays.asList(
                                LootStatisticsItem.builder().itemName("Empty").times("108").build(),
                                LootStatisticsItem.builder().itemName("Cheese").times("5741").build(),
                                LootStatisticsItem.builder().itemName("Gold Coin").times("14477").amount("1").total("20591").build()
                        )))
                        .version("9.63")
                        .build())
                .build();
    }

    private static LootWrapper makeLootRatWithSword() {
        return LootWrapper.builder()
                .loot2(Loot.builder()
                        .kills("14605")
                        .name("Rat")
                        .loot(new ArrayList<>(Arrays.asList(
                                LootStatisticsItem.builder().itemName("Empty").times("108").build(),
                                LootStatisticsItem.builder().itemName("Cheese").times("5741").build(),
                                LootStatisticsItem.builder().itemName("Gold Coin").times("14477").amount("1").total("20591").build(),
                                LootStatisticsItem.builder().itemName("Sword").times("1").build()
                        )))
                        .version("9.63")
                        .build())
                .build();
    }

    private LootWrapper makeLootAmazon() {
        return LootWrapper.builder()
                .loot2(Loot.builder()
                        .kills("21983")
                        .name("Amazon")
                        .loot(new ArrayList<>(Arrays.asList(
                                LootStatisticsItem.builder().itemName("Empty").times("253").build(),
                                LootStatisticsItem.builder().itemName("Dagger").times("17606").amount("1").total("17606").build(),
                                LootStatisticsItem.builder().itemName("Skull").times("17581").amount("1-2").total("26316").build(),
                                LootStatisticsItem.builder().itemName("Gold Coin").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Brown Bread").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Sabre").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Girlish Hair Decoration").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Protective Charm").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Torch").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Crystal Necklace").times("1").amount("1").total("2").build(),
                                LootStatisticsItem.builder().itemName("Small Ruby").times("1").amount("1").total("1").build()
                        )))
                        .version("8.6")
                        .build())
                .build();
    }

    private LootWrapper makeLootDemon() {
        return LootWrapper.builder()
                .loot2(Loot.builder()
                        .kills("34026")
                        .name("Demon")
                        .loot(new ArrayList<>(Arrays.asList(
                                LootStatisticsItem.builder().itemName("Small Stone").times("21").amount("1-3").total("38").build(),
                                LootStatisticsItem.builder().itemName("Mouldy Cheese").times("10").amount("1").total("10").build(),
                                LootStatisticsItem.builder().itemName("Leather Armor").times("8").amount("1").total("8").build(),
                                LootStatisticsItem.builder().itemName("Bone").times("7").amount("1").total("7").build(),
                                LootStatisticsItem.builder().itemName("Demonrage Sword").times("20").amount("1").total("20").build()
                        )))
                        .version("10.37")
                        .build())
                .build();
    }
}

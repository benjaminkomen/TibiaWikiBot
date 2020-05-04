package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.LootItem;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.ItemRepository;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FixItemsTest {

    private FixItems target;
    private Creature Rat = makeRat();
    private Creature Bear = makeBear();
    private Creature Wasp = makeWasp();
    private Item Cheese = makeCheese();
    private Item Honeycomb = makeHoneycomb();
    private Item BearPaw = makeBearPaw();
    private CreatureRepository mockCreatureRepository;
    private ItemRepository mockItemRepository;

    private static Creature makeRat() {
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

    private static Creature makeBear() {
        return Creature.builder()
                .actualname("Bear")
                .name("Bear")
                .loot(new ArrayList<>(Arrays.asList(
                        LootItem.builder().itemName("Meat").amount("0-4").build(),
                        LootItem.builder().itemName("Ham").amount("0-3").build()
                        ))
                )
                .build();
    }

    private static Creature makeWasp() {
        return Creature.builder()
                .actualname("Wasp")
                .name("Wasp")
                .loot(new ArrayList<>())
                .build();
    }

    private static Item makeCheese() {
        return Item.builder()
                .actualname("Cheese")
                .name("Cheese")
                .droppedby(new ArrayList<>(Arrays.asList("Cave Rat", "Corym Charlatan", "Green Djinn", "Mutated Human", "Rat")))
                .build();
    }

    private static Item makeHoneycomb() {
        return Item.builder()
                .actualname("Honeycomb")
                .name("Honeycomb")
                .droppedby(new ArrayList<>(Arrays.asList("Bear", "Grynch Clan Goblin", "Shadowpelt", "Wasp", "Werebear", "Willi Wasp")))
                .build();
    }

    private static Item makeBearPaw() {
        return Item.builder()
                .actualname("Bear Paw")
                .name("Bear Paw")
                .droppedby(new ArrayList<>(Arrays.asList("Bear", "Shadowpelt", "Werebear")))
                .build();
    }

    @Before
    public void setup() {
        mockCreatureRepository = mock(CreatureRepository.class);
        mockItemRepository = mock(ItemRepository.class);
        target = new FixItems(mockCreatureRepository, mockItemRepository);
    }

    @Test
    public void shouldFixItems_DoNothing() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Rat)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Cheese)));

        // when
        final Map<String, Creature> result = target.checkItems();
        // then
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldFixItems_AddHoneycombToLootOfBear() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Bear)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Honeycomb)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkItems();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.containsKey("Bear"), is(true));
        assertThat(result.get("Bear").getLoot().size(), is(3));
        assertThat(result.get("Bear").getLoot().contains(LootItem.builder().itemName("Honeycomb").build()), is(true));
    }

    @Test
    public void shouldFixItems_AddHoneycombToLootOfBearAndWasp() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Bear, Wasp)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Honeycomb)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkItems();
        // then
        assertThat(result.size(), is(2));
        assertThat(result.containsKey("Bear"), is(true));
        assertThat(result.containsKey("Wasp"), is(true));
        assertThat(result.get("Bear").getLoot().size(), is(3));
        assertThat(result.get("Bear").getLoot().contains(LootItem.builder().itemName("Honeycomb").build()), is(true));

        assertThat(result.get("Wasp").getLoot().size(), is(1));
        assertThat(result.get("Wasp").getLoot().contains(LootItem.builder().itemName("Honeycomb").build()), is(true));
    }

    @Test
    public void shouldFixItems_AddHoneycombAndBearPawToLootOfBear() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Bear)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Honeycomb, BearPaw)));
        when(mockCreatureRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Creature> result = target.checkItems();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.containsKey("Bear"), is(true));
        assertThat(result.get("Bear").getLoot().size(), is(4));
        assertThat(result.get("Bear").getLoot().contains(LootItem.builder().itemName("Honeycomb").build()), is(true));
        assertThat(result.get("Bear").getLoot().contains(LootItem.builder().itemName("Bear Paw").build()), is(true));
    }

}
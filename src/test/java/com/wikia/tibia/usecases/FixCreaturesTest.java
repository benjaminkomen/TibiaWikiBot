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

public class FixCreaturesTest {

    private FixCreatures target;
    private Creature Rat = makeRat();
    private Creature Bear = makeBear();
    private Creature Wasp = makeWasp();
    private Item Cheese = makeCheese();
    private Item Honeycomb = makeHoneycomb();
    private CreatureRepository mockCreatureRepository;
    private ItemRepository mockItemRepository;

    @Before
    public void setup() {
        mockCreatureRepository = mock(CreatureRepository.class);
        mockItemRepository = mock(ItemRepository.class);
        target = new FixCreatures(mockCreatureRepository, mockItemRepository);
    }

    @Test
    public void shouldFixCreatures_DoNothing() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Rat)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Cheese)));

        // when
        final Map<String, Item> result = target.checkCreatures();
        // then
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldFixCreatures_AddBearToDroppedbyOfHoneycomb() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Bear)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Honeycomb)));
        when(mockItemRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Item> result = target.checkCreatures();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.containsKey("Honeycomb"), is(true));
        assertThat(result.get("Honeycomb").getDroppedby().size(), is(5));
        assertThat(result.get("Honeycomb").getDroppedby().contains("Bear"), is(true));
        assertThat(result.get("Honeycomb").getDroppedby().contains("Wasp"), is(false));
    }

    @Test
    public void shouldFixCreatures_AddBearAndWaspToDroppedbyOfHoneycomb() {
        // given
        when(mockCreatureRepository.getWikiObjects()).thenReturn(Try.success(List.of(Bear, Wasp)));
        when(mockItemRepository.getWikiObjects()).thenReturn(Try.success(List.of(Honeycomb)));
        when(mockItemRepository.saveWikiObject(any(WikiObject.class), anyString(), anyBoolean())).thenReturn(Try.success("success"));

        // when
        final Map<String, Item> result = target.checkCreatures();
        // then
        assertThat(result.size(), is(1));
        assertThat(result.containsKey("Honeycomb"), is(true));
        assertThat(result.get("Honeycomb").getDroppedby().size(), is(6));
        assertThat(result.get("Honeycomb").getDroppedby().contains("Bear"), is(true));
        assertThat(result.get("Honeycomb").getDroppedby().contains("Wasp"), is(true));
    }

    private static Creature makeRat() {
        return Creature.builder()
                .actualname("Rat")
                .name("Rat")
                .loot(List.of(
                        LootItem.builder().itemName("Gold Coin").amount("0-4").build(),
                        LootItem.builder().itemName("Cheese").build()
                        )
                )
                .build();
    }

    private static Creature makeBear() {
        return Creature.builder()
                .actualname("Bear")
                .name("Bear")
                .loot(List.of(
                        LootItem.builder().itemName("Meat").amount("0-4").build(),
                        LootItem.builder().itemName("Ham").amount("0-3").build(),
                        LootItem.builder().itemName("Bear Paw").rarity(Rarity.SEMI_RARE).build(),
                        LootItem.builder().itemName("Honeycomb").rarity(Rarity.RARE).build()
                        )
                )
                .build();
    }

    private static Creature makeWasp() {
        return Creature.builder()
                .actualname("Wasp")
                .name("Wasp")
                .loot(List.of(
                        LootItem.builder().itemName("Honeycomb").rarity(Rarity.SEMI_RARE).build()
                        )
                )
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
                .droppedby(new ArrayList<>(Arrays.asList("Grynch Clan Goblin", "Shadowpelt", "Werebear", "Willi Wasp"))) // Bear and Wasp are purposely missing
                .build();
    }
}

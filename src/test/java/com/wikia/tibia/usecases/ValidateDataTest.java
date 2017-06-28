package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.*;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ValidateDataTest {

    private static final String CATEGORY_LISTS = "Lists";
    private static final String CATEGORY_ACHIEVEMENTS = "Achievements";
    private static final String CATEGORY_BOOKS = "Book Texts";
    private static final String CATEGORY_BUILDINGS = "Buildings";
    private static final String CATEGORY_CORPSES = "Corpses";
    private static final String CATEGORY_CREATURES = "Creatures";
    private static final String CATEGORY_EFFECTS = "Effects";
    private static final String CATEGORY_GEOGRAPHICAL_LOCATIONS = "Locations";
    private static final String CATEGORY_HUNTING_PLACES = "Hunting Places";
    private static final String CATEGORY_ITEMS = "Items";
    private static final String CATEGORY_KEYS = "Keys";
    private static final String CATEGORY_MOUNTS = "Mounts";
    private static final String CATEGORY_NPCS = "NPCs";
    private static final String CATEGORY_OBJECTS = "Objects";
    private static final String CATEGORY_OUTFITS = "Outfits";
    private static final String CATEGORY_QUESTS = "Quests";
    private static final String CATEGORY_SPELLS = "Spells";
    private static final String CATEGORY_STREETS = "Streets";
    private static final String CATEGORY_WORLDS = "Worlds";

    private WikiArticleRepository repository;


    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        this.repository = new WikiArticleRepository(tibiaWikiBot);
    }

    @Test
    public void testGetAllAchievements() {
        CategoryMembersSimple pagesInAchievementsCategory = repository.getMembersFromCategory(CATEGORY_ACHIEVEMENTS);
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory(CATEGORY_LISTS);

        List<String> achievementsCategory = new ArrayList<>();
        for (String pageName : pagesInAchievementsCategory) {
            achievementsCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInAchievementsCategoryButNotLists = achievementsCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Achievement> achievements = pagesInAchievementsCategoryButNotLists.stream()
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Achievement.class::isInstance)
                .map(Achievement.class::cast)
                .collect(Collectors.toList());

        // then
        assertThat(achievements.size(), is(pagesInAchievementsCategoryButNotLists.size()));
    }

    @Test
    public void testGetAllBooks() {
        CategoryMembersSimple pagesInBooksCategory = repository.getMembersFromCategory(CATEGORY_BOOKS);
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory(CATEGORY_LISTS);

        List<String> booksCategory = new ArrayList<>();
        for (String pageName : pagesInBooksCategory) {
            booksCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInBooksCategoryButNotLists = booksCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Book> books = pagesInBooksCategoryButNotLists.stream()
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Book.class::isInstance)
                .map(Book.class::cast)
                .collect(Collectors.toList());

        // then
        assertThat(books.size(), is(pagesInBooksCategoryButNotLists.size()));
    }

    @Test
    public void testGetAllBuildings() {
        CategoryMembersSimple pagesInBuildingsCategory = repository.getMembersFromCategory(CATEGORY_BUILDINGS);
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory(CATEGORY_LISTS);

        List<String> buildingsCategory = new ArrayList<>();
        for (String pageName : pagesInBuildingsCategory) {
            buildingsCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInBuildingsCategoryButNotLists = buildingsCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Building> buildings = pagesInBuildingsCategoryButNotLists.stream()
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Building.class::isInstance)
                .map(Building.class::cast)
                .collect(Collectors.toList());

        // then
        assertThat(buildings.size(), is(pagesInBuildingsCategoryButNotLists.size()));
    }

    @Test
    public void testGetAllCreatures() {
        CategoryMembersSimple pagesInCreaturesCategory = repository.getMembersFromCategory(CATEGORY_CREATURES);
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory(CATEGORY_LISTS);

        List<String> creaturesCategory = new ArrayList<>();
        for (String pageName : pagesInCreaturesCategory) {
            creaturesCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInCreaturesCategoryButNotLists = creaturesCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Creature> creatures = pagesInCreaturesCategoryButNotLists.stream()
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .collect(Collectors.toList());

        // then
        assertThat(creatures.size(), is(pagesInCreaturesCategoryButNotLists.size()));
    }

    @Test
    public void testGetAllItems() {
        CategoryMembersSimple pagesInItemsCategory = repository.getMembersFromCategory(CATEGORY_ITEMS);
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory(CATEGORY_LISTS);

        List<String> itemsCategory = new ArrayList<>();
        for (String pageName : pagesInItemsCategory) {
            itemsCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInItemsCategoryButNotLists = itemsCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        List<Item> items = pagesInItemsCategoryButNotLists.stream()
                .skip(0)
                .map(pageName -> repository.getWikiObject(pageName))
                .filter(Item.class::isInstance)
                .map(Item.class::cast)
                .collect(Collectors.toList());

        // then
        assertThat(items.size(), is(pagesInItemsCategoryButNotLists.size()));
    }

    @Test
    public void testGetSpecificItem() {
        // given
        String itemName = "Emerald Sword";
        // when
        WikiObject result = repository.getWikiObject(itemName);
        // then
        assertThat(result, not(nullValue()));
    }
}
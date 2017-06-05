package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


public class FixItemsTest {

    private FixItems target;
    private WikiArticleRepository repository;

    @Before
    public void setup() {
        TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();
        tibiaWikiBot.login();
        target = new FixItems(tibiaWikiBot);
        this.repository = new WikiArticleRepository(tibiaWikiBot);
    }

    @Test
    public void testGetAllItems() {
        CategoryMembersSimple pagesInItemsCategory = repository.getMembersFromCategory("Items");
        CategoryMembersSimple pagesInListsCategory = repository.getMembersFromCategory("Lists");

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
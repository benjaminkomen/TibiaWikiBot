package com.wikia.tibia.usecases;

import com.wikia.tibia.objects.Achievement;
import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.AchievementRepository;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Null-edit (make an edit without changing anything) all wiki objects. It must be possible to only do a subset of wiki
 * objects, e.g. only Items.
 */
public class NullEditWikiObjects {

    private static final Logger LOG = LoggerFactory.getLogger(NullEditWikiObjects.class);
    private static final boolean DEBUG_MODE = false;
    private static final String LOG_SAVE_ARTICLE = "Saving article {}";

    private AchievementRepository achievementRepository;
    private CreatureRepository creatureRepository;
    private ItemRepository itemRepository;
    private List<Achievement> achievements = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public NullEditWikiObjects() {
        this.achievementRepository = new AchievementRepository();
        this.creatureRepository = new CreatureRepository();
        this.itemRepository = new ItemRepository();
    }

    public void checkAchievements() {
        getAchievements()
                .forEach(achievement -> {
                            LOG.info(LOG_SAVE_ARTICLE, achievement.getName());
                            saveArticle(achievement);
                            pauseForABit();
                        }
                );
    }

    public void checkCreatures() {
        getCreatures()
                .forEach(creature -> {
                            LOG.info(LOG_SAVE_ARTICLE, creature.getName());
                            saveArticle(creature);
                            pauseForABit();
                        }
                );
    }

    public void checkItems() {
        getItems()
                .forEach(item -> {
                            LOG.info(LOG_SAVE_ARTICLE, item.getName());
                            saveArticle(item);
                            pauseForABit();
                        }
                );
    }

    private List<Achievement> getAchievements() {
        if (achievements == null || achievements.isEmpty()) {
            achievements = achievementRepository.getWikiObjects();
        }
        return achievements;
    }

    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            creatures = creatureRepository.getWikiObjects();
        }
        return creatures;
    }

    private List<Item> getItems() {
        if (items == null || items.isEmpty()) {
            items = itemRepository.getWikiObjects();
        }
        return items;
    }

    private void saveArticle(WikiObject wikiObject) {
        if (!DEBUG_MODE) {
            achievementRepository.saveWikiObject(wikiObject, "[bot] formatting parameters in standardised way.");
        }
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
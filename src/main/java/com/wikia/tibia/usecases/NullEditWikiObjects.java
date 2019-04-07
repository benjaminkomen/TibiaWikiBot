package com.wikia.tibia.usecases;

import com.wikia.tibia.jackson.Parser;
import com.wikia.tibia.objects.Achievement;
import com.wikia.tibia.objects.Book;
import com.wikia.tibia.objects.Building;
import com.wikia.tibia.objects.Corpse;
import com.wikia.tibia.objects.Creature;
import com.wikia.tibia.objects.Effect;
import com.wikia.tibia.objects.HuntingPlace;
import com.wikia.tibia.objects.Item;
import com.wikia.tibia.objects.Key;
import com.wikia.tibia.objects.Location;
import com.wikia.tibia.objects.Missile;
import com.wikia.tibia.objects.Mount;
import com.wikia.tibia.objects.NPC;
import com.wikia.tibia.objects.Outfit;
import com.wikia.tibia.objects.Quest;
import com.wikia.tibia.objects.Spell;
import com.wikia.tibia.objects.Street;
import com.wikia.tibia.objects.TibiaObject;
import com.wikia.tibia.objects.WikiObject;
import com.wikia.tibia.repositories.AchievementRepository;
import com.wikia.tibia.repositories.BookRepository;
import com.wikia.tibia.repositories.BuildingRepository;
import com.wikia.tibia.repositories.CorpseRepository;
import com.wikia.tibia.repositories.CreatureRepository;
import com.wikia.tibia.repositories.EffectRepository;
import com.wikia.tibia.repositories.HuntingPlaceRepository;
import com.wikia.tibia.repositories.ItemRepository;
import com.wikia.tibia.repositories.KeyRepository;
import com.wikia.tibia.repositories.LocationRepository;
import com.wikia.tibia.repositories.MissileRepository;
import com.wikia.tibia.repositories.MountRepository;
import com.wikia.tibia.repositories.NPCRepository;
import com.wikia.tibia.repositories.ObjectRepository;
import com.wikia.tibia.repositories.OutfitRepository;
import com.wikia.tibia.repositories.QuestRepository;
import com.wikia.tibia.repositories.SpellRepository;
import com.wikia.tibia.repositories.StreetRepository;
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
    private static final boolean DEBUG_MODE = true;
    private static final String LOG_SAVE_ARTICLE = "Saving article {} with JSON difference:\n {}";

    private AchievementRepository achievementRepository;
    private BookRepository bookRepository;
    private BuildingRepository buildingRepository;
    private CorpseRepository corpseRepository;
    private CreatureRepository creatureRepository;
    private EffectRepository effectRepository;
    private HuntingPlaceRepository huntingPlaceRepository;
    private ItemRepository itemRepository;
    private KeyRepository keyRepository;
    private LocationRepository locationRepository;
    private MissileRepository missileRepository;
    private MountRepository mountRepository;
    private NPCRepository npcRepository;
    private ObjectRepository objectRepository;
    private OutfitRepository outfitRepository;
    private QuestRepository questRepository;
    private SpellRepository spellRepository;
    private StreetRepository streetRepository;
    private List<Achievement> achievements = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();
    private List<Corpse> corpses = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();
    private List<Effect> effects = new ArrayList<>();
    private List<HuntingPlace> huntingPlaces = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Missile> missiles = new ArrayList<>();
    private List<Mount> mounts = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private List<TibiaObject> objects = new ArrayList<>();
    private List<Outfit> outfits = new ArrayList<>();
    private List<Quest> quests = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private List<Street> streets = new ArrayList<>();

    public NullEditWikiObjects() {
        this.achievementRepository = new AchievementRepository();
        this.bookRepository = new BookRepository();
        this.buildingRepository = new BuildingRepository();
        this.corpseRepository = new CorpseRepository();
        this.creatureRepository = new CreatureRepository();
        this.effectRepository = new EffectRepository();
        this.huntingPlaceRepository = new HuntingPlaceRepository();
        this.itemRepository = new ItemRepository();
        this.keyRepository = new KeyRepository();
        this.locationRepository = new LocationRepository();
        this.missileRepository = new MissileRepository();
        this.mountRepository = new MountRepository();
        this.npcRepository = new NPCRepository();
        this.objectRepository = new ObjectRepository();
        this.outfitRepository = new OutfitRepository();
        this.questRepository = new QuestRepository();
        this.spellRepository = new SpellRepository();
        this.streetRepository = new StreetRepository();
    }

    public void checkAchievements() {
        getAchievements()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                    saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkBooks() {
        getBooks()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkBuildings() {
        getBuildings()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkCorpses() {
        getCorpses().stream()
                .limit(10)
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }


    public void checkCreatures() {
        getCreatures()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                    saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkEffects() {
        getEffects()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkHuntingPlaces() {
        getHuntingPlaces()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkItems() {
        getItems()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                    saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkKeys() {
        getKeys()
                .forEach(wikiObject -> {
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkLocations() {
        getLocations()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkMissiles() {
        getMissiles()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkMounts() {
        getMounts()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkNPCs() {
        getNPCs()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkObjects() {
        getObjects()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkOutfits() {
        getOutfits()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkQuests() {
        getQuests()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkSpells() {
        getSpells()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
                            pauseForABit();
                        }
                );
    }

    public void checkStreets() {
        getStreets()
                .forEach(wikiObject -> {
                    wikiObject.withTargetJson(Parser.json(wikiObject));
                    LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            saveArticle(wikiObject);
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

    private List<Book> getBooks() {
        if (books == null || books.isEmpty()) {
            books = bookRepository.getWikiObjects();
        }
        return books;
    }

    private List<Building> getBuildings() {
        if (buildings == null || buildings.isEmpty()) {
            buildings = buildingRepository.getWikiObjects();
        }
        return buildings;
    }

    private List<Corpse> getCorpses() {
        if (corpses == null || corpses.isEmpty()) {
            corpses = corpseRepository.getWikiObjects();
        }
        return corpses;
    }

    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            creatures = creatureRepository.getWikiObjects();
        }
        return creatures;
    }

    private List<Effect> getEffects() {
        if (effects == null || effects.isEmpty()) {
            effects = effectRepository.getWikiObjects();
        }
        return effects;
    }

    private List<HuntingPlace> getHuntingPlaces() {
        if (huntingPlaces == null || huntingPlaces.isEmpty()) {
            huntingPlaces = huntingPlaceRepository.getWikiObjects();
        }
        return huntingPlaces;
    }

    private List<Item> getItems() {
        if (items == null || items.isEmpty()) {
            items = itemRepository.getWikiObjects();
        }
        return items;
    }

    private List<Key> getKeys() {
        if (keys == null || keys.isEmpty()) {
            keys = keyRepository.getWikiObjects();
        }
        return keys;
    }

    private List<Location> getLocations() {
        if (locations == null || locations.isEmpty()) {
            locations = locationRepository.getWikiObjects();
        }
        return locations;
    }

    private List<Missile> getMissiles() {
        if (missiles == null || missiles.isEmpty()) {
            missiles = missileRepository.getWikiObjects();
        }
        return missiles;
    }

    private List<Mount> getMounts() {
        if (mounts == null || mounts.isEmpty()) {
            mounts = mountRepository.getWikiObjects();
        }
        return mounts;
    }

    private List<NPC> getNPCs() {
        if (npcs == null || npcs.isEmpty()) {
            npcs = npcRepository.getWikiObjects();
        }
        return npcs;
    }

    private List<TibiaObject> getObjects() {
        if (objects == null || objects.isEmpty()) {
            objects = objectRepository.getWikiObjects();
        }
        return objects;
    }

    private List<Outfit> getOutfits() {
        if (outfits == null || outfits.isEmpty()) {
            outfits = outfitRepository.getWikiObjects();
        }
        return outfits;
    }

    private List<Quest> getQuests() {
        if (quests == null || quests.isEmpty()) {
            quests = questRepository.getWikiObjects();
        }
        return quests;
    }

    private List<Spell> getSpells() {
        if (spells == null || spells.isEmpty()) {
            spells = spellRepository.getWikiObjects();
        }
        return spells;
    }

    private List<Street> getStreets() {
        if (streets == null || streets.isEmpty()) {
            streets = streetRepository.getWikiObjects();
        }
        return streets;
    }

    private void saveArticle(WikiObject wikiObject) {
        achievementRepository.saveWikiObject(wikiObject, "[bot] formatting parameters in standardised way.", DEBUG_MODE);
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
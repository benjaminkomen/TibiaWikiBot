package com.wikia.tibia.usecases;

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
import org.json.JSONObject;
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
    private static final String LOG_SAVE_ARTICLE = "Saving article {} with JSON difference:\n {}";
    private static final String EDIT_SUMMARY = "[bot] formatting parameters in standardised way.";

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
                            wikiObject.setDefaultValues();
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            var jsonDifference = wikiObject.jsonDifference();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), jsonDifference);
                            achievementRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkBooks() {
        getBooks()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            bookRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkBuildings() {
        getBuildings()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            buildingRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkCorpses() {
        getCorpses()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            corpseRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }


    public void checkCreatures() {
        getCreatures()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            creatureRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkEffects() {
        getEffects()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            effectRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkHuntingPlaces() {
        getHuntingPlaces()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            huntingPlaceRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkItems() {
        getItems()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            itemRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkKeys() {
        getKeys()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            keyRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkLocations() {
        getLocations()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            locationRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkMissiles() {
        getMissiles()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            missileRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkMounts() {
        getMounts()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            mountRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkNPCs() {
        getNPCs()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            npcRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkObjects() {
        getObjects()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            objectRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkOutfits() {
        getOutfits()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            outfitRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkQuests() {
        getQuests()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            questRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkSpells() {
        getSpells()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            spellRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    public void checkStreets() {
        getStreets()
                .forEach(wikiObject -> {
                            wikiObject.setTargetJson(new JSONObject(wikiObject));
                            wikiObject.setDefaultValues();
                            LOG.info(LOG_SAVE_ARTICLE, wikiObject.getName(), wikiObject.jsonDifference());
                            streetRepository.saveWikiObject(wikiObject, EDIT_SUMMARY, DEBUG_MODE);
                            pauseForABit();
                        }
                );
    }

    private List<Achievement> getAchievements() {
        if (achievements == null || achievements.isEmpty()) {
            var tryList = achievementRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                achievements = (List<Achievement>) tryList.get();
            } else {
                LOG.error("Failed to get a list of achievements: %s", tryList.getCause());
            }
        }
        return achievements;
    }

    private List<Book> getBooks() {
        if (books == null || books.isEmpty()) {
            var tryList = bookRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                books = (List<Book>) tryList.get();
            } else {
                LOG.error("Failed to get a list of books: %s", tryList.getCause());
            }
        }
        return books;
    }

    private List<Building> getBuildings() {
        if (buildings == null || buildings.isEmpty()) {
            var tryList = buildingRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                buildings = (List<Building>) tryList.get();
            } else {
                LOG.error("Failed to get a list of buildings: %s", tryList.getCause());
            }
        }
        return buildings;
    }

    private List<Corpse> getCorpses() {
        if (corpses == null || corpses.isEmpty()) {
            var tryList = corpseRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                corpses = (List<Corpse>) tryList.get();
            } else {
                LOG.error("Failed to get a list of corpses: %s", tryList.getCause());
            }
        }
        return corpses;
    }

    private List<Creature> getCreatures() {
        if (creatures == null || creatures.isEmpty()) {
            var tryList = creatureRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                creatures = (List<Creature>) tryList.get();
            } else {
                LOG.error("Failed to get a list of creatures: %s", tryList.getCause());
            }
        }
        return creatures;
    }

    private List<Effect> getEffects() {
        if (effects == null || effects.isEmpty()) {
            var tryList = effectRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                effects = (List<Effect>) tryList.get();
            } else {
                LOG.error("Failed to get a list of effects: %s", tryList.getCause());
            }
        }
        return effects;
    }

    private List<HuntingPlace> getHuntingPlaces() {
        if (huntingPlaces == null || huntingPlaces.isEmpty()) {
            var tryList = huntingPlaceRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                huntingPlaces = (List<HuntingPlace>) tryList.get();
            } else {
                LOG.error("Failed to get a list of hunting places: %s", tryList.getCause());
            }
        }
        return huntingPlaces;
    }

    private List<Item> getItems() {
        if (items == null || items.isEmpty()) {
            var tryList = itemRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                items = (List<Item>) tryList.get();
            } else {
                LOG.error("Failed to get a list of items: %s", tryList.getCause());
            }
        }
        return items;
    }

    private List<Key> getKeys() {
        if (keys == null || keys.isEmpty()) {
            var tryList = keyRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                keys = (List<Key>) tryList.get();
            } else {
                LOG.error("Failed to get a list of keys: %s", tryList.getCause());
            }
        }
        return keys;
    }

    private List<Location> getLocations() {
        if (locations == null || locations.isEmpty()) {
            var tryList = locationRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                locations = (List<Location>) tryList.get();
            } else {
                LOG.error("Failed to get a list of locations: %s", tryList.getCause());
            }
        }
        return locations;
    }

    private List<Missile> getMissiles() {
        if (missiles == null || missiles.isEmpty()) {
            var tryList = missileRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                missiles = (List<Missile>) tryList.get();
            } else {
                LOG.error("Failed to get a list of missiles: %s", tryList.getCause());
            }
        }
        return missiles;
    }

    private List<Mount> getMounts() {
        if (mounts == null || mounts.isEmpty()) {
            var tryList = mountRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                mounts = (List<Mount>) tryList.get();
            } else {
                LOG.error("Failed to get a list of mounts: %s", tryList.getCause());
            }
        }
        return mounts;
    }

    private List<NPC> getNPCs() {
        if (npcs == null || npcs.isEmpty()) {
            var tryList = npcRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                npcs = (List<NPC>) tryList.get();
            } else {
                LOG.error("Failed to get a list of npcs: %s", tryList.getCause());
            }
        }
        return npcs;
    }

    private List<TibiaObject> getObjects() {
        if (objects == null || objects.isEmpty()) {
            var tryList = objectRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                objects = (List<TibiaObject>) tryList.get();
            } else {
                LOG.error("Failed to get a list of objects: %s", tryList.getCause());
            }
        }
        return objects;
    }

    private List<Outfit> getOutfits() {
        if (outfits == null || outfits.isEmpty()) {
            var tryList = outfitRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                outfits = (List<Outfit>) tryList.get();
            } else {
                LOG.error("Failed to get a list of outfits: %s", tryList.getCause());
            }
        }
        return outfits;
    }

    private List<Quest> getQuests() {
        if (quests == null || quests.isEmpty()) {
            var tryList = questRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                quests = (List<Quest>) tryList.get();
            } else {
                LOG.error("Failed to get a list of quests: %s", tryList.getCause());
            }
        }
        return quests;
    }

    private List<Spell> getSpells() {
        if (spells == null || spells.isEmpty()) {
            var tryList = spellRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                spells = (List<Spell>) tryList.get();
            } else {
                LOG.error("Failed to get a list of spells: %s", tryList.getCause());
            }
        }
        return spells;
    }

    private List<Street> getStreets() {
        if (streets == null || streets.isEmpty()) {
            var tryList = streetRepository.getWikiObjects();

            if (tryList.isSuccess()) {
                streets = (List<Street>) tryList.get();
            } else {
                LOG.error("Failed to get a list of streets: %s", tryList.getCause());
            }
        }
        return streets;
    }

    private void pauseForABit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
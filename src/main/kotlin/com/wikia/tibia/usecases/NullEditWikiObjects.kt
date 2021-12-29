package com.wikia.tibia.usecases

import com.wikia.tibia.objects.Achievement
import com.wikia.tibia.objects.Book
import com.wikia.tibia.objects.Building
import com.wikia.tibia.objects.Corpse
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Effect
import com.wikia.tibia.objects.HuntingPlace
import com.wikia.tibia.objects.Key
import com.wikia.tibia.objects.Location
import com.wikia.tibia.objects.Missile
import com.wikia.tibia.objects.Mount
import com.wikia.tibia.objects.NPC
import com.wikia.tibia.objects.Outfit
import com.wikia.tibia.objects.Quest
import com.wikia.tibia.objects.Spell
import com.wikia.tibia.objects.Street
import com.wikia.tibia.objects.TibiaObject
import com.wikia.tibia.repositories.AchievementRepository
import com.wikia.tibia.repositories.BookRepository
import com.wikia.tibia.repositories.BuildingRepository
import com.wikia.tibia.repositories.CorpseRepository
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.EffectRepository
import com.wikia.tibia.repositories.HuntingPlaceRepository
import com.wikia.tibia.repositories.KeyRepository
import com.wikia.tibia.repositories.LocationRepository
import com.wikia.tibia.repositories.MissileRepository
import com.wikia.tibia.repositories.MountRepository
import com.wikia.tibia.repositories.NPCRepository
import com.wikia.tibia.repositories.ObjectRepository
import com.wikia.tibia.repositories.OutfitRepository
import com.wikia.tibia.repositories.QuestRepository
import com.wikia.tibia.repositories.SpellRepository
import com.wikia.tibia.repositories.StreetRepository
import com.wikia.tibia.utils.pauseForABit
import org.slf4j.LoggerFactory

class NullEditWikiObjects(
  private val achievementRepository: AchievementRepository,
  private val bookRepository: BookRepository,
  private val buildingRepository: BuildingRepository,
  private val corpseRepository: CorpseRepository,
  private val creatureRepository: CreatureRepository,
  private val effectRepository: EffectRepository,
  private val huntingPlaceRepository: HuntingPlaceRepository,
  private val keyRepository: KeyRepository,
  private val locationRepository: LocationRepository,
  private val missileRepository: MissileRepository,
  private val mountRepository: MountRepository,
  private val npcRepository: NPCRepository,
  private val objectRepository: ObjectRepository,
  private val outfitRepository: OutfitRepository,
  private val questRepository: QuestRepository,
  private val spellRepository: SpellRepository,
  private val streetRepository: StreetRepository,
  private var achievements: List<Achievement> = ArrayList(),
  private var books: List<Book> = ArrayList(),
  private var buildings: List<Building> = ArrayList(),
  private var corpses: List<Corpse> = ArrayList(),
  private var creatures: List<Creature> = ArrayList(),
  private var effects: List<Effect> = ArrayList(),
  private var huntingPlaces: List<HuntingPlace> = ArrayList(),
  private var keys: List<Key> = ArrayList(),
  private var locations: List<Location> = ArrayList(),
  private var missiles: List<Missile> = ArrayList(),
  private var mounts: List<Mount> = ArrayList(),
  private var npcs: List<NPC> = ArrayList(),
  private var objects: List<TibiaObject> = ArrayList(),
  private var outfits: List<Outfit> = ArrayList(),
  private var quests: List<Quest> = ArrayList(),
  private var spells: List<Spell> = ArrayList(),
  private var streets: List<Street> = ArrayList()
) {

  fun checkAchievements() {
    getAchievements()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        achievementRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkBooks() {
    getBooks()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        bookRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkBuildings() {
    getBuildings()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        buildingRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkCorpses() {
    getCorpses()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        corpseRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkCreatures() {
    getCreatures()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        creatureRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkEffects() {
    getEffects()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        effectRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkHuntingPlaces() {
    getHuntingPlaces()
      .forEach {
        logger.info(LOG_SAVE_ARTICLE, it.name)
        huntingPlaceRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkKeys() {
    getKeys()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.getName())
        keyRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkLocations() {
    getLocations()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        locationRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkMissiles() {
    getMissiles()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        missileRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkMounts() {
    getMounts()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        mountRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkNPCs() {
    getNPCs()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        npcRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkObjects() {
    getObjects()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        objectRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkOutfits() {
    getOutfits()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        outfitRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkQuests() {
    getQuests()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        questRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkSpells() {
    getSpells()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        spellRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  fun checkStreets() {
    getStreets()
      .forEach {
        it.setDefaultValues()
        logger.info(LOG_SAVE_ARTICLE, it.name)
        streetRepository.saveWikiObject(it, EDIT_SUMMARY, DEBUG_MODE)
        pauseForABit()
      }
  }

  private fun getAchievements(): List<Achievement> {
    if (achievements.isEmpty()) {
      val tryList = achievementRepository.getWikiObjects()
      achievements = if (tryList.isSuccess) {
        tryList.get() as List<Achievement>
      } else {
        logger.error("Failed to get a list of achievements: ${tryList.cause}")
        emptyList()
      }
    }
    return achievements
  }

  private fun getBooks(): List<Book> {
    if (books.isEmpty()) {
      val tryList = bookRepository.getWikiObjects()
      books = if (tryList.isSuccess) {
        tryList.get() as List<Book>
      } else {
        logger.error("Failed to get a list of books: ${tryList.cause}")
        emptyList()
      }
    }
    return books
  }

  private fun getBuildings(): List<Building> {
    if (buildings.isEmpty()) {
      val tryList = buildingRepository.getWikiObjects()
      buildings = if (tryList.isSuccess) {
        tryList.get() as List<Building>
      } else {
        logger.error("Failed to get a list of buildings: ${tryList.cause}")
        emptyList()
      }
    }
    return buildings
  }

  private fun getCorpses(): List<Corpse> {
    if (corpses.isEmpty()) {
      val tryList = corpseRepository.getWikiObjects()
      corpses = if (tryList.isSuccess) {
        tryList.get() as List<Corpse>
      } else {
        logger.error("Failed to get a list of corpses: ${tryList.cause}")
        emptyList()
      }
    }
    return corpses
  }

  private fun getCreatures(): List<Creature> {
    if (creatures.isEmpty()) {
      val tryList = creatureRepository.getWikiObjects()
      creatures = if (tryList.isSuccess) {
        tryList.get() as List<Creature>
      } else {
        logger.error("Failed to get a list of creatures: ${tryList.cause}")
        emptyList()
      }
    }
    return creatures
  }

  private fun getEffects(): List<Effect> {
    if (effects.isEmpty()) {
      val tryList = effectRepository.getWikiObjects()
      effects = if (tryList.isSuccess) {
        tryList.get() as List<Effect>
      } else {
        logger.error("Failed to get a list of effects: ${tryList.cause}")
        emptyList()
      }
    }
    return effects
  }

  private fun getHuntingPlaces(): List<HuntingPlace> {
    if (huntingPlaces.isEmpty()) {
      val tryList = huntingPlaceRepository.getWikiObjects()
      huntingPlaces = if (tryList.isSuccess) {
        tryList.get() as List<HuntingPlace>
      } else {
        logger.error("Failed to get a list of hunting places: ${tryList.cause}")
        emptyList()
      }
    }
    return huntingPlaces
  }

  private fun getKeys(): List<Key> {
    if (keys.isEmpty()) {
      val tryList = keyRepository.getWikiObjects()
      keys = if (tryList.isSuccess) {
        tryList.get() as List<Key>
      } else {
        logger.error("Failed to get a list of keys: ${tryList.cause}")
        emptyList()
      }
    }
    return keys
  }

  private fun getLocations(): List<Location> {
    if (locations.isEmpty()) {
      val tryList = locationRepository.getWikiObjects()
      locations = if (tryList.isSuccess) {
        tryList.get() as List<Location>
      } else {
        logger.error("Failed to get a list of locations: ${tryList.cause}")
        emptyList()
      }
    }
    return locations
  }

  private fun getMissiles(): List<Missile> {
    if (missiles.isEmpty()) {
      val tryList = missileRepository.getWikiObjects()
      missiles = if (tryList.isSuccess) {
        tryList.get() as List<Missile>
      } else {
        logger.error("Failed to get a list of missiles: ${tryList.cause}")
        emptyList()
      }
    }
    return missiles
  }

  private fun getMounts(): List<Mount> {
    if (mounts.isEmpty()) {
      val tryList = mountRepository.getWikiObjects()
      mounts = if (tryList.isSuccess) {
        tryList.get() as List<Mount>
      } else {
        logger.error("Failed to get a list of mounts: ${tryList.cause}")
        emptyList()
      }
    }
    return mounts
  }

  private fun getNPCs(): List<NPC> {
    if (npcs.isEmpty()) {
      val tryList = npcRepository.getWikiObjects()
      npcs = if (tryList.isSuccess) {
        tryList.get() as List<NPC>
      } else {
        logger.error("Failed to get a list of npcs: ${tryList.cause}")
        emptyList()
      }
    }
    return npcs
  }

  private fun getObjects(): List<TibiaObject> {
    if (objects.isEmpty()) {
      val tryList = objectRepository.getWikiObjects()
      objects = if (tryList.isSuccess) {
        tryList.get() as List<TibiaObject>
      } else {
        logger.error("Failed to get a list of objects: ${tryList.cause}")
        emptyList()
      }
    }
    return objects
  }

  private fun getOutfits(): List<Outfit> {
    if (outfits.isEmpty()) {
      val tryList = outfitRepository.getWikiObjects()
      outfits = if (tryList.isSuccess) {
        tryList.get() as List<Outfit>
      } else {
        logger.error("Failed to get a list of outfits: ${tryList.cause}")
        emptyList()
      }
    }
    return outfits
  }

  private fun getQuests(): List<Quest> {
    if (quests.isEmpty()) {
      val tryList = questRepository.getWikiObjects()
      quests = if (tryList.isSuccess) {
        tryList.get() as List<Quest>
      } else {
        logger.error("Failed to get a list of quests: ${tryList.cause}")
        emptyList()
      }
    }
    return quests
  }

  private fun getSpells(): List<Spell> {
    if (spells.isEmpty()) {
      val tryList = spellRepository.getWikiObjects()
      spells = if (tryList.isSuccess) {
        tryList.get() as List<Spell>
      } else {
        logger.error("Failed to get a list of spells: ${tryList.cause}")
        emptyList()
      }
    }
    return spells
  }

  private fun getStreets(): List<Street> {
    if (streets.isEmpty()) {
      val tryList = streetRepository.getWikiObjects()
      streets = if (tryList.isSuccess) {
        tryList.get() as List<Street>
      } else {
        logger.error("Failed to get a list of streets: ${tryList.cause}")
        emptyList()
      }
    }
    return streets
  }

  companion object {
    private val logger = LoggerFactory.getLogger(NullEditWikiObjects::class.java)
    private const val DEBUG_MODE = false
    private const val LOG_SAVE_ARTICLE = "Saving article {}"
    private const val EDIT_SUMMARY = "[bot] formatting parameters in standardised way."
  }
}

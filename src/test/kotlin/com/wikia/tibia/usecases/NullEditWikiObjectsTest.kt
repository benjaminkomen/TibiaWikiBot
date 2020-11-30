package com.wikia.tibia.usecases

import com.wikia.tibia.repositories.AchievementRepository
import com.wikia.tibia.repositories.BookRepository
import com.wikia.tibia.repositories.BuildingRepository
import com.wikia.tibia.repositories.CorpseRepository
import com.wikia.tibia.repositories.CreatureRepository
import com.wikia.tibia.repositories.EffectRepository
import com.wikia.tibia.repositories.HuntingPlaceRepository
import com.wikia.tibia.repositories.ItemRepository
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
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Ignore
class NullEditWikiObjectsTest {
    private lateinit var target: NullEditWikiObjects

    @Before
    fun setup() {
        target = NullEditWikiObjects(
            achievementRepository = AchievementRepository(),
            bookRepository = BookRepository(),
            buildingRepository = BuildingRepository(),
            corpseRepository = CorpseRepository(),
            creatureRepository = CreatureRepository(),
            effectRepository = EffectRepository(),
            huntingPlaceRepository = HuntingPlaceRepository(),
            itemRepository = ItemRepository(),
            keyRepository = KeyRepository(),
            locationRepository = LocationRepository(),
            missileRepository = MissileRepository(),
            mountRepository = MountRepository(),
            npcRepository = NPCRepository(),
            objectRepository = ObjectRepository(),
            outfitRepository = OutfitRepository(),
            questRepository = QuestRepository(),
            spellRepository = SpellRepository(),
            streetRepository = StreetRepository()
        )
    }

    /**
     * Last partial run: February 29, 2020
     */
    @Test
    fun testCheckAchievements() {
        target.checkAchievements()
    }

    /**
     * Last successful run: May 4, 2019
     */
    @Test
    fun testCheckBooks() {
        target.checkBooks()
    }

    /**
     * Last successful run: May 4, 2019
     */
    @Test
    fun testCheckBuildings() {
        target.checkBuildings()
    }

    /**
     * Last successful run: May 12, 2019
     */
    @Test
    fun testCheckCorpses() {
        target.checkCorpses()
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    fun testCheckCreatures() {
        target.checkCreatures()
    }

    /**
     * Last successful run: May 12, 2019
     */
    @Test
    fun testCheckEffects() {
        target.checkEffects()
    }

    /**
     * Last successful run:
     */
    @Test
    fun testCheckHuntingPlaces() {
        target.checkHuntingPlaces()
    }

    /**
     * Last successful run: February 29, 2020
     */
    @Test
    fun testCheckItems() {
        target.checkItems()
    }

    /**
     * Last successful run: never
     */
    @Test
    fun testCheckKeys() {
        target.checkKeys()
    }

    /**
     * Last successful run: not yet
     */
    @Test
    fun testCheckLocations() {
        target.checkLocations()
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    fun testCheckMissiles() {
        target.checkMissiles()
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    fun testCheckMounts() {
        target.checkMounts()
    }

    /**
     * Last successful run: May 25, 2019
     */
    @Test
    fun testCheckNPCs() {
        target.checkNPCs()
    }

    /**
     * Last successful run:
     */
    @Test
    fun testCheckObjects() {
        target.checkObjects()
    }

    /**
     * Last partial run: February 29, 2020
     */
    @Test
    fun testCheckOutfits() {
        target.checkOutfits()
    }

    /**
     * Last partial run: February 29, 2020
     */
    @Test
    fun testCheckQuests() {
        target.checkQuests()
    }

    /**
     * Last partial run: February 29, 2020
     */
    @Test
    fun testCheckSpells() {
        target.checkSpells()
    }

    /**
     * Last successful run: February 29, 2020
     */
    @Test
    fun testCheckStreets() {
        target.checkStreets()
    }
}

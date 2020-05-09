package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Creature(
        private val name: String,
        private val article: Article,
        private val actualname: String,
        private val plural: String,
        private var implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        @get:JsonGetter("hp") var hitPoints: String?,
        @get:JsonGetter("exp") var experiencePoints: String?,
        private var armor: String?,
        private var summon: String?,
        private var convince: String?,
        private var illusionable: YesNo?,
        private var creatureclass: String?,
        private var primarytype: String?,
        private val secondarytype: String,
        private val bestiaryclass: BestiaryClass,
        private val bestiarylevel: BestiaryLevel,
        private val occurrence: BestiaryOccurrence,
        private val spawntype: List<Spawntype>,
        private var isboss: YesNo?,
        private val isarenaboss: YesNo,
        private val isevent: YesNo,
        private var abilities: String?,
        private val usedelements: String,
        private var maxdmg: String?,
        private var pushable: YesNo?,
        private var pushobjects: YesNo?,
        private var walksaround: String?,
        private var walksthrough: String?,
        private var paraimmune: YesNo?,
        private var senseinvis: YesNo?,
        private var physicalDmgMod: Percentage?,
        private var holyDmgMod: Percentage?,
        private var deathDmgMod: Percentage?,
        private var fireDmgMod: Percentage?,
        private var energyDmgMod: Percentage?,
        private var iceDmgMod: Percentage?,
        private var earthDmgMod: Percentage?,
        private var drownDmgMod: Percentage?,
        private var hpDrainDmgMod: Percentage?,
        private var healMod: Percentage?,
        private val bestiaryname: String,
        private val bestiarytext: String,
        private var sounds: List<String>?,
        private var behaviour: String?,
        private val runsat: String,
        private var speed: String?,
        private var strategy: String?,
        private var location: String?,
        private var loot: List<LootItem>?
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(hitPoints)) {
            hitPoints = "?"
        }
        if (isEmpty(experiencePoints)) {
            experiencePoints = "?"
        }
        if (isEmpty(armor)) {
            armor = "?"
        }
        if (isEmpty(summon)) {
            summon = "?"
        }
        if (isEmpty(convince)) {
            convince = "?"
        }
        if (isEmpty(illusionable)) {
            illusionable = YesNo.UNKNOWN
        }
        if (isEmpty(creatureclass)) {
            creatureclass = ""
            logger.warn("Creature '{}' has no creatureclass set", name)
        }
        if (isEmpty(primarytype)) {
            primarytype = ""
            logger.warn("Creature '{}' has no primarytype set", name)
        }
        if (isEmpty(isboss)) {
            isboss = YesNo.UNKNOWN
        }
        if (isEmpty(abilities)) {
            abilities = "Unknown"
        }
        if (isEmpty(maxdmg)) {
            maxdmg = "?"
        }
        if (isEmpty(pushable)) {
            pushable = YesNo.UNKNOWN
        }
        if (isEmpty(pushobjects)) {
            pushobjects = YesNo.UNKNOWN
        }
        if (isEmpty(walksaround)) {
            walksaround = "?"
        }
        if (isEmpty(walksthrough)) {
            walksthrough = "?"
        }
        if (isEmpty(paraimmune)) {
            paraimmune = YesNo.UNKNOWN
        }
        if (isEmpty(senseinvis)) {
            senseinvis = YesNo.UNKNOWN
        }
        if (isEmpty(physicalDmgMod)) {
            physicalDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(earthDmgMod)) {
            earthDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(fireDmgMod)) {
            fireDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(deathDmgMod)) {
            deathDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(energyDmgMod)) {
            energyDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(holyDmgMod)) {
            holyDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(iceDmgMod)) {
            iceDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(healMod)) {
            healMod = Percentage.UNKNOWN
        }

        // Special case, all creatures in the Bestiary have a healMod of 100%
        if (!isEmpty(healMod) && healMod == Percentage.UNKNOWN && (!isEmpty(bestiaryclass) || !isEmpty(bestiarylevel))) {
            healMod = Percentage.of(100)
        }
        if (isEmpty(hpDrainDmgMod)) {
            hpDrainDmgMod = Percentage.UNKNOWN
        }
        if (isEmpty(drownDmgMod)) {
            drownDmgMod = Percentage.UNKNOWN
        }
        if (sounds == null) {
            sounds = emptyList()
        }

        // if runsat is filled you would get something like this printed "Unknown A stalker never retreats.", which is ugly.
        if (isEmpty(behaviour) && isEmpty(runsat)) {
            behaviour = "Unknown"
        } else if ("Unknown" == behaviour && !isEmpty(runsat)) {
            behaviour = ""
        }
        if (isEmpty(speed)) {
            speed = "?"
        }
        if (isEmpty(strategy)) {
            strategy = "Unknown"
        }
        if (isEmpty(location)) {
            location = "Unknown"
        }
        if (loot == null) {
            loot = emptyList()
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Creature::class.java)
    }
}
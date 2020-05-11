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
        private val implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        @get:JsonGetter("hp") val hitPoints: String?,
        @get:JsonGetter("exp") val experiencePoints: String?,
        private val armor: String?,
        private val summon: String?,
        private val convince: String?,
        private val illusionable: YesNo?,
        private val creatureclass: String?,
        private val primarytype: String?,
        private val secondarytype: String,
        private val bestiaryclass: BestiaryClass,
        private val bestiarylevel: BestiaryLevel,
        private val occurrence: BestiaryOccurrence,
        private val spawntype: List<Spawntype>,
        private val isboss: YesNo?,
        private val isarenaboss: YesNo,
        private val isevent: YesNo,
        private val abilities: String?,
        private val usedelements: String,
        private val maxdmg: String?,
        private val pushable: YesNo?,
        private val pushobjects: YesNo?,
        private val walksaround: String?,
        private val walksthrough: String?,
        private val paraimmune: YesNo?,
        private val senseinvis: YesNo?,
        private val physicalDmgMod: Percentage?,
        private val holyDmgMod: Percentage?,
        private val deathDmgMod: Percentage?,
        private val fireDmgMod: Percentage?,
        private val energyDmgMod: Percentage?,
        private val iceDmgMod: Percentage?,
        private val earthDmgMod: Percentage?,
        private val drownDmgMod: Percentage?,
        private val hpDrainDmgMod: Percentage?,
        private val healMod: Percentage?,
        private val bestiaryname: String,
        private val bestiarytext: String,
        private val sounds: List<String>?,
        private val behaviour: String?,
        private val runsat: String,
        private val speed: String?,
        private val strategy: String?,
        private val location: String?,
        private val loot: List<LootItem>?
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(hitPoints)) {
            this.copy(hitPoints = "?")
        }
        if (isEmpty(experiencePoints)) {
            this.copy(experiencePoints = "?")
        }
        if (isEmpty(armor)) {
            this.copy(armor = "?")
        }
        if (isEmpty(summon)) {
            this.copy(summon = "?")
        }
        if (isEmpty(convince)) {
            this.copy(convince = "?")
        }
        if (isEmpty(illusionable)) {
            this.copy(illusionable = YesNo.UNKNOWN)
        }
        if (isEmpty(creatureclass)) {
            this.copy(creatureclass = "")
            logger.warn("Creature '{}' has no creatureclass set", name)
        }
        if (isEmpty(primarytype)) {
            this.copy(primarytype = "")
            logger.warn("Creature '{}' has no primarytype set", name)
        }
        if (isEmpty(isboss)) {
            this.copy(isboss = YesNo.UNKNOWN)
        }
        if (isEmpty(abilities)) {
            this.copy(abilities = "Unknown")
        }
        if (isEmpty(maxdmg)) {
            this.copy(maxdmg = "?")
        }
        if (isEmpty(pushable)) {
            this.copy(pushable = YesNo.UNKNOWN)
        }
        if (isEmpty(pushobjects)) {
            this.copy(pushobjects = YesNo.UNKNOWN)
        }
        if (isEmpty(walksaround)) {
            this.copy(walksaround = "?")
        }
        if (isEmpty(walksthrough)) {
            this.copy(walksthrough = "?")
        }
        if (isEmpty(paraimmune)) {
            this.copy(paraimmune = YesNo.UNKNOWN)
        }
        if (isEmpty(senseinvis)) {
            this.copy(senseinvis = YesNo.UNKNOWN)
        }
        if (isEmpty(physicalDmgMod)) {
            this.copy(physicalDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(earthDmgMod)) {
            this.copy(earthDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(fireDmgMod)) {
            this.copy(fireDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(deathDmgMod)) {
            this.copy(deathDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(energyDmgMod)) {
            this.copy(energyDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(holyDmgMod)) {
            this.copy(holyDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(iceDmgMod)) {
            this.copy(iceDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(healMod)) {
            this.copy(healMod = Percentage.UNKNOWN)
        }

        // Special case, all creatures in the Bestiary have a healMod of 100%
        if (!isEmpty(healMod) && healMod == Percentage.UNKNOWN && (!isEmpty(bestiaryclass) || !isEmpty(bestiarylevel))) {
            this.copy(healMod = Percentage.of(100))
        }
        if (isEmpty(hpDrainDmgMod)) {
            this.copy(hpDrainDmgMod = Percentage.UNKNOWN)
        }
        if (isEmpty(drownDmgMod)) {
            this.copy(drownDmgMod = Percentage.UNKNOWN)
        }
        if (sounds == null) {
            this.copy(sounds = emptyList())
        }

        // if runsat is filled you would get something like this printed "Unknown A stalker never retreats.", which is ugly.
        if (isEmpty(behaviour) && isEmpty(runsat)) {
            this.copy(behaviour = "Unknown")
        } else if ("Unknown" == behaviour && !isEmpty(runsat)) {
            this.copy(behaviour = "")
        }
        if (isEmpty(speed)) {
            this.copy(speed = "?")
        }
        if (isEmpty(strategy)) {
            this.copy(strategy = "Unknown")
        }
        if (isEmpty(location)) {
            this.copy(location = "Unknown")
        }
        if (loot == null) {
            this.copy(loot = emptyList())
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Creature::class.java)
    }
}
package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Creature(
        val name: String,
        private val article: Article? = null,
        private val actualname: String,
        private val plural: String? = null,
        private val implemented: String? = null,
        private val notes: String? = null,
        private val history: String? = null,
        val status: Status? = null,
        @get:JsonGetter("hp") val hitPoints: String? = null,
        @get:JsonGetter("exp") val experiencePoints: String? = null,
        private val armor: String? = null,
        private val summon: String? = null,
        private val convince: String? = null,
        private val illusionable: YesNo? = null,
        private val creatureclass: String? = null,
        private val primarytype: String? = null,
        private val secondarytype: String? = null,
        private val bestiaryclass: BestiaryClass? = null,
        private val bestiarylevel: BestiaryLevel? = null,
        private val occurrence: BestiaryOccurrence? = null,
        private val spawntype: List<Spawntype>? = emptyList(),
        private val isboss: YesNo? = null,
        private val isarenaboss: YesNo? = null,
        private val isevent: YesNo? = null,
        private val abilities: String? = null,
        private val usedelements: String? = null,
        private val maxdmg: String? = null,
        private val pushable: YesNo? = null,
        private val pushobjects: YesNo? = null,
        private val walksaround: String? = null,
        private val walksthrough: String? = null,
        private val paraimmune: YesNo? = null,
        private val senseinvis: YesNo? = null,
        private val physicalDmgMod: Percentage? = null,
        private val holyDmgMod: Percentage? = null,
        private val deathDmgMod: Percentage? = null,
        private val fireDmgMod: Percentage? = null,
        private val energyDmgMod: Percentage? = null,
        private val iceDmgMod: Percentage? = null,
        private val earthDmgMod: Percentage? = null,
        private val drownDmgMod: Percentage? = null,
        private val hpDrainDmgMod: Percentage? = null,
        private val healMod: Percentage? = null,
        private val bestiaryname: String? = null,
        private val bestiarytext: String? = null,
        private val sounds: List<String>? = emptyList(),
        private val behaviour: String? = null,
        private val runsat: String? = null,
        private val speed: String? = null,
        private val strategy: String? = null,
        private val location: String? = null,
        val loot: MutableList<LootItem>? = mutableListOf()
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
            this.copy(loot = mutableListOf())
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Creature::class.java)
    }
}
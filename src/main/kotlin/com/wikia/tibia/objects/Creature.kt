package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Creature(
        val name: String,
        val article: Article? = null,
        val actualname: String? = null,
        val plural: String? = null,
        val implemented: String? = null,
        val notes: String? = null,
        val history: String? = null,
        val status: Status? = null,
        @get:JsonGetter("hp") val hitPoints: String? = null,
        @get:JsonGetter("exp") val experiencePoints: String? = null,
        val armor: String? = null,
        val summon: String? = null,
        val convince: String? = null,
        val illusionable: YesNo? = null,
        val creatureclass: String? = null,
        val primarytype: String? = null,
        val secondarytype: String? = null,
        val bestiaryclass: BestiaryClass? = null,
        val bestiarylevel: BestiaryLevel? = null,
        val occurrence: BestiaryOccurrence? = null,
        val spawntype: List<Spawntype>? = emptyList(),
        val isboss: YesNo? = null,
        val isarenaboss: YesNo? = null,
        val isevent: YesNo? = null,
        val abilities: String? = null,
        val usedelements: String? = null,
        val maxdmg: String? = null,
        val pushable: YesNo? = null,
        val pushobjects: YesNo? = null,
        val walksaround: String? = null,
        val walksthrough: String? = null,
        val paraimmune: YesNo? = null,
        val senseinvis: YesNo? = null,
        val physicalDmgMod: Percentage? = null,
        val holyDmgMod: Percentage? = null,
        val deathDmgMod: Percentage? = null,
        val fireDmgMod: Percentage? = null,
        val energyDmgMod: Percentage? = null,
        val iceDmgMod: Percentage? = null,
        val earthDmgMod: Percentage? = null,
        val drownDmgMod: Percentage? = null,
        val hpDrainDmgMod: Percentage? = null,
        val healMod: Percentage? = null,
        val bestiaryname: String? = null,
        val bestiarytext: String? = null,
        val sounds: List<String>? = emptyList(),
        val behaviour: String? = null,
        val runsat: String? = null,
        val speed: String? = null,
        val strategy: String? = null,
        val location: String? = null,
        val loot: MutableList<LootItem>? = mutableListOf()
) : WikiObject() {

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
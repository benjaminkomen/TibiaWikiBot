package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory
import java.math.BigDecimal

data class Item(
        val name: String,
        val article: Article? = null,
        val actualname: String,
        val plural: String? = null,
        val implemented: String? = null,
        val notes: String? = null,
        val notes2: String? = null,
        val history: String? = null,
        val status: Status? = null,
        val itemid: List<Int>? = emptyList(),
        val marketable: YesNo? = null,
        val usable: YesNo? = null,
        val sprites: String? = null,
        val flavortext: String? = null,
        val ingamestatus: Status? = null,
        val words: String? = null,
        val itemclass: ItemClass,
        val primarytype: String? = null,
        val secondarytype: String? = null,
        val lightcolor: Int? = null,
        val lightradius: Int? = null,
        val levelrequired: Int? = null,
        val vocrequired: String? = null,
        val mlrequired: Int? = null,
        val hands: Hands? = null,
        val type: WeaponType? = null,
        val attack: String? = null,
        @get:JsonGetter("fire_attack") val fireAttack: Int? = null,
        @get:JsonGetter("earth_attack") val earthAttack: Int? = null,
        @get:JsonGetter("ice_attack") val iceAttack: Int? = null,
        @get:JsonGetter("energy_attack") val energyAttack: Int? = null,
        @get:JsonGetter("death_attack") val deathAttack: Int? = null,
        val defense: Int? = null,
        val defensemod: String? = null,
        val imbueslots: Int? = null,
        val imbuements: String? = null,
        val enchantable: YesNo? = null,
        val enchanted: YesNo? = null,
        val range: String? = null,
        @get:JsonGetter("atk_mod") val attackModification: String? = null,
        @get:JsonGetter("hit_mod") val hitpointModification: String? = null,
        val armor: Int? = null,
        val resist: String? = null,
        val charges: Int? = null,
        @get:JsonGetter("crithit_ch") val criticalHitChance: Percentage? = null,
        @get:JsonGetter("critextra_dmg") val criticalHitExtraDamage: Percentage? = null,
        @get:JsonGetter("manaleech_ch") val manaleechChance: Percentage? = null,
        @get:JsonGetter("manaleech_am") val manaleechAmount: Percentage? = null,
        @get:JsonGetter("hpleech_ch") val hitpointLeechChance: Percentage? = null,
        @get:JsonGetter("hpleech_am") val hitpointLeechAmount: Percentage? = null,
        val attrib: String? = null,
        val weight: BigDecimal? = null,
        val stackable: YesNo? = null,
        val pickupable: YesNo? = null,
        val immobile: YesNo? = null,
        val walkable: YesNo? = null,
        val unshootable: YesNo? = null,
        val blockspath: YesNo? = null,
        val rotatable: YesNo? = null,
        val mapcolor: Int? = null,
        val consumable: YesNo? = null,
        val regenseconds: Int? = null,
        val sounds: List<String>? = emptyList(),
        val writable: YesNo? = null,
        val rewritable: YesNo? = null,
        val writechars: Int? = null,
        val hangable: YesNo? = null,
        val holdsliquid: YesNo? = null,
        val mana: Int? = null,
        val damagetype: DamageElement? = null,
        val damage: String? = null,
        val volume: Int? = null,
        val duration: String? = null,
        val destructible: YesNo? = null,
        val droppedby: MutableList<String>? = mutableListOf(),
        val value: String? = null,
        val npcvalue: String? = null,
        val npcprice: String? = null,
        val npcvaluerook: String? = null,
        val npcpricerook: String? = null,
        val buyfrom: String? = null,
        val sellto: String? = null,
        val fansite: String? = null
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(itemclass)) {
            logger.warn("Creature '{}' has no itemclass set", name)
        }
        if (isEmpty(primarytype)) {
            this.copy(primarytype = "?")
            logger.warn("Creature '{}' has no primarytype set", name)
        }
        if (isEmpty(value)) {
            this.copy(value = "?")
        }
        if (isEmpty(npcvalue)) {
            this.copy(npcvalue = "?")
        }
        if (isEmpty(npcprice)) {
            this.copy(npcprice = "?")
        }
        if (isEmpty(buyfrom)) {
            this.copy(buyfrom = "?")
        }
        if (isEmpty(sellto)) {
            this.copy(sellto = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Item::class.java)
    }
}
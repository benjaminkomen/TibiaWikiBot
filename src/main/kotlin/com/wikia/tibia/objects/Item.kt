package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory
import java.math.BigDecimal

data class Item(
        val name: String,
        private val article: Article? = null,
        private val actualname: String,
        private val plural: String? = null,
        private val implemented: String? = null,
        private val notes: String? = null,
        private val notes2: String? = null,
        private val history: String? = null,
        val status: Status? = null,
        private val itemid: List<Int>? = emptyList(),
        private val marketable: YesNo? = null,
        private val usable: YesNo? = null,
        private val sprites: String? = null,
        private val flavortext: String? = null,
        private val ingamestatus: Status? = null,
        private val words: String? = null,
        private val itemclass: ItemClass,
        private val primarytype: String? = null,
        private val secondarytype: String? = null,
        private val lightcolor: Int? = null,
        private val lightradius: Int? = null,
        private val levelrequired: Int? = null,
        private val vocrequired: String? = null,
        private val mlrequired: Int? = null,
        private val hands: Hands? = null,
        private val type: WeaponType? = null,
        private val attack: String? = null,
        @get:JsonGetter("fire_attack") private val fireAttack: Int? = null,
        @get:JsonGetter("earth_attack") private val earthAttack: Int? = null,
        @get:JsonGetter("ice_attack") private val iceAttack: Int? = null,
        @get:JsonGetter("energy_attack") private val energyAttack: Int? = null,
        @get:JsonGetter("death_attack") private val deathAttack: Int? = null,
        private val defense: Int? = null,
        private val defensemod: String? = null,
        private val imbueslots: Int? = null,
        private val imbuements: String? = null,
        private val enchantable: YesNo? = null,
        private val enchanted: YesNo? = null,
        private val range: String? = null,
        @get:JsonGetter("atk_mod") private val attackModification: String? = null,
        @get:JsonGetter("hit_mod") private val hitpointModification: String? = null,
        private val armor: Int? = null,
        private val resist: String? = null,
        private val charges: Int? = null,
        @get:JsonGetter("crithit_ch") private val criticalHitChance: Percentage? = null,
        @get:JsonGetter("critextra_dmg") private val criticalHitExtraDamage: Percentage? = null,
        @get:JsonGetter("manaleech_ch") private val manaleechChance: Percentage? = null,
        @get:JsonGetter("manaleech_am") private val manaleechAmount: Percentage? = null,
        @get:JsonGetter("hpleech_ch") private val hitpointLeechChance: Percentage? = null,
        @get:JsonGetter("hpleech_am") private val hitpointLeechAmount: Percentage? = null,
        private val attrib: String? = null,
        private val weight: BigDecimal? = null,
        private val stackable: YesNo? = null,
        private val pickupable: YesNo? = null,
        private val immobile: YesNo? = null,
        private val walkable: YesNo? = null,
        private val unshootable: YesNo? = null,
        private val blockspath: YesNo? = null,
        private val rotatable: YesNo? = null,
        private val mapcolor: Int? = null,
        private val consumable: YesNo? = null,
        private val regenseconds: Int? = null,
        private val sounds: List<String>? = emptyList(),
        private val writable: YesNo? = null,
        private val rewritable: YesNo? = null,
        private val writechars: Int? = null,
        private val hangable: YesNo? = null,
        private val holdsliquid: YesNo? = null,
        private val mana: Int? = null,
        private val damagetype: DamageElement? = null,
        private val damage: String? = null,
        private val volume: Int? = null,
        private val duration: String? = null,
        private val destructible: YesNo? = null,
        val droppedby: MutableList<String>? = mutableListOf(),
        private val value: String? = null,
        private val npcvalue: String? = null,
        private val npcprice: String? = null,
        private val npcvaluerook: String? = null,
        private val npcpricerook: String? = null,
        private val buyfrom: String? = null,
        private val sellto: String? = null,
        private val fansite: String? = null
): WikiObject() {

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
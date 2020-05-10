package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.*
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory
import java.math.BigDecimal

data class Item(
        private val name: String,
        private val article: Article,
        private val actualname: String,
        private val plural: String,
        private val implemented: String?,
        private val notes: String,
        private val notes2: String,
        private val history: String,
        private val status: Status,
        private val itemid: List<Int>,
        private val marketable: YesNo,
        private val usable: YesNo,
        private val sprites: String,
        private val flavortext: String,
        private val ingamestatus: Status,
        private val words: String,
        private val itemclass: ItemClass,
        private val primarytype: String?,
        private val secondarytype: String,
        private val lightcolor: Int,
        private val lightradius: Int,
        private val levelrequired: Int,
        private val vocrequired: String,
        private val mlrequired: Int,
        private val hands: Hands,
        private val type: WeaponType,
        private val attack: String,
        @get:JsonGetter("fire_attack") private val fireAttack: Int,
        @get:JsonGetter("earth_attack") private val earthAttack: Int,
        @get:JsonGetter("ice_attack") private val iceAttack: Int,
        @get:JsonGetter("energy_attack") private val energyAttack: Int,
        @get:JsonGetter("death_attack") private val deathAttack: Int,
        private val defense: Int,
        private val defensemod: String,
        private val imbueslots: Int,
        private val imbuements: String,
        private val enchantable: YesNo,
        private val enchanted: YesNo,
        private val range: String,
        @get:JsonGetter("atk_mod") private val attackModification: String,
        @get:JsonGetter("hit_mod") private val hitpointModification: String,
        private val armor: Int,
        private val resist: String,
        private val charges: Int,
        @get:JsonGetter("crithit_ch") private val criticalHitChance: Percentage,
        @get:JsonGetter("critextra_dmg") private val criticalHitExtraDamage: Percentage,
        @get:JsonGetter("manaleech_ch") private val manaleechChance: Percentage,
        @get:JsonGetter("manaleech_am") private val manaleechAmount: Percentage,
        @get:JsonGetter("hpleech_ch") private val hitpointLeechChance: Percentage,
        @get:JsonGetter("hpleech_am") private val hitpointLeechAmount: Percentage,
        private val attrib: String,
        private val weight: BigDecimal,
        private val stackable: YesNo,
        private val pickupable: YesNo,
        private val immobile: YesNo,
        private val walkable: YesNo,
        private val unshootable: YesNo,
        private val blockspath: YesNo,
        private val rotatable: YesNo,
        private val mapcolor: Int,
        private val consumable: YesNo,
        private val regenseconds: Int,
        private val sounds: List<String>,
        private val writable: YesNo,
        private val rewritable: YesNo,
        private val writechars: Int,
        private val hangable: YesNo,
        private val holdsliquid: YesNo,
        private val mana: Int,
        private val damagetype: DamageElement,
        private val damage: String,
        private val volume: Int,
        private val duration: String,
        private val destructible: YesNo,
        private val droppedby: List<String>,
        private val value: String?,
        private val npcvalue: String?,
        private val npcprice: String?,
        private val npcvaluerook: String,
        private val npcpricerook: String,
        private val buyfrom: String?,
        private val sellto: String?,
        private val fansite: String
) {

    fun setDefaultValues() {
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
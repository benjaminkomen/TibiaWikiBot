package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.Article
import com.wikia.tibia.enums.DamageElement
import com.wikia.tibia.enums.Hands
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.WeaponType
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory
import java.math.BigDecimal

data class TibiaObject(
    val name: String,
    val article: Article? = null,
    val actualname: String,
    val plural: String? = null,
    val itemid: List<Int>? = emptyList(),
    val objectclass: String,
    val secondarytype: String? = null,
    val tertiarytype: String? = null,
    val flavortext: String? = null,
    val words: String? = null,
    val sounds: List<String>? = emptyList(),
    val implemented: String? = null,
    val lightradius: Int? = null,
    val lightcolor: Int? = null,
    val volume: Int? = null,
    val destructable: YesNo? = null,
    val immobile: YesNo? = null,
    val walkable: YesNo? = null,
    val walkingspeed: Int? = null,
    val unshootable: YesNo? = null,
    val blockspath: YesNo? = null,
    val pickupable: YesNo? = null,
    val holdsliquid: YesNo? = null,
    val usable: YesNo? = null,
    val writable: YesNo? = null,
    val rewritable: YesNo? = null,
    val writechars: Int? = null,
    val levelrequired: Int? = null,
    val vocrequired: String? = null,
    val mlrequired: Int? = null,
    val hands: Hands? = null,
    val weapontype: WeaponType? = null,
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
    @get:JsonGetter("crithit_ch") val criticalHitChance: Percentage? = null,
    @get:JsonGetter("critextra_dmg") val criticalHitExtraDamage: Percentage? = null,
    @get:JsonGetter("manaleech_ch") val manaleechChance: Percentage? = null,
    @get:JsonGetter("manaleech_am") val manaleechAmount: Percentage? = null,
    @get:JsonGetter("hpleech_ch") val hitpointLeechChance: Percentage? = null,
    @get:JsonGetter("hpleech_am") val hitpointLeechAmount: Percentage? = null,
    val manacost: Int? = null,
    val damagetype: DamageElement? = null,
    val damagerange: String? = null,
    val attrib: String? = null,
    val charges: Int? = null,
    val armor: Int? = null,
    val resist: String? = null,
    val weight: BigDecimal? = null,
    val stackable: YesNo? = null,
    val marketable: YesNo? = null,
    val consumable: YesNo? = null,
    val regenseconds: Int? = null,
    val hangable: YesNo? = null,
    val duration: String? = null,
    val destructible: YesNo? = null,
    val rotatable: YesNo? = null,
    val mapcolor: Int? = null,
    val droppedby: MutableList<String>? = mutableListOf(),
    val value: String? = null,
    val storevalue: String? = null,
    val npcvalue: String? = null,
    val npcprice: String? = null,
    val npcvaluerook: String? = null,
    val npcpricerook: String? = null,
    val buyfrom: String? = null,
    val sellto: String? = null,
    val fansite: String? = null,
    val location: String? = null,
    val notes: String? = null,
    val notes2: String? = null,
    val history: String? = null,
    val status: Status? = null,
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(objectclass)) {
            this.copy(objectclass = "")
            logger.warn("Object '{}' has no objectclass set", name)
        }
        if (isEmpty(walkable)) {
            this.copy(walkable = YesNo.UNKNOWN)
        }
        if (isEmpty(location)) {
            this.copy(location = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TibiaObject::class.java)
    }
}

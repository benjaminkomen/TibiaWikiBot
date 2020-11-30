package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.SpellSubclass
import com.wikia.tibia.enums.SpellType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Spell(
    val name: String,
    val implemented: String,
    val notes: String,
    val history: String,
    val status: Status,
    val spellid: String,
    val type: SpellType,
    val subclass: SpellSubclass,
    val runegroup: SpellSubclass,
    val damagetype: String,
    val words: String,
    val mana: Int,
    val cooldown: Int,
    val cooldowngroup: Int,
    val cooldowngroup2: Int,
    val levelrequired: Int,
    val premium: YesNo,
    val promotion: YesNo,
    val soul: Int,
    val zoltanonly: YesNo,
    val partyspell: YesNo,
    val specialspell: YesNo,
    val conjurespell: YesNo,
    val voc: String,
    @get:JsonGetter("d-abd") val druidAbDendriel: String,
    @get:JsonGetter("d-ank") val druidAnkrahmun: String,
    @get:JsonGetter("d-car") val druidCarlin: String,
    @get:JsonGetter("d-dar") val druidDarashia: String,
    @get:JsonGetter("d-edr") val druidEdron: String,
    @get:JsonGetter("d-kaz") val druidKazordoon: String,
    @get:JsonGetter("d-lib") val druidLibertyBay: String,
    @get:JsonGetter("d-por") val druidPortHope: String,
    @get:JsonGetter("d-rat") val druidRathleton: String,
    @get:JsonGetter("d-sva") val druidSvargrond: String,
    @get:JsonGetter("d-tha") val druidThais: String,
    @get:JsonGetter("d-ven") val druidVenore: String,
    @get:JsonGetter("d-yal") val druidYalahar: String,
    @get:JsonGetter("k-abd") val knightAbDendriel: String,
    @get:JsonGetter("k-ank") val knightAnkrahmun: String,
    @get:JsonGetter("k-car") val knightCarlin: String,
    @get:JsonGetter("k-dar") val knightDarashia: String,
    @get:JsonGetter("k-edr") val knightEdron: String,
    @get:JsonGetter("k-kaz") val knightKazordoon: String,
    @get:JsonGetter("k-lib") val knightLibertyBay: String,
    @get:JsonGetter("k-por") val knightPortHope: String,
    @get:JsonGetter("k-rat") val knightRathleton: String,
    @get:JsonGetter("k-sva") val knightSvargrond: String,
    @get:JsonGetter("k-tha") val knightThais: String,
    @get:JsonGetter("k-ven") val knightVenore: String,
    @get:JsonGetter("k-yal") val knightYalahar: String,
    @get:JsonGetter("p-abd") val paladinAbDendriel: String,
    @get:JsonGetter("p-ank") val paladinAnkrahmun: String,
    @get:JsonGetter("p-car") val paladinCarlin: String,
    @get:JsonGetter("p-dar") val paladinDarashia: String,
    @get:JsonGetter("p-edr") val paladinEdron: String,
    @get:JsonGetter("p-kaz") val paladinKazordoon: String,
    @get:JsonGetter("p-lib") val paladinLibertyBay: String,
    @get:JsonGetter("p-por") val paladinPortHope: String,
    @get:JsonGetter("p-rat") val paladinRathleton: String,
    @get:JsonGetter("p-sva") val paladinSvargrond: String,
    @get:JsonGetter("p-tha") val paladinThais: String,
    @get:JsonGetter("p-ven") val paladinVenore: String,
    @get:JsonGetter("p-yal") val paladinYalahar: String,
    @get:JsonGetter("s-abd") val sorcererAbDendriel: String,
    @get:JsonGetter("s-ank") val sorcererAnkrahmun: String,
    @get:JsonGetter("s-car") val sorcererCarlin: String,
    @get:JsonGetter("s-dar") val sorcererDarashia: String,
    @get:JsonGetter("s-edr") val sorcererEdron: String,
    @get:JsonGetter("s-kaz") val sorcererKazordoon: String,
    @get:JsonGetter("s-lib") val sorcererLibertyBay: String,
    @get:JsonGetter("s-por") val sorcererPortHope: String,
    @get:JsonGetter("s-rat") val sorcererRathleton: String,
    @get:JsonGetter("s-sva") val sorcererSvargrond: String,
    @get:JsonGetter("s-tha") val sorcererThais: String,
    @get:JsonGetter("s-ven") val sorcererVenore: String,
    @get:JsonGetter("s-yal") val sorcererYalahar: String,
    val spellcost: Int,
    val effect: String,
    val animation: String
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(type)) {
            logger.warn("Spell '{}' has no type set", name)
        }
        if (isEmpty(subclass)) {
            logger.warn("Spell '{}' has no subclass set", name)
        }
        if (isEmpty(words)) {
            this.copy(words = "?")
        }
        if (isEmpty(premium)) {
            this.copy(premium = YesNo.UNKNOWN)
        }
        if (isEmpty(voc)) {
            this.copy(voc = "?")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Spell::class.java)
    }
}

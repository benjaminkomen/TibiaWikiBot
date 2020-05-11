package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.SpellSubclass
import com.wikia.tibia.enums.SpellType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Spell(
        private val name: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val spellid: String,
        private val type: SpellType,
        private val subclass: SpellSubclass,
        private val runegroup: SpellSubclass,
        private val damagetype: String,
        private val words: String,
        private val mana: Int,
        private val cooldown: Int,
        private val cooldowngroup: Int,
        private val cooldowngroup2: Int,
        private val levelrequired: Int,
        private val premium: YesNo,
        private val promotion: YesNo,
        private val soul: Int,
        private val zoltanonly: YesNo,
        private val partyspell: YesNo,
        private val specialspell: YesNo,
        private val conjurespell: YesNo,
        private val voc: String,
        @get:JsonGetter("d-abd") private val druidAbDendriel: String,
        @get:JsonGetter("d-ank") private val druidAnkrahmun: String,
        @get:JsonGetter("d-car") private val druidCarlin: String,
        @get:JsonGetter("d-dar") private val druidDarashia: String,
        @get:JsonGetter("d-edr") private val druidEdron: String,
        @get:JsonGetter("d-kaz") private val druidKazordoon: String,
        @get:JsonGetter("d-lib") private val druidLibertyBay: String,
        @get:JsonGetter("d-por") private val druidPortHope: String,
        @get:JsonGetter("d-rat") private val druidRathleton: String,
        @get:JsonGetter("d-sva") private val druidSvargrond: String,
        @get:JsonGetter("d-tha") private val druidThais: String,
        @get:JsonGetter("d-ven") private val druidVenore: String,
        @get:JsonGetter("d-yal") private val druidYalahar: String,
        @get:JsonGetter("k-abd") private val knightAbDendriel: String,
        @get:JsonGetter("k-ank") private val knightAnkrahmun: String,
        @get:JsonGetter("k-car") private val knightCarlin: String,
        @get:JsonGetter("k-dar") private val knightDarashia: String,
        @get:JsonGetter("k-edr") private val knightEdron: String,
        @get:JsonGetter("k-kaz") private val knightKazordoon: String,
        @get:JsonGetter("k-lib") private val knightLibertyBay: String,
        @get:JsonGetter("k-por") private val knightPortHope: String,
        @get:JsonGetter("k-rat") private val knightRathleton: String,
        @get:JsonGetter("k-sva") private val knightSvargrond: String,
        @get:JsonGetter("k-tha") private val knightThais: String,
        @get:JsonGetter("k-ven") private val knightVenore: String,
        @get:JsonGetter("k-yal") private val knightYalahar: String,
        @get:JsonGetter("p-abd") private val paladinAbDendriel: String,
        @get:JsonGetter("p-ank") private val paladinAnkrahmun: String,
        @get:JsonGetter("p-car") private val paladinCarlin: String,
        @get:JsonGetter("p-dar") private val paladinDarashia: String,
        @get:JsonGetter("p-edr") private val paladinEdron: String,
        @get:JsonGetter("p-kaz") private val paladinKazordoon: String,
        @get:JsonGetter("p-lib") private val paladinLibertyBay: String,
        @get:JsonGetter("p-por") private val paladinPortHope: String,
        @get:JsonGetter("p-rat") private val paladinRathleton: String,
        @get:JsonGetter("p-sva") private val paladinSvargrond: String,
        @get:JsonGetter("p-tha") private val paladinThais: String,
        @get:JsonGetter("p-ven") private val paladinVenore: String,
        @get:JsonGetter("p-yal") private val paladinYalahar: String,
        @get:JsonGetter("s-abd") private val sorcererAbDendriel: String,
        @get:JsonGetter("s-ank") private val sorcererAnkrahmun: String,
        @get:JsonGetter("s-car") private val sorcererCarlin: String,
        @get:JsonGetter("s-dar") private val sorcererDarashia: String,
        @get:JsonGetter("s-edr") private val sorcererEdron: String,
        @get:JsonGetter("s-kaz") private val sorcererKazordoon: String,
        @get:JsonGetter("s-lib") private val sorcererLibertyBay: String,
        @get:JsonGetter("s-por") private val sorcererPortHope: String,
        @get:JsonGetter("s-rat") private val sorcererRathleton: String,
        @get:JsonGetter("s-sva") private val sorcererSvargrond: String,
        @get:JsonGetter("s-tha") private val sorcererThais: String,
        @get:JsonGetter("s-ven") private val sorcererVenore: String,
        @get:JsonGetter("s-yal") private val sorcererYalahar: String,
        private val spellcost: Int,
        private val effect: String,
        private val animation: String
): WikiObject() {

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
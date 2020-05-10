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

        @get:JsonGetter("d-yal")
val druidYalahar: String,

@get:JsonGetter("k-abd")
val knightAbDendriel: String,

@get:JsonGetter("k-ank")
val knightAnkrahmun: String,

@get:JsonGetter("k-car")
val knightCarlin: String,

@get:JsonGetter("k-dar")
val knightDarashia: String,

@get:JsonGetter("k-edr")
val knightEdron: String,

@get:JsonGetter("k-kaz")
val knightKazordoon: String,

@get:JsonGetter("k-lib")
val knightLibertyBay: String,

@get:JsonGetter("k-por")
val knightPortHope: String,

@get:JsonGetter("k-rat")
val knightRathleton: String,

@get:JsonGetter("k-sva")
val knightSvargrond: String,

@get:JsonGetter("k-tha")
val knightThais: String,

@get:JsonGetter("k-ven")
val knightVenore: String,

@get:JsonGetter("k-yal")
val knightYalahar: String,

@get:JsonGetter("p-abd")
val paladinAbDendriel: String,

@get:JsonGetter("p-ank")
val paladinAnkrahmun: String

@get:JsonGetter("p-car")
val paladinCarlin: String

@get:JsonGetter("p-dar")
val paladinDarashia: String

@get:JsonGetter("p-edr")
val paladinEdron: String

@get:JsonGetter("p-kaz")
val paladinKazordoon: String

@get:JsonGetter("p-lib")
val paladinLibertyBay: String

@get:JsonGetter("p-por")
val paladinPortHope: String

@get:JsonGetter("p-rat")
val paladinRathleton: String

@get:JsonGetter("p-sva")
val paladinSvargrond: String

@get:JsonGetter("p-tha")
val paladinThais: String

@get:JsonGetter("p-ven")
val paladinVenore: String

@get:JsonGetter("p-yal")
val paladinYalahar: String

@get:JsonGetter("s-abd")
val sorcererAbDendriel: String

@get:JsonGetter("s-ank")
val sorcererAnkrahmun: String

@get:JsonGetter("s-car")
val sorcererCarlin: String

@get:JsonGetter("s-dar")
val sorcererDarashia: String

@get:JsonGetter("s-edr")
val sorcererEdron: String

@get:JsonGetter("s-kaz")
val sorcererKazordoon: String

@get:JsonGetter("s-lib")
val sorcererLibertyBay: String

@get:JsonGetter("s-por")
val sorcererPortHope: String

@get:JsonGetter("s-rat")
val sorcererRathleton: String

@get:JsonGetter("s-sva")
val sorcererSvargrond: String

@get:JsonGetter("s-tha")
val sorcererThais: String

@get:JsonGetter("s-ven")
val sorcererVenore: String

@get:JsonGetter("s-yal")
val sorcererYalahar: String
spellcost: Int,
effect: String,
animation: String
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(type)) {
            logger.warn("Spell '{}' has no type set", name)
        }
        if (isEmpty(subclass)) {
            logger.warn("Spell '{}' has no subclass set", name)
        }
        if (isEmpty(words)) {
            words = "?"
        }
        if (isEmpty(premium)) {
            premium = YesNo.UNKNOWN
        }
        if (isEmpty(voc)) {
            voc = "?"
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Spell::class.java)
    }
}
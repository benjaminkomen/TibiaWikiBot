package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*
import org.slf4j.LoggerFactory

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Spell @Builder private constructor(name: String, implemented: String, notes: String, history: String, status: Status, private val spellid: String, type: SpellType,
                                         subclass: SpellSubclass, runegroup: SpellSubclass, damagetype: String, words: String, mana: Int,
                                         cooldown: Int, cooldowngroup: Int, cooldowngroup2: Int, levelrequired: Int, premium: YesNo,
                                         promotion: YesNo, soul: Int, zoltanonly: YesNo, partyspell: YesNo, specialspell: YesNo,
                                         conjurespell: YesNo, voc: String, druidAbDendriel: String, druidAnkrahmun: String, druidCarlin: String,
                                         druidDarashia: String, druidEdron: String, druidKazordoon: String, druidLibertyBay: String,
                                         druidPortHope: String, druidRathleton: String, druidSvargrond: String, druidThais: String,
                                         druidVenore: String, druidYalahar: String, knightAbDendriel: String, knightAnkrahmun: String,
                                         knightCarlin: String, knightDarashia: String, knightEdron: String, knightKazordoon: String,
                                         knightLibertyBay: String, knightPortHope: String, knightRathleton: String, knightSvargrond: String,
                                         knightThais: String, knightVenore: String, knightYalahar: String, paladinAbDendriel: String,
                                         paladinAnkrahmun: String, paladinCarlin: String, paladinDarashia: String, paladinEdron: String,
                                         paladinKazordoon: String, paladinLibertyBay: String, paladinPortHope: String, paladinRathleton: String,
                                         paladinSvargrond: String, paladinThais: String, paladinVenore: String, paladinYalahar: String,
                                         sorcererAbDendriel: String, sorcererAnkrahmun: String, sorcererCarlin: String, sorcererDarashia: String,
                                         sorcererEdron: String, sorcererKazordoon: String, sorcererLibertyBay: String, sorcererPortHope: String,
                                         sorcererRathleton: String, sorcererSvargrond: String, sorcererThais: String, sorcererVenore: String,
                                         sorcererYalahar: String, spellcost: Int, effect: String, animation: String) : WikiObject(name, null, null, null, implemented, notes, history, status) {
    private val type: SpellType
    private val subclass: SpellSubclass
    private val runegroup: SpellSubclass
    private val damagetype: String
    private var words: String
    private val mana: Int
    private val cooldown: Int
    private val cooldowngroup: Int
    private val cooldowngroup2: Int
    private val levelrequired: Int
    private var premium: YesNo
    private val promotion: YesNo
    private val soul: Int
    private val zoltanonly: YesNo
    private val partyspell: YesNo
    private val specialspell: YesNo
    private val conjurespell: YesNo
    private var voc: String

    @get:JsonGetter("d-abd")
    private val druidAbDendriel: String

    @get:JsonGetter("d-ank")
    val druidAnkrahmun: String

    @get:JsonGetter("d-car")
    val druidCarlin: String

    @get:JsonGetter("d-dar")
    val druidDarashia: String

    @get:JsonGetter("d-edr")
    val druidEdron: String

    @get:JsonGetter("d-kaz")
    val druidKazordoon: String

    @get:JsonGetter("d-lib")
    val druidLibertyBay: String

    @get:JsonGetter("d-por")
    val druidPortHope: String

    @get:JsonGetter("d-rat")
    val druidRathleton: String

    @get:JsonGetter("d-sva")
    val druidSvargrond: String

    @get:JsonGetter("d-tha")
    val druidThais: String

    @get:JsonGetter("d-ven")
    val druidVenore: String

    @get:JsonGetter("d-yal")
    val druidYalahar: String

    @get:JsonGetter("k-abd")
    val knightAbDendriel: String

    @get:JsonGetter("k-ank")
    val knightAnkrahmun: String

    @get:JsonGetter("k-car")
    val knightCarlin: String

    @get:JsonGetter("k-dar")
    val knightDarashia: String

    @get:JsonGetter("k-edr")
    val knightEdron: String

    @get:JsonGetter("k-kaz")
    val knightKazordoon: String

    @get:JsonGetter("k-lib")
    val knightLibertyBay: String

    @get:JsonGetter("k-por")
    val knightPortHope: String

    @get:JsonGetter("k-rat")
    val knightRathleton: String

    @get:JsonGetter("k-sva")
    val knightSvargrond: String

    @get:JsonGetter("k-tha")
    val knightThais: String

    @get:JsonGetter("k-ven")
    val knightVenore: String

    @get:JsonGetter("k-yal")
    val knightYalahar: String

    @get:JsonGetter("p-abd")
    val paladinAbDendriel: String

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
    private val spellcost: Int
    private val effect: String
    private val animation: String

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(type)) {
            LOG.warn("Spell '{}' has no type set", name)
        }
        if (isEmpty(subclass)) {
            LOG.warn("Spell '{}' has no subclass set", name)
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
        private val LOG = LoggerFactory.getLogger(Spell::class.java)
    }

    init {
        this.type = type
        this.subclass = subclass
        this.runegroup = runegroup
        this.damagetype = damagetype
        this.words = words
        this.mana = mana
        this.cooldown = cooldown
        this.cooldowngroup = cooldowngroup
        this.cooldowngroup2 = cooldowngroup2
        this.levelrequired = levelrequired
        this.premium = premium
        this.promotion = promotion
        this.soul = soul
        this.zoltanonly = zoltanonly
        this.partyspell = partyspell
        this.specialspell = specialspell
        this.conjurespell = conjurespell
        this.voc = voc
        this.druidAbDendriel = druidAbDendriel
        this.druidAnkrahmun = druidAnkrahmun
        this.druidCarlin = druidCarlin
        this.druidDarashia = druidDarashia
        this.druidEdron = druidEdron
        this.druidKazordoon = druidKazordoon
        this.druidLibertyBay = druidLibertyBay
        this.druidPortHope = druidPortHope
        this.druidRathleton = druidRathleton
        this.druidSvargrond = druidSvargrond
        this.druidThais = druidThais
        this.druidVenore = druidVenore
        this.druidYalahar = druidYalahar
        this.knightAbDendriel = knightAbDendriel
        this.knightAnkrahmun = knightAnkrahmun
        this.knightCarlin = knightCarlin
        this.knightDarashia = knightDarashia
        this.knightEdron = knightEdron
        this.knightKazordoon = knightKazordoon
        this.knightLibertyBay = knightLibertyBay
        this.knightPortHope = knightPortHope
        this.knightRathleton = knightRathleton
        this.knightSvargrond = knightSvargrond
        this.knightThais = knightThais
        this.knightVenore = knightVenore
        this.knightYalahar = knightYalahar
        this.paladinAbDendriel = paladinAbDendriel
        this.paladinAnkrahmun = paladinAnkrahmun
        this.paladinCarlin = paladinCarlin
        this.paladinDarashia = paladinDarashia
        this.paladinEdron = paladinEdron
        this.paladinKazordoon = paladinKazordoon
        this.paladinLibertyBay = paladinLibertyBay
        this.paladinPortHope = paladinPortHope
        this.paladinRathleton = paladinRathleton
        this.paladinSvargrond = paladinSvargrond
        this.paladinThais = paladinThais
        this.paladinVenore = paladinVenore
        this.paladinYalahar = paladinYalahar
        this.sorcererAbDendriel = sorcererAbDendriel
        this.sorcererAnkrahmun = sorcererAnkrahmun
        this.sorcererCarlin = sorcererCarlin
        this.sorcererDarashia = sorcererDarashia
        this.sorcererEdron = sorcererEdron
        this.sorcererKazordoon = sorcererKazordoon
        this.sorcererLibertyBay = sorcererLibertyBay
        this.sorcererPortHope = sorcererPortHope
        this.sorcererRathleton = sorcererRathleton
        this.sorcererSvargrond = sorcererSvargrond
        this.sorcererThais = sorcererThais
        this.sorcererVenore = sorcererVenore
        this.sorcererYalahar = sorcererYalahar
        this.spellcost = spellcost
        this.effect = effect
        this.animation = animation
    }
}
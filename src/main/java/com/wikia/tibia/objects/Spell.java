package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.SpellSubclass;
import com.wikia.tibia.enums.SpellType;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Spell extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Spell.class);

    private String spellid;
    private SpellType type;
    private SpellSubclass subclass;
    private SpellSubclass runegroup;
    private String damagetype;
    private String words;
    private Integer mana;
    private Integer cooldown;
    private Integer cooldowngroup;
    private Integer cooldowngroup2;
    private Integer levelrequired;
    private YesNo premium;
    private YesNo promotion;
    private Integer soul;
    private YesNo zoltanonly;
    private YesNo partyspell;
    private YesNo specialspell;
    private YesNo conjurespell;
    private String voc;
    private String druidAbDendriel;
    private String druidAnkrahmun;
    private String druidCarlin;
    private String druidDarashia;
    private String druidEdron;
    private String druidKazordoon;
    private String druidLibertyBay;
    private String druidPortHope;
    private String druidRathleton;
    private String druidSvargrond;
    private String druidThais;
    private String druidVenore;
    private String druidYalahar;
    private String knightAbDendriel;
    private String knightAnkrahmun;
    private String knightCarlin;
    private String knightDarashia;
    private String knightEdron;
    private String knightKazordoon;
    private String knightLibertyBay;
    private String knightPortHope;
    private String knightRathleton;
    private String knightSvargrond;
    private String knightThais;
    private String knightVenore;
    private String knightYalahar;
    private String paladinAbDendriel;
    private String paladinAnkrahmun;
    private String paladinCarlin;
    private String paladinDarashia;
    private String paladinEdron;
    private String paladinKazordoon;
    private String paladinLibertyBay;
    private String paladinPortHope;
    private String paladinRathleton;
    private String paladinSvargrond;
    private String paladinThais;
    private String paladinVenore;
    private String paladinYalahar;
    private String sorcererAbDendriel;
    private String sorcererAnkrahmun;
    private String sorcererCarlin;
    private String sorcererDarashia;
    private String sorcererEdron;
    private String sorcererKazordoon;
    private String sorcererLibertyBay;
    private String sorcererPortHope;
    private String sorcererRathleton;
    private String sorcererSvargrond;
    private String sorcererThais;
    private String sorcererVenore;
    private String sorcererYalahar;
    private Integer spellcost;
    private String effect;
    private String animation;

    @Builder
    private Spell(String name, String implemented, String notes, String history, Status status, String spellid, SpellType type,
                  SpellSubclass subclass, SpellSubclass runegroup, String damagetype, String words, Integer mana,
                  Integer cooldown, Integer cooldowngroup, Integer cooldowngroup2, Integer levelrequired, YesNo premium,
                  YesNo promotion, Integer soul, YesNo zoltanonly, YesNo partyspell, YesNo specialspell,
                  YesNo conjurespell, String voc, String druidAbDendriel, String druidAnkrahmun, String druidCarlin,
                  String druidDarashia, String druidEdron, String druidKazordoon, String druidLibertyBay,
                  String druidPortHope, String druidRathleton, String druidSvargrond, String druidThais,
                  String druidVenore, String druidYalahar, String knightAbDendriel, String knightAnkrahmun,
                  String knightCarlin, String knightDarashia, String knightEdron, String knightKazordoon,
                  String knightLibertyBay, String knightPortHope, String knightRathleton, String knightSvargrond,
                  String knightThais, String knightVenore, String knightYalahar, String paladinAbDendriel,
                  String paladinAnkrahmun, String paladinCarlin, String paladinDarashia, String paladinEdron,
                  String paladinKazordoon, String paladinLibertyBay, String paladinPortHope, String paladinRathleton,
                  String paladinSvargrond, String paladinThais, String paladinVenore, String paladinYalahar,
                  String sorcererAbDendriel, String sorcererAnkrahmun, String sorcererCarlin, String sorcererDarashia,
                  String sorcererEdron, String sorcererKazordoon, String sorcererLibertyBay, String sorcererPortHope,
                  String sorcererRathleton, String sorcererSvargrond, String sorcererThais, String sorcererVenore,
                  String sorcererYalahar, Integer spellcost, String effect, String animation) {
        super(name, null, null, null, implemented, notes, history, status);
        this.spellid = spellid;
        this.type = type;
        this.subclass = subclass;
        this.runegroup = runegroup;
        this.damagetype = damagetype;
        this.words = words;
        this.mana = mana;
        this.cooldown = cooldown;
        this.cooldowngroup = cooldowngroup;
        this.cooldowngroup2 = cooldowngroup2;
        this.levelrequired = levelrequired;
        this.premium = premium;
        this.promotion = promotion;
        this.soul = soul;
        this.zoltanonly = zoltanonly;
        this.partyspell = partyspell;
        this.specialspell = specialspell;
        this.conjurespell = conjurespell;
        this.voc = voc;
        this.druidAbDendriel = druidAbDendriel;
        this.druidAnkrahmun = druidAnkrahmun;
        this.druidCarlin = druidCarlin;
        this.druidDarashia = druidDarashia;
        this.druidEdron = druidEdron;
        this.druidKazordoon = druidKazordoon;
        this.druidLibertyBay = druidLibertyBay;
        this.druidPortHope = druidPortHope;
        this.druidRathleton = druidRathleton;
        this.druidSvargrond = druidSvargrond;
        this.druidThais = druidThais;
        this.druidVenore = druidVenore;
        this.druidYalahar = druidYalahar;
        this.knightAbDendriel = knightAbDendriel;
        this.knightAnkrahmun = knightAnkrahmun;
        this.knightCarlin = knightCarlin;
        this.knightDarashia = knightDarashia;
        this.knightEdron = knightEdron;
        this.knightKazordoon = knightKazordoon;
        this.knightLibertyBay = knightLibertyBay;
        this.knightPortHope = knightPortHope;
        this.knightRathleton = knightRathleton;
        this.knightSvargrond = knightSvargrond;
        this.knightThais = knightThais;
        this.knightVenore = knightVenore;
        this.knightYalahar = knightYalahar;
        this.paladinAbDendriel = paladinAbDendriel;
        this.paladinAnkrahmun = paladinAnkrahmun;
        this.paladinCarlin = paladinCarlin;
        this.paladinDarashia = paladinDarashia;
        this.paladinEdron = paladinEdron;
        this.paladinKazordoon = paladinKazordoon;
        this.paladinLibertyBay = paladinLibertyBay;
        this.paladinPortHope = paladinPortHope;
        this.paladinRathleton = paladinRathleton;
        this.paladinSvargrond = paladinSvargrond;
        this.paladinThais = paladinThais;
        this.paladinVenore = paladinVenore;
        this.paladinYalahar = paladinYalahar;
        this.sorcererAbDendriel = sorcererAbDendriel;
        this.sorcererAnkrahmun = sorcererAnkrahmun;
        this.sorcererCarlin = sorcererCarlin;
        this.sorcererDarashia = sorcererDarashia;
        this.sorcererEdron = sorcererEdron;
        this.sorcererKazordoon = sorcererKazordoon;
        this.sorcererLibertyBay = sorcererLibertyBay;
        this.sorcererPortHope = sorcererPortHope;
        this.sorcererRathleton = sorcererRathleton;
        this.sorcererSvargrond = sorcererSvargrond;
        this.sorcererThais = sorcererThais;
        this.sorcererVenore = sorcererVenore;
        this.sorcererYalahar = sorcererYalahar;
        this.spellcost = spellcost;
        this.effect = effect;
        this.animation = animation;
    }

    @JsonGetter("d-abd")
    private String getDruidAbDendriel() {
        return druidAbDendriel;
    }

    @JsonGetter("d-ank")
    public String getDruidAnkrahmun() {
        return druidAnkrahmun;
    }

    @JsonGetter("d-car")
    public String getDruidCarlin() {
        return druidCarlin;
    }

    @JsonGetter("d-dar")
    public String getDruidDarashia() {
        return druidDarashia;
    }

    @JsonGetter("d-edr")
    public String getDruidEdron() {
        return druidEdron;
    }

    @JsonGetter("d-kaz")
    public String getDruidKazordoon() {
        return druidKazordoon;
    }

    @JsonGetter("d-lib")
    public String getDruidLibertyBay() {
        return druidLibertyBay;
    }

    @JsonGetter("d-por")
    public String getDruidPortHope() {
        return druidPortHope;
    }

    @JsonGetter("d-rat")
    public String getDruidRathleton() {
        return druidRathleton;
    }

    @JsonGetter("d-sva")
    public String getDruidSvargrond() {
        return druidSvargrond;
    }

    @JsonGetter("d-tha")
    public String getDruidThais() {
        return druidThais;
    }

    @JsonGetter("d-ven")
    public String getDruidVenore() {
        return druidVenore;
    }

    @JsonGetter("d-yal")
    public String getDruidYalahar() {
        return druidYalahar;
    }

    @JsonGetter("k-abd")
    public String getKnightAbDendriel() {
        return knightAbDendriel;
    }

    @JsonGetter("k-ank")
    public String getKnightAnkrahmun() {
        return knightAnkrahmun;
    }

    @JsonGetter("k-car")
    public String getKnightCarlin() {
        return knightCarlin;
    }

    @JsonGetter("k-dar")
    public String getKnightDarashia() {
        return knightDarashia;
    }

    @JsonGetter("k-edr")
    public String getKnightEdron() {
        return knightEdron;
    }

    @JsonGetter("k-kaz")
    public String getKnightKazordoon() {
        return knightKazordoon;
    }

    @JsonGetter("k-lib")
    public String getKnightLibertyBay() {
        return knightLibertyBay;
    }

    @JsonGetter("k-por")
    public String getKnightPortHope() {
        return knightPortHope;
    }

    @JsonGetter("k-rat")
    public String getKnightRathleton() {
        return knightRathleton;
    }

    @JsonGetter("k-sva")
    public String getKnightSvargrond() {
        return knightSvargrond;
    }

    @JsonGetter("k-tha")
    public String getKnightThais() {
        return knightThais;
    }

    @JsonGetter("k-ven")
    public String getKnightVenore() {
        return knightVenore;
    }

    @JsonGetter("k-yal")
    public String getKnightYalahar() {
        return knightYalahar;
    }

    @JsonGetter("p-abd")
    public String getPaladinAbDendriel() {
        return paladinAbDendriel;
    }

    @JsonGetter("p-ank")
    public String getPaladinAnkrahmun() {
        return paladinAnkrahmun;
    }

    @JsonGetter("p-car")
    public String getPaladinCarlin() {
        return paladinCarlin;
    }

    @JsonGetter("p-dar")
    public String getPaladinDarashia() {
        return paladinDarashia;
    }

    @JsonGetter("p-edr")
    public String getPaladinEdron() {
        return paladinEdron;
    }

    @JsonGetter("p-kaz")
    public String getPaladinKazordoon() {
        return paladinKazordoon;
    }

    @JsonGetter("p-lib")
    public String getPaladinLibertyBay() {
        return paladinLibertyBay;
    }

    @JsonGetter("p-por")
    public String getPaladinPortHope() {
        return paladinPortHope;
    }

    @JsonGetter("p-rat")
    public String getPaladinRathleton() {
        return paladinRathleton;
    }

    @JsonGetter("p-sva")
    public String getPaladinSvargrond() {
        return paladinSvargrond;
    }

    @JsonGetter("p-tha")
    public String getPaladinThais() {
        return paladinThais;
    }

    @JsonGetter("p-ven")
    public String getPaladinVenore() {
        return paladinVenore;
    }

    @JsonGetter("p-yal")
    public String getPaladinYalahar() {
        return paladinYalahar;
    }

    @JsonGetter("s-abd")
    public String getSorcererAbDendriel() {
        return sorcererAbDendriel;
    }

    @JsonGetter("s-ank")
    public String getSorcererAnkrahmun() {
        return sorcererAnkrahmun;
    }

    @JsonGetter("s-car")
    public String getSorcererCarlin() {
        return sorcererCarlin;
    }

    @JsonGetter("s-dar")
    public String getSorcererDarashia() {
        return sorcererDarashia;
    }

    @JsonGetter("s-edr")
    public String getSorcererEdron() {
        return sorcererEdron;
    }

    @JsonGetter("s-kaz")
    public String getSorcererKazordoon() {
        return sorcererKazordoon;
    }

    @JsonGetter("s-lib")
    public String getSorcererLibertyBay() {
        return sorcererLibertyBay;
    }

    @JsonGetter("s-por")
    public String getSorcererPortHope() {
        return sorcererPortHope;
    }

    @JsonGetter("s-rat")
    public String getSorcererRathleton() {
        return sorcererRathleton;
    }

    @JsonGetter("s-sva")
    public String getSorcererSvargrond() {
        return sorcererSvargrond;
    }

    @JsonGetter("s-tha")
    public String getSorcererThais() {
        return sorcererThais;
    }

    @JsonGetter("s-ven")
    public String getSorcererVenore() {
        return sorcererVenore;
    }

    @JsonGetter("s-yal")
    public String getSorcererYalahar() {
        return sorcererYalahar;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(type)) {
            LOG.warn("Spell '{}' has no type set", getName());
        }

        if (ObjectUtils.isEmpty(subclass)) {
            LOG.warn("Spell '{}' has no subclass set", getName());
        }

        if (ObjectUtils.isEmpty(words)) {
            words = "?";
        }

        if (ObjectUtils.isEmpty(premium)) {
            premium = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(voc)) {
            voc = "?";
        }
    }
}
package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.BestiaryClass;
import com.wikia.tibia.enums.BestiaryLevel;
import com.wikia.tibia.enums.BestiaryOccurrence;
import com.wikia.tibia.enums.Spawntype;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Creature extends WikiObject {

    private final String hitPoints;
    private final String experiencePoints;
    private final String armor;
    private final String summon;
    private final String convince;
    private final YesNo illusionable;
    private final String creatureclass;
    private final String primarytype;
    private final String secondarytype;
    private final BestiaryClass bestiaryclass;
    private final BestiaryLevel bestiarylevel;
    private final BestiaryOccurrence occurrence;
    private final List<Spawntype> spawntype;
    private final YesNo isboss;
    private final YesNo isarenaboss;
    private final YesNo isevent;
    private final String abilities;
    private final String usedelements;
    private final String maxdmg;
    private final YesNo pushable;
    private final YesNo pushobjects;
    private final String walksaround;
    private final String walksthrough;
    private final YesNo paraimmune;
    private final YesNo senseinvis;
    private final Percentage physicalDmgMod;
    private final Percentage holyDmgMod;
    private final Percentage deathDmgMod;
    private final Percentage fireDmgMod;
    private final Percentage energyDmgMod;
    private final Percentage iceDmgMod;
    private final Percentage earthDmgMod;
    private final Percentage drownDmgMod;
    private final Percentage hpDrainDmgMod;
    private final Percentage healMod;
    private final String bestiaryname;
    private final String bestiarytext;
    private final List<String> sounds;
    private final String behaviour;
    private final String runsat;
    private final String speed;
    private final String strategy;
    private final String location;
    private final List<LootItem> loot;

    private Creature() {
        this.hitPoints = null;
        this.experiencePoints = null;
        this.armor = null;
        this.summon = null;
        this.convince = null;
        this.illusionable = null;
        this.creatureclass = null;
        this.primarytype = null;
        this.secondarytype = null;
        this.bestiaryclass = null;
        this.bestiarylevel = null;
        this.occurrence = null;
        this.spawntype = null;
        this.isboss = null;
        this.isarenaboss = null;
        this.isevent = null;
        this.abilities = null;
        this.usedelements = null;
        this.maxdmg = null;
        this.pushable = null;
        this.pushobjects = null;
        this.walksaround = null;
        this.walksthrough = null;
        this.paraimmune = null;
        this.senseinvis = null;
        this.physicalDmgMod = null;
        this.holyDmgMod = null;
        this.deathDmgMod = null;
        this.fireDmgMod = null;
        this.energyDmgMod = null;
        this.iceDmgMod = null;
        this.earthDmgMod = null;
        this.drownDmgMod = null;
        this.hpDrainDmgMod = null;
        this.healMod = null;
        this.bestiaryname = null;
        this.bestiarytext = null;
        this.sounds = null;
        this.behaviour = null;
        this.runsat = null;
        this.speed = null;
        this.strategy = null;
        this.location = null;
        this.loot = null;
    }

    @Builder
    public Creature(String name, Article article, String actualname, String plural, String implemented, String notes,
                    String history, Status status, String hitPoints, String experiencePoints, String armor,
                    String summon, String convince, YesNo illusionable, String creatureclass, String primarytype,
                    String secondarytype, BestiaryClass bestiaryclass, BestiaryLevel bestiarylevel,
                    BestiaryOccurrence occurrence, List<Spawntype> spawntype, YesNo isboss, YesNo isarenaboss,
                    YesNo isevent, String abilities, String usedelements, String maxdmg, YesNo pushable,
                    YesNo pushobjects, String walksaround, String walksthrough, YesNo paraimmune, YesNo senseinvis,
                    Percentage physicalDmgMod, Percentage holyDmgMod, Percentage deathDmgMod, Percentage fireDmgMod,
                    Percentage energyDmgMod, Percentage iceDmgMod, Percentage earthDmgMod, Percentage drownDmgMod,
                    Percentage hpDrainDmgMod, Percentage healMod, String bestiaryname, String bestiarytext,
                    List<String> sounds, String behaviour, String runsat, String speed, String strategy, String location,
                    List<LootItem> loot) {
        super(name, article, actualname, plural, implemented, notes, history, status);
        this.hitPoints = hitPoints;
        this.experiencePoints = experiencePoints;
        this.armor = armor;
        this.summon = summon;
        this.convince = convince;
        this.illusionable = illusionable;
        this.creatureclass = creatureclass;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.bestiaryclass = bestiaryclass;
        this.bestiarylevel = bestiarylevel;
        this.occurrence = occurrence;
        this.spawntype = spawntype;
        this.isboss = isboss;
        this.isarenaboss = isarenaboss;
        this.isevent = isevent;
        this.abilities = abilities;
        this.usedelements = usedelements;
        this.maxdmg = maxdmg;
        this.pushable = pushable;
        this.pushobjects = pushobjects;
        this.walksaround = walksaround;
        this.walksthrough = walksthrough;
        this.paraimmune = paraimmune;
        this.senseinvis = senseinvis;
        this.physicalDmgMod = physicalDmgMod;
        this.holyDmgMod = holyDmgMod;
        this.deathDmgMod = deathDmgMod;
        this.fireDmgMod = fireDmgMod;
        this.energyDmgMod = energyDmgMod;
        this.iceDmgMod = iceDmgMod;
        this.earthDmgMod = earthDmgMod;
        this.drownDmgMod = drownDmgMod;
        this.hpDrainDmgMod = hpDrainDmgMod;
        this.healMod = healMod;
        this.bestiaryname = bestiaryname;
        this.bestiarytext = bestiarytext;
        this.sounds = sounds;
        this.behaviour = behaviour;
        this.runsat = runsat;
        this.speed = speed;
        this.strategy = strategy;
        this.location = location;
        this.loot = loot;
    }

    @JsonGetter("hp")
    public String getHitPoints() {
        return hitPoints;
    }

    @JsonGetter("exp")
    public String getExperiencePoints() {
        return experiencePoints;
    }
}
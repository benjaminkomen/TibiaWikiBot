package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.BestiaryClass;
import com.wikia.tibia.enums.BestiaryLevel;
import com.wikia.tibia.enums.BestiaryOccurrence;
import com.wikia.tibia.enums.Spawntype;
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

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Creature extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Creature.class);

    private String hitPoints;
    private String experiencePoints;
    private String armor;
    private String summon;
    private String convince;
    private YesNo illusionable;
    private String creatureclass;
    private String primarytype;
    private String secondarytype;
    private BestiaryClass bestiaryclass;
    private BestiaryLevel bestiarylevel;
    private BestiaryOccurrence occurrence;
    private List<Spawntype> spawntype;
    private YesNo isboss;
    private YesNo isarenaboss;
    private YesNo isevent;
    private String abilities;
    private String usedelements;
    private String maxdmg;
    private YesNo pushable;
    private YesNo pushobjects;
    private String walksaround;
    private String walksthrough;
    private YesNo paraimmune;
    private YesNo senseinvis;
    private Percentage physicalDmgMod;
    private Percentage earthDmgMod;
    private Percentage fireDmgMod;
    private Percentage deathDmgMod;
    private Percentage energyDmgMod;
    private Percentage holyDmgMod;
    private Percentage iceDmgMod;
    private Percentage healMod;
    private Percentage hpDrainDmgMod;
    private Percentage drownDmgMod;
    private String bestiaryname;
    private String bestiarytext;
    private List<String> sounds;
    private String behaviour;
    private String runsat;
    private String speed;
    private String strategy;
    private String location;
    private List<LootItem> loot;

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
        this.earthDmgMod = earthDmgMod;
        this.fireDmgMod = fireDmgMod;
        this.deathDmgMod = deathDmgMod;
        this.energyDmgMod = energyDmgMod;
        this.holyDmgMod = holyDmgMod;
        this.iceDmgMod = iceDmgMod;
        this.healMod = healMod;
        this.hpDrainDmgMod = hpDrainDmgMod;
        this.drownDmgMod = drownDmgMod;
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

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(hitPoints)) {
            hitPoints = "?";
        }

        if (ObjectUtils.isEmpty(experiencePoints)) {
            experiencePoints = "?";
        }

        if (ObjectUtils.isEmpty(armor)) {
            armor = "?";
        }

        if (ObjectUtils.isEmpty(summon)) {
            summon = "?";
        }

        if (ObjectUtils.isEmpty(convince)) {
            convince = "?";
        }

        if (ObjectUtils.isEmpty(illusionable)) {
            illusionable = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(creatureclass)) {
            creatureclass = "";
            LOG.warn("Creature '{}' has no creatureclass set", getName());
        }

        if (ObjectUtils.isEmpty(primarytype)) {
            primarytype = "";
            LOG.warn("Creature '{}' has no primarytype set", getName());
        }

        if (ObjectUtils.isEmpty(isboss)) {
            isboss = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(abilities)) {
            abilities = "Unknown";
        }

        if (ObjectUtils.isEmpty(maxdmg)) {
            maxdmg = "?";
        }

        if (ObjectUtils.isEmpty(pushable)) {
            pushable = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(pushobjects)) {
            pushobjects = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(walksaround)) {
            walksaround = "?";
        }

        if (ObjectUtils.isEmpty(walksthrough)) {
            walksthrough = "?";
        }

        if (ObjectUtils.isEmpty(paraimmune)) {
            paraimmune = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(senseinvis)) {
            senseinvis = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(physicalDmgMod)) {
            physicalDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(earthDmgMod)) {
            earthDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(fireDmgMod)) {
            fireDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(deathDmgMod)) {
            deathDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(energyDmgMod)) {
            energyDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(holyDmgMod)) {
            holyDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(iceDmgMod)) {
            iceDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(healMod)) {
            healMod = Percentage.UNKNOWN;
        }

        // Special case, all creatures in the Bestiary have a healMod of 100%
        if (!ObjectUtils.isEmpty(healMod) && healMod.equals(Percentage.UNKNOWN) && (!ObjectUtils.isEmpty(bestiaryclass) || !ObjectUtils.isEmpty(bestiarylevel))) {
            healMod = Percentage.of(100);
        }

        if (ObjectUtils.isEmpty(hpDrainDmgMod)) {
            hpDrainDmgMod = Percentage.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(drownDmgMod)) {
            drownDmgMod = Percentage.UNKNOWN;
        }

        if (sounds == null) {
            sounds = Collections.emptyList();
        }

        if (ObjectUtils.isEmpty(behaviour)) {
            behaviour = "Unknown";
        }

        if (ObjectUtils.isEmpty(speed)) {
            speed = "?";
        }

        if (ObjectUtils.isEmpty(strategy)) {
            strategy = "Unknown";
        }

        if (ObjectUtils.isEmpty(location)) {
            location = "Unknown";
        }

        if (loot == null) {
            loot = Collections.emptyList();
        }
    }
}
package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.wikia.tibia.enums.BestiaryClass;
import com.wikia.tibia.enums.BestiaryLevel;
import com.wikia.tibia.enums.BestiaryOccurrence;
import com.wikia.tibia.enums.Spawntype;
import com.wikia.tibia.enums.YesNo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"templateType"})
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) // TODO make this private and add builder
public class Creature extends WikiObject {

    private String hitPoints; // FIXME should be Integer
    private String experiencePoints; // FIXME should be Integer
    private String armor; // FIXME should be Integer
    private String summon; // FIXME should be Integer
    private String convince; // FIXME should be Integer
    private YesNo illusionable;
    private String creatureclass;
    private String primarytype;
    private String secondarytype;
    private BestiaryClass bestiaryclass;
    private BestiaryLevel bestiarylevel;
    private BestiaryOccurrence occurrence;
    private List<Spawntype> spawntype = new ArrayList<>();
    private YesNo isboss;
    private YesNo isarenaboss;
    private YesNo isevent;
    private String abilities;
    private String usedelements; // FIXME should be List<DamageElement>
    private String maxdmg; // FIXME should be Integer
    private YesNo pushable;
    private YesNo pushobjects;
    private String walksaround; // FIXME should be List<Field>
    private String walksthrough; // FIXME should be List<Field>
    private YesNo paraimmune;
    private YesNo senseinvis;
    private Percentage physicalDmgMod;
    private Percentage holyDmgMod;
    private Percentage deathDmgMod;
    private Percentage fireDmgMod;
    private Percentage energyDmgMod;
    private Percentage iceDmgMod;
    private Percentage earthDmgMod;
    private Percentage drownDmgMod;
    private Percentage hpDrainDmgMod;
    private Percentage healMod;
    private String bestiaryname;
    private String bestiarytext;
    private List<String> sounds = new ArrayList<>();
    private String behaviour;
    private String runsat; // FIXME should be Integer
    private String speed; // FIXME should be Integer
    private String strategy;
    private String location;
    private List<LootItem> loot = new ArrayList<>();

    @JsonGetter("hp")
    public String getHitPoints() {
        return hitPoints;
    }

    @JsonSetter("hp")
    public void setHitPoints(String hitPoints) {
        this.hitPoints = hitPoints;
    }

    @JsonGetter("exp")
    public String getExperiencePoints() {
        return experiencePoints;
    }

    @JsonSetter("exp")
    public void setExperiencePoints(String experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @JsonGetter("healMod")
    public Percentage gethealMod() {
        return healMod;
    }

    @JsonSetter("healMod")
    public void sethealMod(Percentage healMod) {
        this.healMod = healMod;
    }

    @Override
    public List<String> fieldOrder() {
        return Arrays.asList("name", "article", "actualname", "plural", "hp", "exp", "armor", "summon", "convince",
                "illusionable", "creatureclass", "primarytype", "secondarytype", "bestiaryclass", "bestiarylevel",
                "occurrence", "spawntype", "isboss", "isarenaboss", "isevent", "abilities", "usedelements", "maxdmg",
                "pushable", "pushobjects", "walksaround", "walksthrough", "paraimmune", "senseinvis", "physicalDmgMod",
                "holyDmgMod", "deathDmgMod", "fireDmgMod", "energyDmgMod", "iceDmgMod", "earthDmgMod", "drownDmgMod",
                "hpDrainDmgMod", "healMod", "bestiaryname", "bestiarytext", "sounds", "implemented", "notes", "behaviour",
                "runsat", "speed", "strategy", "location", "loot", "history", "status");
    }
}
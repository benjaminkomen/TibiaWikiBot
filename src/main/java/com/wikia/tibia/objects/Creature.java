package com.wikia.tibia.objects;

import com.wikia.tibia.enums.*;

import java.util.List;

public class Creature {

    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private int hitPoints;
    private int experiencePoints;
    private int armor;
    private Integer summon;
    private Integer convince;
    private YesNo illusionable;
    private String creatureclass;
    private String primarytype;
    private String secondarytype;
    private List<Spawntype> spawntype;
    private YesNo isboss;
    private YesNo isarenaboss;
    private YesNo isevent;
    private String abilities;
    private List<DamageElement> usedelements;
    private int maxdmg;
    private YesNo pushable;
    private YesNo pushobjects;
    private List<DamageElement> walksaround;
    private List<DamageElement> walksthrough;
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
    private String bestiaryname;
    private String bestiarytext;
    private List<Sound> sounds;
    private double implemented;
    private String notes;
    private String behaviour;
    private int runsat;
    private int speed;
    private String strategy;
    private String location;
    private List<LootItem> loot;
    private String history;
    private Status status;


}

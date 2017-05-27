package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.wikia.tibia.enums.*;

import java.util.List;

@JsonIgnoreProperties({ "objectType", "type", "text" })
public class Creature extends WikiObject {

    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private String hitPoints;
    private String experiencePoints;
    private String armor;
    private String summon;
    private String convince;
    private YesNo illusionable;
    private String creatureclass;
    private String primarytype;
    private String secondarytype;
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
    private String implemented;
    private String notes;
    private String behaviour;
    private String runsat;
    private String speed;
    private String strategy;
    private String location;
    private List<LootItem> loot;
    private String history;
    private Status status;

    public Creature() {
        // constructor for Jackson
    }

    public Creature(Boolean illusionable) {
        this();
//        this.illusionable = illusionable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getActualname() {
        return actualname;
    }

    public void setActualname(String actualname) {
        this.actualname = actualname;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

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

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getSummon() {
        return summon;
    }

    public void setSummon(String summon) {
        this.summon = summon;
    }

    public String getConvince() {
        return convince;
    }

    public void setConvince(String convince) {
        this.convince = convince;
    }

    public YesNo getIllusionable() {
        return illusionable;
    }

    public void setIllusionable(YesNo illusionable) {
        this.illusionable = illusionable;
    }

    public String getCreatureclass() {
        return creatureclass;
    }

    public void setCreatureclass(String creatureclass) {
        this.creatureclass = creatureclass;
    }

    public String getPrimarytype() {
        return primarytype;
    }

    public void setPrimarytype(String primarytype) {
        this.primarytype = primarytype;
    }

    public String getSecondarytype() {
        return secondarytype;
    }

    public void setSecondarytype(String secondarytype) {
        this.secondarytype = secondarytype;
    }

    public List<Spawntype> getSpawntype() {
        return spawntype;
    }

    public void setSpawntype(List<Spawntype> spawntype) {
        this.spawntype = spawntype;
    }

    public YesNo getIsboss() {
        return isboss;
    }

    public void setIsboss(YesNo isboss) {
        this.isboss = isboss;
    }

    public YesNo getIsarenaboss() {
        return isarenaboss;
    }

    public void setIsarenaboss(YesNo isarenaboss) {
        this.isarenaboss = isarenaboss;
    }

    public YesNo getIsevent() {
        return isevent;
    }

    public void setIsevent(YesNo isevent) {
        this.isevent = isevent;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getUsedelements() {
        return usedelements;
    }

    public void setUsedelements(String usedelements) {
        this.usedelements = usedelements;
    }

    public String getMaxdmg() {
        return maxdmg;
    }

    public void setMaxdmg(String maxdmg) {
        this.maxdmg = maxdmg;
    }

    public YesNo getPushable() {
        return pushable;
    }

    public void setPushable(YesNo pushable) {
        this.pushable = pushable;
    }

    public YesNo getPushobjects() {
        return pushobjects;
    }

    public void setPushobjects(YesNo pushobjects) {
        this.pushobjects = pushobjects;
    }

    public String getWalksaround() {
        return walksaround;
    }

    public void setWalksaround(String walksaround) {
        this.walksaround = walksaround;
    }

    public String getWalksthrough() {
        return walksthrough;
    }

    public void setWalksthrough(String walksthrough) {
        this.walksthrough = walksthrough;
    }

    public YesNo getParaimmune() {
        return paraimmune;
    }

    public void setParaimmune(YesNo paraimmune) {
        this.paraimmune = paraimmune;
    }

    public YesNo getSenseinvis() {
        return senseinvis;
    }

    public void setSenseinvis(YesNo senseinvis) {
        this.senseinvis = senseinvis;
    }

    public Percentage getPhysicalDmgMod() {
        return physicalDmgMod;
    }

    public void setPhysicalDmgMod(Percentage physicalDmgMod) {
        this.physicalDmgMod = physicalDmgMod;
    }

    public Percentage getHolyDmgMod() {
        return holyDmgMod;
    }

    public void setHolyDmgMod(Percentage holyDmgMod) {
        this.holyDmgMod = holyDmgMod;
    }

    public Percentage getDeathDmgMod() {
        return deathDmgMod;
    }

    public void setDeathDmgMod(Percentage deathDmgMod) {
        this.deathDmgMod = deathDmgMod;
    }

    public Percentage getFireDmgMod() {
        return fireDmgMod;
    }

    public void setFireDmgMod(Percentage fireDmgMod) {
        this.fireDmgMod = fireDmgMod;
    }

    public Percentage getEnergyDmgMod() {
        return energyDmgMod;
    }

    public void setEnergyDmgMod(Percentage energyDmgMod) {
        this.energyDmgMod = energyDmgMod;
    }

    public Percentage getIceDmgMod() {
        return iceDmgMod;
    }

    public void setIceDmgMod(Percentage iceDmgMod) {
        this.iceDmgMod = iceDmgMod;
    }

    public Percentage getEarthDmgMod() {
        return earthDmgMod;
    }

    public void setEarthDmgMod(Percentage earthDmgMod) {
        this.earthDmgMod = earthDmgMod;
    }

    public Percentage getDrownDmgMod() {
        return drownDmgMod;
    }

    public void setDrownDmgMod(Percentage drownDmgMod) {
        this.drownDmgMod = drownDmgMod;
    }

    public Percentage getHpDrainDmgMod() {
        return hpDrainDmgMod;
    }

    public void setHpDrainDmgMod(Percentage hpDrainDmgMod) {
        this.hpDrainDmgMod = hpDrainDmgMod;
    }

    public String getBestiaryname() {
        return bestiaryname;
    }

    public void setBestiaryname(String bestiaryname) {
        this.bestiaryname = bestiaryname;
    }

    public String getBestiarytext() {
        return bestiarytext;
    }

    public void setBestiarytext(String bestiarytext) {
        this.bestiarytext = bestiarytext;
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }

    public String getImplemented() {
        return implemented;
    }

    public void setImplemented(String implemented) {
        this.implemented = implemented;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getRunsat() {
        return runsat;
    }

    public void setRunsat(String runsat) {
        this.runsat = runsat;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<LootItem> getLoot() {
        return loot;
    }

    public void setLoot(List<LootItem> loot) {
        this.loot = loot;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
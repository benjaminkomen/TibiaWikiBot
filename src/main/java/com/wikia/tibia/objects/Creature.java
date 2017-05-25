package com.wikia.tibia.objects;

import com.wikia.tibia.enums.*;

import java.util.List;

public class Creature {

    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private Integer hitPoints;
    private Integer experiencePoints;
    private Integer armor;
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
    private Integer maxdmg;
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
    private Double implemented;
    private String notes;
    private String behaviour;
    private Integer runsat;
    private Integer speed;
    private String strategy;
    private String location;
    private List<LootItem> loot;
    private String history;
    private Status status;

    public Creature() {
        // constructor for Jackson
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

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getSummon() {
        return summon;
    }

    public void setSummon(Integer summon) {
        this.summon = summon;
    }

    public Integer getConvince() {
        return convince;
    }

    public void setConvince(Integer convince) {
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

    public List<DamageElement> getUsedelements() {
        return usedelements;
    }

    public void setUsedelements(List<DamageElement> usedelements) {
        this.usedelements = usedelements;
    }

    public Integer getMaxdmg() {
        return maxdmg;
    }

    public void setMaxdmg(Integer maxdmg) {
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

    public List<DamageElement> getWalksaround() {
        return walksaround;
    }

    public void setWalksaround(List<DamageElement> walksaround) {
        this.walksaround = walksaround;
    }

    public List<DamageElement> getWalksthrough() {
        return walksthrough;
    }

    public void setWalksthrough(List<DamageElement> walksthrough) {
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

    public Double getImplemented() {
        return implemented;
    }

    public void setImplemented(Double implemented) {
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

    public Integer getRunsat() {
        return runsat;
    }

    public void setRunsat(Integer runsat) {
        this.runsat = runsat;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
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

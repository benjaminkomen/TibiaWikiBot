package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.*;

import java.util.List;

@JsonIgnoreProperties({ "objectType", "type" })
public class Item extends WikiObject {

    private List<Integer> itemid;
    private YesNo marketable;
    private YesNo usable;
    private String sprites;
    private String flavortext;
    private Status ingamestatus;
    private String words;
    private ItemClass itemclass;
    private String primarytype;
    private String secondarytype;
    private Integer lightcolor;
    private Integer lightradius;
    private Integer levelrequired;
    private String vocrequired;
    private Integer mlrequired;
    private Hands hands;
    private WeaponType type;
    private Integer attack;
    private String elementattack;
    private Integer defense;
    private String defensemod;
    private Integer imbueslots;
    private String imbuements;
    private YesNo enchantable;
    private YesNo enchanted;
    private String range;
    private String atk_mod;
    private String hit_mod;
    private Integer armor;
    private String resist;
    private Percentage crithit_ch;
    private Percentage critextra_dmg;
    private Percentage manaleech_ch;
    private Percentage manaleech_am;
    private Percentage hpleech_ch;
    private Percentage hpleech_am;
    private String attrib;
    private Double weight;
    private YesNo stackable;
    private YesNo pickupable;
    private YesNo immobile;
    private YesNo walkable;
    private YesNo unshootable;
    private YesNo blockspath;
    private YesNo rotatable;
    private Integer mapcolor;
    private YesNo consumable;
    private Integer regenseconds;
    private List<String> sounds;
    private YesNo writable;
    private YesNo rewritable;
    private Integer writechars;
    private YesNo hangable;
    private YesNo holdsliquid;
    private Integer mana;
    private DamageElement damagetype;
    private String damage;
    private Integer volume;
    private String duration;
    private YesNo destructable;
    private List<String> droppedby;
    private String value;
    private String npcvalue;
    private String npcprice;
    private Integer npcvaluerook;
    private Integer npcpricerook;
    private String buyfrom;
    private String sellto;

    private Item() {
        // constructor for Jackson
    }

    public List<Integer> getItemid() {
        return itemid;
    }

    public void setItemid(List<Integer> itemid) {
        this.itemid = itemid;
    }

    public YesNo getMarketable() {
        return marketable;
    }

    public void setMarketable(YesNo marketable) {
        this.marketable = marketable;
    }

    public YesNo getUsable() {
        return usable;
    }

    public void setUsable(YesNo usable) {
        this.usable = usable;
    }

    public String getSprites() {
        return sprites;
    }

    public void setSprites(String sprites) {
        this.sprites = sprites;
    }

    public String getFlavortext() {
        return flavortext;
    }

    public void setFlavortext(String flavortext) {
        this.flavortext = flavortext;
    }

    public Status getIngamestatus() {
        return ingamestatus;
    }

    public void setIngamestatus(Status ingamestatus) {
        this.ingamestatus = ingamestatus;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public ItemClass getItemclass() {
        return itemclass;
    }

    public void setItemclass(ItemClass itemclass) {
        this.itemclass = itemclass;
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

    public Integer getLightcolor() {
        return lightcolor;
    }

    public void setLightcolor(Integer lightcolor) {
        this.lightcolor = lightcolor;
    }

    public Integer getLightradius() {
        return lightradius;
    }

    public void setLightradius(Integer lightradius) {
        this.lightradius = lightradius;
    }

    public Integer getLevelrequired() {
        return levelrequired;
    }

    public void setLevelrequired(Integer levelrequired) {
        this.levelrequired = levelrequired;
    }

    public String getVocrequired() {
        return vocrequired;
    }

    public void setVocrequired(String vocrequired) {
        this.vocrequired = vocrequired;
    }

    public Integer getMlrequired() {
        return mlrequired;
    }

    public void setMlrequired(Integer mlrequired) {
        this.mlrequired = mlrequired;
    }

    public Hands getHands() {
        return hands;
    }

    public void setHands(Hands hands) {
        this.hands = hands;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public String getElementattack() {
        return elementattack;
    }

    public void setElementattack(String elementattack) {
        this.elementattack = elementattack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public String getDefensemod() {
        return defensemod;
    }

    public void setDefensemod(String defensemod) {
        this.defensemod = defensemod;
    }

    public Integer getImbueslots() {
        return imbueslots;
    }

    public void setImbueslots(Integer imbueslots) {
        this.imbueslots = imbueslots;
    }

    public String getImbuements() {
        return imbuements;
    }

    public void setImbuements(String imbuements) {
        this.imbuements = imbuements;
    }

    public YesNo getEnchantable() {
        return enchantable;
    }

    public void setEnchantable(YesNo enchantable) {
        this.enchantable = enchantable;
    }

    public YesNo getEnchanted() {
        return enchanted;
    }

    public void setEnchanted(YesNo enchanted) {
        this.enchanted = enchanted;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAtk_mod() {
        return atk_mod;
    }

    public void setAtk_mod(String atk_mod) {
        this.atk_mod = atk_mod;
    }

    public String getHit_mod() {
        return hit_mod;
    }

    public void setHit_mod(String hit_mod) {
        this.hit_mod = hit_mod;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public String getResist() {
        return resist;
    }

    public void setResist(String resist) {
        this.resist = resist;
    }

    public Percentage getCrithit_ch() {
        return crithit_ch;
    }

    public void setCrithit_ch(Percentage crithit_ch) {
        this.crithit_ch = crithit_ch;
    }

    public Percentage getCritextra_dmg() {
        return critextra_dmg;
    }

    public void setCritextra_dmg(Percentage critextra_dmg) {
        this.critextra_dmg = critextra_dmg;
    }

    public Percentage getManaleech_ch() {
        return manaleech_ch;
    }

    public void setManaleech_ch(Percentage manaleech_ch) {
        this.manaleech_ch = manaleech_ch;
    }

    public Percentage getManaleech_am() {
        return manaleech_am;
    }

    public void setManaleech_am(Percentage manaleech_am) {
        this.manaleech_am = manaleech_am;
    }

    public Percentage getHpleech_ch() {
        return hpleech_ch;
    }

    public void setHpleech_ch(Percentage hpleech_ch) {
        this.hpleech_ch = hpleech_ch;
    }

    public Percentage getHpleech_am() {
        return hpleech_am;
    }

    public void setHpleech_am(Percentage hpleech_am) {
        this.hpleech_am = hpleech_am;
    }

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public YesNo getStackable() {
        return stackable;
    }

    public void setStackable(YesNo stackable) {
        this.stackable = stackable;
    }

    public YesNo getPickupable() {
        return pickupable;
    }

    public void setPickupable(YesNo pickupable) {
        this.pickupable = pickupable;
    }

    public YesNo getImmobile() {
        return immobile;
    }

    public void setImmobile(YesNo immobile) {
        this.immobile = immobile;
    }

    public YesNo getWalkable() {
        return walkable;
    }

    public void setWalkable(YesNo walkable) {
        this.walkable = walkable;
    }

    public YesNo getUnshootable() {
        return unshootable;
    }

    public void setUnshootable(YesNo unshootable) {
        this.unshootable = unshootable;
    }

    public YesNo getBlockspath() {
        return blockspath;
    }

    public void setBlockspath(YesNo blockspath) {
        this.blockspath = blockspath;
    }

    public YesNo getRotatable() {
        return rotatable;
    }

    public void setRotatable(YesNo rotatable) {
        this.rotatable = rotatable;
    }

    public Integer getMapcolor() {
        return mapcolor;
    }

    public void setMapcolor(Integer mapcolor) {
        this.mapcolor = mapcolor;
    }

    public YesNo getConsumable() {
        return consumable;
    }

    public void setConsumable(YesNo consumable) {
        this.consumable = consumable;
    }

    public Integer getRegenseconds() {
        return regenseconds;
    }

    public void setRegenseconds(Integer regenseconds) {
        this.regenseconds = regenseconds;
    }

    public List<String> getSounds() {
        return sounds;
    }

    public void setSounds(List<String> sounds) {
        this.sounds = sounds;
    }

    public YesNo getWritable() {
        return writable;
    }

    public void setWritable(YesNo writable) {
        this.writable = writable;
    }

    public YesNo getRewritable() {
        return rewritable;
    }

    public void setRewritable(YesNo rewritable) {
        this.rewritable = rewritable;
    }

    public Integer getWritechars() {
        return writechars;
    }

    public void setWritechars(Integer writechars) {
        this.writechars = writechars;
    }

    public YesNo getHangable() {
        return hangable;
    }

    public void setHangable(YesNo hangable) {
        this.hangable = hangable;
    }

    public YesNo getHoldsliquid() {
        return holdsliquid;
    }

    public void setHoldsliquid(YesNo holdsliquid) {
        this.holdsliquid = holdsliquid;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public DamageElement getDamagetype() {
        return damagetype;
    }

    public void setDamagetype(DamageElement damagetype) {
        this.damagetype = damagetype;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public YesNo getDestructable() {
        return destructable;
    }

    public void setDestructable(YesNo destructable) {
        this.destructable = destructable;
    }

    public List<String> getDroppedby() {
        return droppedby;
    }

    public void setDroppedby(List<String> droppedby) {
        this.droppedby = droppedby;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNpcvalue() {
        return npcvalue;
    }

    public void setNpcvalue(String npcvalue) {
        this.npcvalue = npcvalue;
    }

    public String getNpcprice() {
        return npcprice;
    }

    public void setNpcprice(String npcprice) {
        this.npcprice = npcprice;
    }

    public Integer getNpcvaluerook() {
        return npcvaluerook;
    }

    public void setNpcvaluerook(Integer npcvaluerook) {
        this.npcvaluerook = npcvaluerook;
    }

    public Integer getNpcpricerook() {
        return npcpricerook;
    }

    public void setNpcpricerook(Integer npcpricerook) {
        this.npcpricerook = npcpricerook;
    }

    public String getBuyfrom() {
        return buyfrom;
    }

    public void setBuyfrom(String buyfrom) {
        this.buyfrom = buyfrom;
    }

    public String getSellto() {
        return sellto;
    }

    public void setSellto(String sellto) {
        this.sellto = sellto;
    }
}
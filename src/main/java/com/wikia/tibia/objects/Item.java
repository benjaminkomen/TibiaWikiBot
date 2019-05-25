package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.DamageElement;
import com.wikia.tibia.enums.Hands;
import com.wikia.tibia.enums.ItemClass;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.WeaponType;
import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Item.class);

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
    private String attack;
    private Integer fireAttack;
    private Integer earthAttack;
    private Integer iceAttack;
    private Integer energyAttack;
    private Integer defense;
    private String defensemod;
    private Integer imbueslots;
    private String imbuements;
    private YesNo enchantable;
    private YesNo enchanted;
    private String range;
    private String attackModification;
    private String hitpointModification;
    private Integer armor;
    private String resist;
    private Integer charges;
    private Percentage criticalHitChance;
    private Percentage criticalHitExtraDamage;
    private Percentage manaleechChance;
    private Percentage manaleechAmount;
    private Percentage hitpointLeechChance;
    private Percentage hitpointLeechAmount;
    private String attrib;
    private BigDecimal weight;
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
    private YesNo destructible;
    private List<String> droppedby;
    private String value;
    private String npcvalue;
    private String npcprice;
    private String npcvaluerook;
    private String npcpricerook;
    private String buyfrom;
    private String sellto;

    @Builder
    private Item(String name, Article article, String actualname, String plural, String implemented, String notes,
                 String history, Status status, List<Integer> itemid, YesNo marketable, YesNo usable, String sprites,
                 String flavortext, Status ingamestatus, String words, ItemClass itemclass, String primarytype,
                 String secondarytype, Integer lightcolor, Integer lightradius, Integer levelrequired,
                 String vocrequired, Integer mlrequired, Hands hands, WeaponType type, String attack,
                 Integer fireAttack, Integer earthAttack, Integer iceAttack, Integer energyAttack, Integer defense,
                 String defensemod, Integer imbueslots, String imbuements,
                 YesNo enchantable, YesNo enchanted, String range, String attackModification, String hitpointModification,
                 Integer armor, String resist, Integer charges, Percentage criticalHitChance,
                 Percentage criticalHitExtraDamage, Percentage manaleechChance, Percentage manaleechAmount,
                 Percentage hitpointLeechChance, Percentage hitpointLeechAmount, String attrib, BigDecimal weight,
                 YesNo stackable, YesNo pickupable, YesNo immobile, YesNo walkable, YesNo unshootable, YesNo blockspath,
                 YesNo rotatable, Integer mapcolor, YesNo consumable, Integer regenseconds, List<String> sounds,
                 YesNo writable, YesNo rewritable, Integer writechars, YesNo hangable, YesNo holdsliquid, Integer mana,
                 DamageElement damagetype, String damage, Integer volume, String duration, YesNo destructible,
                 List<String> droppedby, String value, String npcvalue, String npcprice, String npcvaluerook,
                 String npcpricerook, String buyfrom, String sellto) {
        super(name, article, actualname, plural, implemented, notes, history, status);
        this.itemid = itemid;
        this.marketable = marketable;
        this.usable = usable;
        this.sprites = sprites;
        this.flavortext = flavortext;
        this.ingamestatus = ingamestatus;
        this.words = words;
        this.itemclass = itemclass;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.lightcolor = lightcolor;
        this.lightradius = lightradius;
        this.levelrequired = levelrequired;
        this.vocrequired = vocrequired;
        this.mlrequired = mlrequired;
        this.hands = hands;
        this.type = type;
        this.attack = attack;
        this.fireAttack = fireAttack;
        this.earthAttack = earthAttack;
        this.iceAttack = iceAttack;
        this.energyAttack = energyAttack;
        this.defense = defense;
        this.defensemod = defensemod;
        this.imbueslots = imbueslots;
        this.imbuements = imbuements;
        this.enchantable = enchantable;
        this.enchanted = enchanted;
        this.range = range;
        this.attackModification = attackModification;
        this.hitpointModification = hitpointModification;
        this.armor = armor;
        this.resist = resist;
        this.charges = charges;
        this.criticalHitChance = criticalHitChance;
        this.criticalHitExtraDamage = criticalHitExtraDamage;
        this.manaleechChance = manaleechChance;
        this.manaleechAmount = manaleechAmount;
        this.hitpointLeechChance = hitpointLeechChance;
        this.hitpointLeechAmount = hitpointLeechAmount;
        this.attrib = attrib;
        this.weight = weight;
        this.stackable = stackable;
        this.pickupable = pickupable;
        this.immobile = immobile;
        this.walkable = walkable;
        this.unshootable = unshootable;
        this.blockspath = blockspath;
        this.rotatable = rotatable;
        this.mapcolor = mapcolor;
        this.consumable = consumable;
        this.regenseconds = regenseconds;
        this.sounds = sounds;
        this.writable = writable;
        this.rewritable = rewritable;
        this.writechars = writechars;
        this.hangable = hangable;
        this.holdsliquid = holdsliquid;
        this.mana = mana;
        this.damagetype = damagetype;
        this.damage = damage;
        this.volume = volume;
        this.duration = duration;
        this.destructible = destructible;
        this.droppedby = droppedby;
        this.value = value;
        this.npcvalue = npcvalue;
        this.npcprice = npcprice;
        this.npcvaluerook = npcvaluerook;
        this.npcpricerook = npcpricerook;
        this.buyfrom = buyfrom;
        this.sellto = sellto;
    }

    @JsonGetter("atk_mod")
    public String getAttackModification() {
        return attackModification;
    }

    @JsonGetter("hit_mod")
    public String getHitpointModification() {
        return hitpointModification;
    }

    @JsonGetter("crithit_ch")
    public Percentage getCriticalHitChance() {
        return criticalHitChance;
    }

    @JsonGetter("critextra_dmg")
    public Percentage getCriticalHitExtraDamage() {
        return criticalHitExtraDamage;
    }

    @JsonGetter("manaleech_ch")
    public Percentage getManaleechChance() {
        return manaleechChance;
    }

    @JsonGetter("manaleech_am")
    public Percentage getManaleechAmount() {
        return manaleechAmount;
    }

    @JsonGetter("hpleech_ch")
    public Percentage getHitpointLeechChance() {
        return hitpointLeechChance;
    }

    @JsonGetter("hpleech_am")
    public Percentage getHitpointLeechAmount() {
        return hitpointLeechAmount;
    }

    @JsonGetter("fire_attack")
    public Integer getFireAttack() {
        return fireAttack;
    }

    @JsonGetter("earth_attack")
    public Integer getEarthAttack() {
        return earthAttack;
    }

    @JsonGetter("ice_attack")
    public Integer getIceAttack() {
        return iceAttack;
    }

    @JsonGetter("energy_attack")
    public Integer getEnergyAttack() {
        return energyAttack;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(itemclass)) {
            LOG.warn("Creature '{}' has no itemclass set", getName());
        }

        if (ObjectUtils.isEmpty(primarytype)) {
            primarytype = "?";
            LOG.warn("Creature '{}' has no primarytype set", getName());
        }

        if (ObjectUtils.isEmpty(value)) {
            value = "?";
        }

        if (ObjectUtils.isEmpty(npcvalue)) {
            npcvalue = "?";
        }

        if (ObjectUtils.isEmpty(npcprice)) {
            npcprice = "?";
        }

        if (ObjectUtils.isEmpty(buyfrom)) {
            buyfrom = "?";
        }

        if (ObjectUtils.isEmpty(sellto)) {
            sellto = "?";
        }
    }
}
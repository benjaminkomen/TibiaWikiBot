package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TibiaObject extends WikiObject {

    private List<Integer> itemid;
    private String objectclass;
    private String secondarytype;
    private String tertiarytype;
    private String flavortext;
    private Integer lightradius;
    private Integer lightcolor;
    private Integer volume;
    private YesNo destructable;
    private YesNo immobile;
    private String attrib;
    private YesNo walkable;
    private Integer walkingspeed;
    private YesNo unshootable;
    private YesNo blockspath;
    private YesNo pickupable;
    private YesNo holdsliquid;
    private YesNo usable;
    private YesNo writable;
    private YesNo rewritable;
    private Integer writechars;
    private YesNo rotatable;
    private Integer mapcolor;
    private String location;
    private String notes2;

    @Builder
    private TibiaObject(String name, Article article, String actualname, String implemented, String notes, String history,
                        Status status, List<Integer> itemid, String objectclass, String secondarytype, String tertiarytype,
                        String flavortext, Integer lightradius, Integer lightcolor, Integer volume, YesNo destructable,
                        YesNo immobile, String attrib, YesNo walkable, Integer walkingspeed, YesNo unshootable,
                        YesNo blockspath, YesNo pickupable, YesNo holdsliquid, YesNo usable, YesNo writable,
                        YesNo rewritable, Integer writechars, YesNo rotatable, Integer mapcolor, String location,
                        String notes2) {
        super(name, article, actualname, null, implemented, notes, history, status);
        this.itemid = itemid;
        this.objectclass = objectclass;
        this.secondarytype = secondarytype;
        this.tertiarytype = tertiarytype;
        this.flavortext = flavortext;
        this.lightradius = lightradius;
        this.lightcolor = lightcolor;
        this.volume = volume;
        this.destructable = destructable;
        this.immobile = immobile;
        this.attrib = attrib;
        this.walkable = walkable;
        this.walkingspeed = walkingspeed;
        this.unshootable = unshootable;
        this.blockspath = blockspath;
        this.pickupable = pickupable;
        this.holdsliquid = holdsliquid;
        this.usable = usable;
        this.writable = writable;
        this.rewritable = rewritable;
        this.writechars = writechars;
        this.rotatable = rotatable;
        this.mapcolor = mapcolor;
        this.location = location;
        this.notes2 = notes2;
    }

    @Override
    public void setDefaultValues() {
        // TODO implement this method
    }
}
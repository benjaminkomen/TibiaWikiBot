package com.wikia.tibia.objects;


import com.wikia.tibia.enums.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Missile extends WikiObject {

    private final Integer missileid;
    private final String primarytype;
    private final String secondarytype;
    private final Integer lightradius;
    private final Integer lightcolor;
    private final String shotby;

    private Missile() {
        this.missileid = null;
        this.primarytype = null;
        this.secondarytype = null;
        this.lightradius = null;
        this.lightcolor = null;
        this.shotby = null;
    }

    @Builder
    private Missile(String name, String implemented, String notes, String history, Status status, Integer missileid,
                    String primarytype, String secondarytype, Integer lightradius, Integer lightcolor, String shotby) {
        super(name, null, null, null, implemented, notes, history, status);
        this.missileid = missileid;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.lightradius = lightradius;
        this.lightcolor = lightcolor;
        this.shotby = shotby;
    }
}
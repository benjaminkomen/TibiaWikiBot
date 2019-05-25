package com.wikia.tibia.objects;


import com.wikia.tibia.enums.Status;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Missile extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Missile.class);

    private Integer missileid;
    private String primarytype;
    private String secondarytype;
    private Integer lightradius;
    private Integer lightcolor;
    private String shotby;

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

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(missileid)) {
            LOG.warn("Creature '{}' has no missileId set", getName());
        }

        if (ObjectUtils.isEmpty(primarytype)) {
            primarytype = "?";
        }

        if (ObjectUtils.isEmpty(shotby)) {
            shotby = "?";
        }
    }
}
package com.wikia.tibia.objects;


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

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Outfit extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Outfit.class);

    private String primarytype;
    private String secondarytype;
    private YesNo premium;
    @SuppressWarnings("squid:S1700") // class and field name are the same, but that's understandable
    private String outfit;
    private String addons;
    private YesNo bought;
    private Integer fulloutfitprice;
    private String achievement;
    private String artwork;

    @Builder
    private Outfit(String name, String implemented, String notes, String history, Status status, String primarytype,
                   String secondarytype, YesNo premium, String outfit, String addons, YesNo bought,
                   Integer fulloutfitprice, String achievement, String artwork) {
        super(name, null, null, null, implemented, notes, history, status);
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.premium = premium;
        this.outfit = outfit;
        this.addons = addons;
        this.bought = bought;
        this.fulloutfitprice = fulloutfitprice;
        this.achievement = achievement;
        this.artwork = artwork;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(primarytype)) {
            primarytype = "";
            LOG.warn("Outfit '{}' has no primarytype set", getName());
        }

        if (ObjectUtils.isEmpty(premium)) {
            premium = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(outfit)) {
            outfit = "?";
        }

        if (ObjectUtils.isEmpty(addons)) {
            addons = "?";
        }
    }
}
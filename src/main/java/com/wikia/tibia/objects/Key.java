package com.wikia.tibia.objects;

import com.wikia.tibia.enums.KeyType;
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
public class Key extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Key.class);

    private String number;
    private String aka;
    private KeyType primarytype;
    private KeyType secondarytype;
    private String location;
    private String value;
    private Integer npcvalue;
    private Integer npcprice;
    private String buyfrom;
    private String sellto;
    private String origin;
    private String shortnotes;
    private String longnotes;

    @Builder
    private Key(String implemented, String history, Status status, String number, String aka, KeyType primarytype,
                KeyType secondarytype, String location, String value, Integer npcvalue, Integer npcprice, String buyfrom,
                String sellto, String origin, String shortnotes, String longnotes) {
        super(null, null, null, null, implemented, null, history, status);
        this.number = number;
        this.aka = aka;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.location = location;
        this.value = value;
        this.npcvalue = npcvalue;
        this.npcprice = npcprice;
        this.buyfrom = buyfrom;
        this.sellto = sellto;
        this.origin = origin;
        this.shortnotes = shortnotes;
        this.longnotes = longnotes;
    }

    @Override
    public String getName() {
        return String.format("Key %s", number);
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(number)) {
            number = "?";
        }

        if (ObjectUtils.isEmpty(primarytype)) {
            LOG.warn("Creature '{}' has no primarytype set", getName());
        }

        if (ObjectUtils.isEmpty(shortnotes)) {
            shortnotes = "?";
        }
    }
}
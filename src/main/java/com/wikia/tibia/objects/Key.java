package com.wikia.tibia.objects;

import com.wikia.tibia.enums.KeyType;
import com.wikia.tibia.enums.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Key extends WikiObject {

    private final String number;
    private final String aka;
    private final KeyType primarytype;
    private final KeyType secondarytype;
    private final String location;
    private final String value;
    private final Integer npcvalue;
    private final Integer npcprice;
    private final String buyfrom;
    private final String sellto;
    private final String origin;
    private final String shortnotes;
    private final String longnotes;

    private Key() {
        this.number = null;
        this.aka = null;
        this.primarytype = null;
        this.secondarytype = null;
        this.location = null;
        this.value = null;
        this.npcvalue = null;
        this.npcprice = null;
        this.buyfrom = null;
        this.sellto = null;
        this.origin = null;
        this.shortnotes = null;
        this.longnotes = null;
    }

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
}
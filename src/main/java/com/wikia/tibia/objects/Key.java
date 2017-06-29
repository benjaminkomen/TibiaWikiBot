package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.KeyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({ "objectType" })
@Getter
@Setter
@NoArgsConstructor
public class Key extends WikiObject {

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
}
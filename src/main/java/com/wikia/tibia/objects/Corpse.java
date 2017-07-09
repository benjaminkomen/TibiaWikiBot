package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.YesNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({ "objectType" })
@Getter
@Setter
@NoArgsConstructor
public class Corpse extends WikiObject {

    private String flavortext;
    private YesNo skinable;
    private String product;
    private String liquid;
    private Integer stages;
    private String firstDecaytime; // FIXME should be Seconds
    private String secondDecaytime; // FIXME should be Seconds
    private String thirdDecaytime; // FIXME should be Seconds
    private Integer firstVolume;
    private Integer secondVolume;
    private Integer thirdVolume;
    private Double firstWeight;
    private Double secondWeight;
    private Double thirdWeight;
    private String corpseof;
    private String sellto;

    @JsonGetter("1decaytime")
    public String getFirstDecaytime() {
        return firstDecaytime;
    }

    @JsonGetter("2decaytime")
    public String getSecondDecaytime() {
        return secondDecaytime;
    }

    @JsonGetter("3decaytime")
    public String getThirdDecaytime() {
        return thirdDecaytime;
    }

    @JsonGetter("1volume")
    public Integer getFirstVolume() {
        return firstVolume;
    }

    @JsonGetter("2volume")
    public Integer getSecondVolume() {
        return secondVolume;
    }

    @JsonGetter("3volume")
    public Integer getThirdVolume() {
        return thirdVolume;
    }

    @JsonGetter("1weight")
    public Double getFirstWeight() {
        return firstWeight;
    }

    @JsonGetter("2weight")
    public Double getSecondWeight() {
        return secondWeight;
    }

    @JsonGetter("3weight")
    public Double getThirdWeight() {
        return thirdWeight;
    }
}
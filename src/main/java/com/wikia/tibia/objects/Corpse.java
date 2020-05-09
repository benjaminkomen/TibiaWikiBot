package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Corpse extends WikiObject {

    private String flavortext;
    private YesNo skinable;
    private String product;
    private String liquid;
    private Integer stages;
    private String firstDecaytime;
    private String secondDecaytime;
    private String thirdDecaytime;
    private Integer firstVolume;
    private Integer secondVolume;
    private Integer thirdVolume;
    private BigDecimal firstWeight;
    private BigDecimal secondWeight;
    private BigDecimal thirdWeight;
    private String corpseof;
    private String sellto;

    @Builder
    private Corpse(String name, Article article, String actualname, String implemented, String notes, String history,
                   Status status, String flavortext, YesNo skinable, String product, String liquid, Integer stages,
                   String firstDecaytime, String secondDecaytime, String thirdDecaytime, Integer firstVolume,
                   Integer secondVolume, Integer thirdVolume, BigDecimal firstWeight, BigDecimal secondWeight, BigDecimal thirdWeight,
                   String corpseof, String sellto) {
        super(name, article, actualname, null, implemented, notes, history, status);
        this.flavortext = flavortext;
        this.skinable = skinable;
        this.product = product;
        this.liquid = liquid;
        this.stages = stages;
        this.firstDecaytime = firstDecaytime;
        this.secondDecaytime = secondDecaytime;
        this.thirdDecaytime = thirdDecaytime;
        this.firstVolume = firstVolume;
        this.secondVolume = secondVolume;
        this.thirdVolume = thirdVolume;
        this.firstWeight = firstWeight;
        this.secondWeight = secondWeight;
        this.thirdWeight = thirdWeight;
        this.corpseof = corpseof;
        this.sellto = sellto;
    }

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
    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    @JsonGetter("2weight")
    public BigDecimal getSecondWeight() {
        return secondWeight;
    }

    @JsonGetter("3weight")
    public BigDecimal getThirdWeight() {
        return thirdWeight;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(skinable)) {
            skinable = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(liquid)) {
            liquid = "?";
        }

        if (ObjectUtils.isEmpty(corpseof)) {
            corpseof = "?";
        }
    }
}
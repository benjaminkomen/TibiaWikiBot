package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.Grade;
import com.wikia.tibia.enums.YesNo;

@JsonIgnoreProperties({ "objectType" })
public class Achievement extends WikiObject {

    private Grade grade;
    private String description;
    private String spoiler;
    private YesNo premium;
    private Integer points;
    private YesNo secret;
    private Integer coincideswith;
    private String relatedpages;

    public Achievement() {
        // constructor for Jackson
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(String spoiler) {
        this.spoiler = spoiler;
    }

    public YesNo getPremium() {
        return premium;
    }

    public void setPremium(YesNo premium) {
        this.premium = premium;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public YesNo getSecret() {
        return secret;
    }

    public void setSecret(YesNo secret) {
        this.secret = secret;
    }

    public Integer getCoincideswith() {
        return coincideswith;
    }

    public void setCoincideswith(Integer coincideswith) {
        this.coincideswith = coincideswith;
    }

    public String getRelatedpages() {
        return relatedpages;
    }

    public void setRelatedpages(String relatedpages) {
        this.relatedpages = relatedpages;
    }
}
package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Achievement extends WikiObject {

    private final Integer grade;
    private final String description;
    private final String spoiler;
    private final YesNo premium;
    private final Integer points;
    private final YesNo secret;
    private final Integer coincideswith;
    private final Integer achievementid;
    private final String relatedpages;

    private Achievement() {
        this.grade = null;
        this.description = null;
        this.spoiler = null;
        this.premium = null;
        this.points = null;
        this.secret = null;
        this.coincideswith = null;
        this.achievementid = null;
        this.relatedpages = null;
    }

    @Builder
    public Achievement(String name, String implemented, String history, Status status, Integer grade, String description,
                       String spoiler, YesNo premium, Integer points, YesNo secret, Integer coincideswith,
                       Integer achievementid, String relatedpages) {
        super(name, null, null, null, implemented, null, history, status);
        this.grade = grade;
        this.description = description;
        this.spoiler = spoiler;
        this.premium = premium;
        this.points = points;
        this.secret = secret;
        this.coincideswith = coincideswith;
        this.achievementid = achievementid;
        this.relatedpages = relatedpages;
    }
}
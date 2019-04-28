package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Achievement extends WikiObject {

    private Integer grade;
    private String description;
    private String spoiler;
    private YesNo premium;
    private Integer points;
    private YesNo secret;
    private Integer coincideswith;
    private Integer achievementid;
    private String relatedpages;

    @Builder
    public Achievement(String name, String actualname, String implemented, String history, Status status, Integer grade,
                       String description, String spoiler, YesNo premium, Integer points, YesNo secret, Integer coincideswith,
                       Integer achievementid, String relatedpages) {
        super(name, null, actualname, null, implemented, null, history, status);
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

    @Override
    public void setDefaultValues() {
        // TODO implement this method
    }
}
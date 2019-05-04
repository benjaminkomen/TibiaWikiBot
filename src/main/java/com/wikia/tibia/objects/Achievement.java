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
public class Achievement extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Achievement.class);

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

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(grade)) {
            LOG.warn("Achievement '{}' has no grade set", getName());
            grade = -1;
        }

        if (ObjectUtils.isEmpty(premium)) {
            premium = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(points)) {
            LOG.warn("Achievement '{}' has no points set", getName());
            points = -1;
        }

        if (ObjectUtils.isEmpty(secret)) {
            secret = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(achievementid)) {
            LOG.warn("Achievement '{}' has no achievementId set", getName());
            achievementid = -1;
        }
    }
}
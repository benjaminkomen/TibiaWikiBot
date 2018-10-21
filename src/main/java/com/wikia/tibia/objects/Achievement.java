package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.Grade;
import com.wikia.tibia.enums.YesNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties({"templateType"})
@Getter
@Setter
@NoArgsConstructor
public class Achievement extends WikiObject {

    private Grade grade;
    private String description;
    private String spoiler;
    private YesNo premium;
    private Integer points;
    private YesNo secret;
    private Integer coincideswith;
    private String relatedpages;

    @Override
    public List<String> fieldOrder() {
        return Collections.emptyList();
    }
}
package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Effect extends WikiObject {

    private Integer effectid;
    private String primarytype;
    private String secondarytype;
    private Integer lightradius;
    private Integer lightcolor;
    private String causes;
    @SuppressWarnings("squid:S1700") // class and field name are the same, but that's understandable
    private String effect;


    @Builder
    private Effect(String name, String implemented, String notes, String history, Status status, Integer effectid,
                   String primarytype, String secondarytype, Integer lightradius, Integer lightcolor, String causes,
                   String effect) {
        super(name, null, null, null, implemented, notes, history, status);
        this.effectid = effectid;
        this.primarytype = primarytype;
        this.secondarytype = secondarytype;
        this.lightradius = lightradius;
        this.lightcolor = lightcolor;
        this.causes = causes;
        this.effect = effect;
    }

    @Override
    public void setDefaultValues() {
        // TODO implement this method
    }
}
package com.wikia.tibia.objects;

import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quest extends WikiObject {

    private String aka;
    private String reward;
    private String location;
    private YesNo rookgaardquest;
    private QuestType type;
    private Integer lvl;
    private Integer lvlrec;
    private String lvlnote;
    private YesNo log;
    private String time;
    private String timealloc;
    private YesNo premium;
    private YesNo transcripts;
    private String dangers;
    private String legend;

    @Builder
    private Quest(String name, String implemented, String history, Status status, String aka, String reward,
                  String location, YesNo rookgaardquest, QuestType type, Integer lvl, Integer lvlrec, String lvlnote,
                  YesNo log, String time, String timealloc, YesNo premium, YesNo transcripts, String dangers, String legend) {
        super(name, null, null, null, implemented, null, history, status);
        this.aka = aka;
        this.reward = reward;
        this.location = location;
        this.rookgaardquest = rookgaardquest;
        this.type = type;
        this.lvl = lvl;
        this.lvlrec = lvlrec;
        this.lvlnote = lvlnote;
        this.log = log;
        this.time = time;
        this.timealloc = timealloc;
        this.premium = premium;
        this.transcripts = transcripts;
        this.dangers = dangers;
        this.legend = legend;
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(reward)) {
            reward = "?";
        }

        if (ObjectUtils.isEmpty(location)) {
            location = "?";
        }

        if (ObjectUtils.isEmpty(log)) {
            log = YesNo.UNKNOWN;
        }

        if (ObjectUtils.isEmpty(premium)) {
            premium = YesNo.UNKNOWN;
        }
    }
}
package com.wikia.tibia.objects;

import com.wikia.tibia.enums.QuestType;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.enums.YesNo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Quest extends WikiObject {

    private final String aka;
    private final String reward;
    private final String location;
    private final YesNo rookgaardquest;
    private final QuestType type;
    private final Integer lvl;
    private final Integer lvlrec;
    private final String lvlnote;
    private final YesNo log;
    private final String time;
    private final String timealloc;
    private final YesNo premium;
    private final YesNo transcripts;
    private final String dangers;
    private final String legend;

    private Quest() {
        this.aka = null;
        this.reward = null;
        this.location = null;
        this.rookgaardquest = null;
        this.type = null;
        this.lvl = null;
        this.lvlrec = null;
        this.lvlnote = null;
        this.log = null;
        this.time = null;
        this.timealloc = null;
        this.premium = null;
        this.transcripts = null;
        this.dangers = null;
        this.legend = null;
    }

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
}
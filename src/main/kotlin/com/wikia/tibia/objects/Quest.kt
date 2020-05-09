package com.wikia.tibia.objects

import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Quest @Builder private constructor(name: String, implemented: String, history: String, status: Status, private val aka: String, private var reward: String,
                                         private var location: String, private val rookgaardquest: YesNo, type: QuestType, lvl: Int, lvlrec: Int, lvlnote: String,
                                         log: YesNo, time: String, timealloc: String, premium: YesNo, transcripts: YesNo, dangers: String, legend: String) : WikiObject(name, null, null, null, implemented, null, history, status) {
    private val type: QuestType
    private val lvl: Int
    private val lvlrec: Int
    private val lvlnote: String
    private var log: YesNo
    private val time: String
    private val timealloc: String
    private var premium: YesNo
    private val transcripts: YesNo
    private val dangers: String
    private val legend: String
    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(reward)) {
            reward = "?"
        }
        if (isEmpty(location)) {
            location = "?"
        }
        if (isEmpty(log)) {
            log = YesNo.UNKNOWN
        }
        if (isEmpty(premium)) {
            premium = YesNo.UNKNOWN
        }
    }

    init {
        this.type = type
        this.lvl = lvl
        this.lvlrec = lvlrec
        this.lvlnote = lvlnote
        this.log = log
        this.time = time
        this.timealloc = timealloc
        this.premium = premium
        this.transcripts = transcripts
        this.dangers = dangers
        this.legend = legend
    }
}
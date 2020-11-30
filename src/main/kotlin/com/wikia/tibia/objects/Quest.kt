package com.wikia.tibia.objects

import com.wikia.tibia.enums.QuestType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Quest(
    val name: String,
    val implemented: String,
    val history: String,
    val status: Status,
    val aka: String,
    val reward: String,
    val location: String,
    val rookgaardquest: YesNo,
    val type: QuestType,
    val lvl: Int,
    val lvlrec: Int,
    val lvlnote: String,
    val log: YesNo,
    val time: String,
    val timealloc: String,
    val premium: YesNo,
    val transcripts: YesNo,
    val dangers: String,
    val legend: String
) : WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(reward)) {
            this.copy(reward = "?")
        }
        if (isEmpty(location)) {
            this.copy(location = "?")
        }
        if (isEmpty(log)) {
            this.copy(log = YesNo.UNKNOWN)
        }
        if (isEmpty(premium)) {
            this.copy(premium = YesNo.UNKNOWN)
        }
    }
}

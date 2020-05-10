package com.wikia.tibia.objects

import com.wikia.tibia.enums.QuestType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty

data class Quest(
        private val name: String,
        private val implemented: String,
        private val history: String,
        private val status: Status,
        private val aka: String,
        private val reward: String,
        private val location: String,
        private val rookgaardquest: YesNo,
        private val type: QuestType,
        private val lvl: Int,
        private val lvlrec: Int,
        private val lvlnote: String,
        private val log: YesNo,
        private val time: String,
        private val timealloc: String,
        private val premium: YesNo,
        private val transcripts: YesNo,
        private val dangers: String,
        private val legend: String
) {

    fun setDefaultValues() {
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
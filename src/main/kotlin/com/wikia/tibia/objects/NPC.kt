package com.wikia.tibia.objects

import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Gender
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import java.math.BigDecimal

data class NPC(
         val name: String,
        private val actualname: String,
        private val implemented: String,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val job: String,
        private val job2: String,
        private val job3: String,
        private val job4: String,
        private val job5: String,
        private val job6: String,
        private val location: String,
        private val city: City,
        private val city2: City,
        private val street: String,
        private val posx: BigDecimal,
        private val posy: BigDecimal,
        private val posz: Int,
        private val posx2: BigDecimal,
        private val posy2: BigDecimal,
        private val posz2: Int,
        private val posx3: BigDecimal,
        private val posy3: BigDecimal,
        private val posz3: Int,
        private val posx4: BigDecimal,
        private val posy4: BigDecimal,
        private val posz4: Int,
        private val posx5: BigDecimal,
        private val posy5: BigDecimal,
        private val posz5: Int,
        private val gender: Gender,
        private val race: String,
        private val buysell: YesNo,
        private val buys: String,
        private val sells: String,
        private val sounds: List<String>
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(location)) {
            this.copy(location = "?")
        }
        if (isEmpty(race)) {
            this.copy(race = "?")
        }
        if (isEmpty(buysell)) {
            this.copy(buysell = YesNo.UNKNOWN)
        }
    }
}
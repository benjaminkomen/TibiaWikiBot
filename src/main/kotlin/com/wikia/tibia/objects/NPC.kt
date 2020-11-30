package com.wikia.tibia.objects

import com.wikia.tibia.enums.City
import com.wikia.tibia.enums.Gender
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import java.math.BigDecimal

data class NPC(
    val name: String,
    val actualname: String,
    val implemented: String,
    val notes: String,
    val history: String,
    val status: Status,
    val job: String,
    val job2: String,
    val job3: String,
    val job4: String,
    val job5: String,
    val job6: String,
    val location: String,
    val city: City,
    val city2: City,
    val street: String,
    val posx: BigDecimal,
    val posy: BigDecimal,
    val posz: Int,
    val posx2: BigDecimal,
    val posy2: BigDecimal,
    val posz2: Int,
    val posx3: BigDecimal,
    val posy3: BigDecimal,
    val posz3: Int,
    val posx4: BigDecimal,
    val posy4: BigDecimal,
    val posz4: Int,
    val posx5: BigDecimal,
    val posy5: BigDecimal,
    val posz5: Int,
    val gender: Gender,
    val race: String,
    val buysell: YesNo,
    val buys: String,
    val sells: String,
    val sounds: List<String>
) : WikiObject() {

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

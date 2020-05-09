package com.wikia.tibia.objects

import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*
import java.math.BigDecimal

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NPC @Builder private constructor(name: String, actualname: String, implemented: String, notes: String, history: String, status: Status,
                                       private val job: String, private val job2: String, private val job3: String, private val job4: String, private val job5: String, private val job6: String, private var location: String, city: City,
                                       city2: City, street: String, posx: BigDecimal, posy: BigDecimal, posz: Int, posx2: BigDecimal, posy2: BigDecimal,
                                       posz2: Int, posx3: BigDecimal, posy3: BigDecimal, posz3: Int, posx4: BigDecimal, posy4: BigDecimal, posz4: Int,
                                       posx5: BigDecimal, posy5: BigDecimal, posz5: Int, gender: Gender, race: String, buysell: YesNo, buys: String,
                                       sells: String, sounds: List<String>) : WikiObject(name, null, actualname, null, implemented, notes, history, status) {
    private val city: City
    private val city2: City
    private val street: String
    private val posx: BigDecimal
    private val posy: BigDecimal
    private val posz: Int
    private val posx2: BigDecimal
    private val posy2: BigDecimal
    private val posz2: Int
    private val posx3: BigDecimal
    private val posy3: BigDecimal
    private val posz3: Int
    private val posx4: BigDecimal
    private val posy4: BigDecimal
    private val posz4: Int
    private val posx5: BigDecimal
    private val posy5: BigDecimal
    private val posz5: Int
    private val gender: Gender
    private var race: String
    private var buysell: YesNo
    private val buys: String
    private val sells: String
    private val sounds: List<String>
    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(location)) {
            location = "?"
        }
        if (isEmpty(race)) {
            race = "?"
        }
        if (isEmpty(buysell)) {
            buysell = YesNo.UNKNOWN
        }
    }

    init {
        this.city = city
        this.city2 = city2
        this.street = street
        this.posx = posx
        this.posy = posy
        this.posz = posz
        this.posx2 = posx2
        this.posy2 = posy2
        this.posz2 = posz2
        this.posx3 = posx3
        this.posy3 = posy3
        this.posz3 = posz3
        this.posx4 = posx4
        this.posy4 = posy4
        this.posz4 = posz4
        this.posx5 = posx5
        this.posy5 = posy5
        this.posz5 = posz5
        this.gender = gender
        this.race = race
        this.buysell = buysell
        this.buys = buys
        this.sells = sells
        this.sounds = sounds
    }
}
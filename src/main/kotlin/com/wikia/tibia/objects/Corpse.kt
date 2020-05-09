package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.Article
import com.wikia.tibia.enums.Status
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import java.math.BigDecimal

data class Corpse(
        private val name: String,
        private val article: Article,
        private val actualname: String,
        private var implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val flavortext: String,
        private var skinable: YesNo,
        private val product: String,
        private var liquid: String,
        private val stages: Int,
        @get:JsonGetter("1decaytime") val firstDecaytime: String,
        @get:JsonGetter("2decaytime") val secondDecaytime: String,
        @get:JsonGetter("3decaytime") val thirdDecaytime: String,
        @get:JsonGetter("1volume") val firstVolume: Int,
        @get:JsonGetter("2volume") val secondVolume: Int,
        @get:JsonGetter("3volume") val thirdVolume: Int,
        @get:JsonGetter("1weight") val firstWeight: BigDecimal,
        @get:JsonGetter("2weight") val secondWeight: BigDecimal,
        @get:JsonGetter("3weight") val thirdWeight: BigDecimal,
        private var corpseof: String,
        private val sellto: String
) {

    fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
        if (isEmpty(skinable)) {
            skinable = YesNo.UNKNOWN
        }
        if (isEmpty(liquid)) {
            liquid = "?"
        }
        if (isEmpty(corpseof)) {
            corpseof = "?"
        }
    }

}
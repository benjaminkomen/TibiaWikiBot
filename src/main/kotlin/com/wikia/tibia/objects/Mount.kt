package com.wikia.tibia.objects

import com.fasterxml.jackson.annotation.JsonGetter
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Mount @Builder private constructor(name: String, implemented: String, notes: String, history: String, status: Status, private val speed: Int,
                                         @get:JsonGetter("taming_method")
                                         private val tamingMethod: String, private val bought: YesNo, private val tournament: YesNo, // unit is Tibia Coins
                                         private val price: Int, // this could link to Achievement
                                         private val achievement: String, private val lightradius: Int,
                                         private val lightcolor: Int, private val artwork: String) : WikiObject(name, null, null, null, implemented, notes, history, status) {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
    }

}
package com.wikia.tibia.objects

import com.wikia.tibia.utils.ObjectUtils.isEmpty
import lombok.*

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Street @Builder private constructor(name: String, implemented: String, notes: String, city: City, city2: City, map: String, floor: String) : WikiObject(name, null, null, null, implemented, notes, null, null) {
    private val city: City
    private val city2: City
    private val map: String
    private val floor: String
    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            implemented = "?"
        }
    }

    init {
        this.city = city
        this.city2 = city2
        this.map = map
        this.floor = floor
    }
}
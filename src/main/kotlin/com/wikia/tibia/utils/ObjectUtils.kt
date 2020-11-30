package com.wikia.tibia.utils

import com.wikia.tibia.enums.BestiaryClass
import com.wikia.tibia.enums.BestiaryLevel
import com.wikia.tibia.enums.BookType
import com.wikia.tibia.enums.BuildingType
import com.wikia.tibia.enums.ItemClass
import com.wikia.tibia.enums.KeyType
import com.wikia.tibia.enums.SpellSubclass
import com.wikia.tibia.enums.SpellType
import com.wikia.tibia.enums.YesNo
import com.wikia.tibia.objects.Percentage

object ObjectUtils {
    fun isEmpty(subject: String?): Boolean {
        return subject == null || "" == subject
    }

    fun isEmpty(subject: YesNo?): Boolean {
        return subject == null || YesNo.EMPTY == subject
    }

    fun isEmpty(subject: Percentage?): Boolean {
        return subject == null || Percentage.EMPTY == subject
    }

    fun isEmpty(subject: BestiaryClass?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: BestiaryLevel?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: Int?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: BookType?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: BuildingType?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: List<*>?): Boolean {
        return subject == null || subject.isEmpty()
    }

    fun isEmpty(subject: ItemClass?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: KeyType?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: SpellType?): Boolean {
        return subject == null
    }

    fun isEmpty(subject: SpellSubclass?): Boolean {
        return subject == null
    }
}

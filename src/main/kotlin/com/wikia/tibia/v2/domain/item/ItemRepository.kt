package com.wikia.tibia.v2.domain.item

import com.wikia.tibia.objects.TibiaObject

interface ItemRepository {

  fun getItems(): List<TibiaObject>

  fun getItemNames(): List<String>

  fun getItem(name: String): TibiaObject?

  fun updateItem(item: TibiaObject, editSummary: String? = ""): TibiaObject
}

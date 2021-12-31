package com.wikia.tibia.v2.domain.item

import com.wikia.tibia.objects.TibiaObject

interface ItemRepository {

  suspend fun getItems(): List<TibiaObject>

  suspend fun getItemNames(): List<String>

  suspend fun getItem(name: String): TibiaObject?

  suspend fun updateItem(item: TibiaObject, editSummary: String? = ""): TibiaObject
}

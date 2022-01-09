package com.wikia.tibia

import com.wikia.tibia.enums.ObjectClass
import com.wikia.tibia.enums.Rarity
import com.wikia.tibia.enums.Status
import com.wikia.tibia.objects.Creature
import com.wikia.tibia.objects.Loot
import com.wikia.tibia.objects.LootItem
import com.wikia.tibia.objects.LootStatisticsItem
import com.wikia.tibia.objects.LootWrapper
import com.wikia.tibia.objects.TibiaObject

fun createRat(): Creature {
  return Creature(
    actualname = "Rat",
    name = "Rat",
    loot = mutableListOf(
      LootItem(itemName = "Gold Coin", amount = "0-4"),
      LootItem(itemName = "Cheese")
    )
  )
}

fun createBear(): Creature {
  return Creature(
    actualname = "Bear",
    name = "Bear",
    loot = mutableListOf(
      LootItem(itemName = "Meat", amount = "0-4"),
      LootItem(itemName = "Ham", amount = "0-3"),
      LootItem(itemName = "Bear Paw", rarity = Rarity.SEMI_RARE),
      LootItem(itemName = "Honeycomb", rarity = Rarity.RARE)
    )
  )
}

fun createWasp(): Creature {
  return Creature(
    actualname = "Wasp",
    name = "Wasp",
    loot = mutableListOf(
      LootItem(itemName = "Honeycomb", rarity = Rarity.SEMI_RARE)
    )
  )
}

fun createCyclops(): Creature {
  return Creature(
    actualname = "Cyclops",
    name = "Cyclops",
    loot = mutableListOf(
      LootItem(itemName = "Meat")
    )
  )
}

fun createCheese(): TibiaObject {
  return TibiaObject(
    status = Status.ACTIVE,
    actualname = "Cheese",
    name = "Cheese",
    objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK.description,
    droppedby = mutableListOf("Cave Rat", "Corym Charlatan", "Green Djinn", "Mutated Human", "Rat")
  )
}

fun createHoneycomb(): TibiaObject {
  return TibiaObject(
    status = Status.ACTIVE,
    actualname = "Honeycomb",
    name = "Honeycomb",
    objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK.description,
    droppedby = mutableListOf(
      "Grynch Clan Goblin",
      "Shadowpelt",
      "Werebear",
      "Willi Wasp"
    ) // Bear and Wasp are purposely missing
  )
}

fun createOldRag(): TibiaObject {
  return TibiaObject(
    status = Status.ACTIVE,
    actualname = "Old Rag",
    name = "Old Rag",
    objectclass = ObjectClass.OTHER_ITEMS.description,
    droppedby = mutableListOf(
      "Cyclops",
      "Troll",
      "Wolf",
    )
  )
}

fun createMeat(): TibiaObject {
  return TibiaObject(
    status = Status.ACTIVE,
    actualname = "Meat",
    name = "Meat",
    objectclass = ObjectClass.OTHER_ITEMS.description,
    droppedby = mutableListOf()
  )
}

fun createBearPaw(): TibiaObject {
  return TibiaObject(
    status = Status.ACTIVE,
    actualname = "Bear Paw",
    name = "Bear Paw",
    objectclass = ObjectClass.PLANTS_ANIMAL_PRODUCTS_FOOD_AND_DRINK.description,
    droppedby = mutableListOf("Bear", "Shadowpelt", "Werebear")
  )
}

fun createLootRat(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "14605",
      pageName = "Rat",
      name = "Rat",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Empty", times = "108"),
        LootStatisticsItem(itemName = "Cheese", times = "5741"),
        LootStatisticsItem(itemName = "Gold Coin", times = "14477", amount = "1", total = "20591")
      ),
      version = "9.63"
    )
  )
}

fun createLootRatWithSword(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "14605",
      pageName = "Rat",
      name = "Rat",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Empty", times = "108"),
        LootStatisticsItem(itemName = "Cheese", times = "5741"),
        LootStatisticsItem(itemName = "Gold Coin", times = "14477", amount = "1", total = "20591"),
        LootStatisticsItem(itemName = "Sword", times = "1")
      ),
      version = "9.63"
    )
  )
}

fun createLootAmazon(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "21983",
      pageName = "Amazon",
      name = "Amazon",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Empty", times = "253"),
        LootStatisticsItem(itemName = "Dagger", times = "17606", amount = "1", total = "17606"),
        LootStatisticsItem(itemName = "Skull", times = "17581", amount = "1-2", total = "26316"),
        LootStatisticsItem(itemName = "Gold Coin", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Brown Bread", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Sabre", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Girlish Hair Decoration", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Protective Charm", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Torch", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Crystal Necklace", times = "1", amount = "1", total = "2"),
        LootStatisticsItem(itemName = "Small Ruby", times = "1", amount = "1", total = "1")
      ),
      version = "8.6"
    )
  )
}

fun createAmazon(): Creature {
  return Creature(
    actualname = "Amazon",
    name = "Amazon",
    loot = mutableListOf(
      LootItem(itemName = "Gold Coin", amount = "0-20"),
      LootItem(itemName = "Skull (Item)", amount = "0-2"),
      LootItem(itemName = "Dagger"),
      LootItem(itemName = "Brown Bread"),
      LootItem(itemName = "Sabre"),
      LootItem(itemName = "Girlish Hair Decoration"),
      LootItem(itemName = "Protective Charm"),
      LootItem(itemName = "Torch", rarity = Rarity.RARE),
      LootItem(itemName = "Crystal Necklace", rarity = Rarity.VERY_RARE),
      LootItem(itemName = "Small Ruby", rarity = Rarity.VERY_RARE)
    )
  )
}

fun createDemon(): Creature {
  return Creature(
    actualname = "Demon",
    name = "Demon",
    loot = mutableListOf(LootItem(itemName = "Magic Plate Armor"))
  )
}

fun createLootDemon(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "34026",
      pageName = "Demon",
      name = "Demon",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Small Stone", times = "21", amount = "1-3", total = "38"),
        LootStatisticsItem(itemName = "Mouldy Cheese", times = "10", amount = "1", total = "10"),
        LootStatisticsItem(itemName = "Leather Armor", times = "8", amount = "1", total = "8"),
        LootStatisticsItem(itemName = "Bone", times = "7", amount = "1", total = "7"),
        LootStatisticsItem(itemName = "Demonrage Sword", times = "20", amount = "1", total = "20")
      ),
      version = "10.37"
    )
  )
}

fun createPriestessOfTheWildSun(): Creature {
  return Creature(
    actualname = "Priestess of the Wild Sun",
    name = "Priestess of the Wild Sun",
    loot = mutableListOf(
      LootItem(itemName = "Secret Instruction (Gryphon)"),
      LootItem(itemName = "Secret Instruction (Mirror)"),
      LootItem(itemName = "Secret Instruction (Silver)"),
    )
  )
}

fun createLootPriestessOfTheWildSun(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "2073",
      pageName = "Priestess of the Wild Sun",
      name = "Priestess of the Wild Sun",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Platinum Coin", times = "2073", amount = "1-3", total = "4185"),
        LootStatisticsItem(itemName = "Fafnar Symbol", times = "185", amount = "1", total = "185"),
        LootStatisticsItem(itemName = "Secret Instruction", times = "144", amount = "1", total = "144"),
      ),
      version = "12.31.9580"
    )
  )
}

fun createThePerchtQueen(): Creature {
  return Creature(
    actualname = "The Percht Queen",
    name = "The Percht Queen",
    loot = mutableListOf(
      LootItem(itemName = "Giant Shimmering Pearl (Brown)"),
      LootItem(itemName = "Giant Shimmering Pearl (Green)"),
      LootItem(itemName = "The Crown of the Percht Queen (Ice)"),
      LootItem(itemName = "The Crown of the Percht Queen (Fire)"),
    )
  )
}

fun createLootThePerchtQueen(): LootWrapper {
  return LootWrapper(
    loot2 = Loot(
      kills = "109",
      pageName = "The Percht Queen",
      name = "The Percht Queen",
      loot = mutableListOf(
        LootStatisticsItem(itemName = "Giant Shimmering Pearl", times = "13", amount = "1", total = "13"),
        LootStatisticsItem(itemName = "The Crown of the Percht Queen", times = "1"),
        LootStatisticsItem(itemName = "Horseshoe", times = "1", amount = "1", total = "1"),
      ),
      version = "12.03"
    )
  )
}

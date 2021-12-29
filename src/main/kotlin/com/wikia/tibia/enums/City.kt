package com.wikia.tibia.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.wikia.tibia.interfaces.Description

enum class City(@JsonValue override val description: String) : Description {
  AB_DENDRIEL("Ab'Dendriel"),
  ANKRAHMUN("Ankrahmun"),
  CARLIN("Carlin"),
  CORMAYA("Cormaya"),
  DARASHIA("Darashia"),
  DAWNPORT("Dawnport"),
  EDRON("Edron"),
  FARMINE("Farmine"),
  FEYRIST("Feyrist"),
  GNOMEGATE("Gnomegate"),
  GRAY_BEACH("Gray Beach"),
  ISSAVI("Issavi"),
  ISLAND_OF_DESTINY("Island of Destiny"),
  KAZORDOON("Kazordoon"),
  LIBERTY_BAY("Liberty Bay"),
  MELUNA("Meluna"),
  NOSTALGIA("Nostalgia"),
  PORT_HOPE("Port Hope"),
  RATHLETON("Rathleton"),
  ROOKGAARD("Rookgaard"),
  ROSHAMUUL("Roshamuul"),
  SVARGROND("Svargrond"),
  THAIS("Thais"),
  TRAVORA("Travora"),
  VARIES("Varies"),
  VENORE("Venore"),
  YALAHAR("Yalahar"),
  EMPTY("");
}

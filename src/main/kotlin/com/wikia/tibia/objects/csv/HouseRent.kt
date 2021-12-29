package com.wikia.tibia.objects.csv

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("house_id", "house_name", "new_rent_in_kk", "new_rent_in_gps")
data class HouseRent(
  @JsonProperty("house_id") var houseId: String? = null,
  @JsonProperty("house_name") var houseName: String? = null,
  @JsonProperty("new_rent_in_kk") var newRentInKk: String? = null,
  @JsonProperty("new_rent_in_gps") var newRentInGps: String? = null
)

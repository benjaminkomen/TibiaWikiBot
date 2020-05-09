package com.wikia.tibia.objects.csv

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("house_id", "house_name", "new_rent_in_kk", "new_rent_in_gps")
class HouseRent {
    @JsonProperty("house_id")
    private val houseId: String? = null

    @JsonProperty("house_name")
    private val houseName: String? = null

    @JsonProperty("new_rent_in_kk")
    private val newRentInKk: String? = null

    @JsonProperty("new_rent_in_gps")
    private val newRentInGps: String? = null
}

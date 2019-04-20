package com.wikia.tibia.objects.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonPropertyOrder({"house_id", "house_name", "new_rent_in_kk", "new_rent_in_gps"})
public class HouseRent {

    @JsonProperty("house_id")
    private String houseId;
    @JsonProperty("house_name")
    private String houseName;
    @JsonProperty("new_rent_in_kk")
    private String newRentInKk;
    @JsonProperty("new_rent_in_gps")
    private String newRentInGps;
}

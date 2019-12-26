package com.wikia.tibia.objects;

import lombok.*;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LootStatisticsItem {

    private String itemName;
    private String times;
    private String amount;
    private String total;

    @Override
    public boolean equals(Object other) {
        return other instanceof LootStatisticsItem &&
                Objects.equals(itemName, ((LootStatisticsItem) other).getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }

    @Override
    public String toString() {
        return "LootStatisticsItem{itemName='" + itemName + "'}";
    }
}
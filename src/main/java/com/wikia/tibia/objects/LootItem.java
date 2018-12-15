package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Rarity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LootItem {

    private String itemName;
    private String amount;
    private Rarity rarity;

    public static LootItem fromName(String itemName) {
        return LootItem.builder()
                .itemName(itemName)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof LootItem &&
                Objects.equals(itemName, ((LootItem) other).getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }
}

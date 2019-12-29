package com.wikia.tibia.objects;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Loot {

    private String pageName;
    private String version;
    private String kills;
    private String name;
    private List<LootStatisticsItem> loot;

    public boolean isEmpty() {
        return loot == null ||
                loot.isEmpty() ||
                loot.size() == 1 && "Empty".equals(loot.get(0).getItemName());
    }
}

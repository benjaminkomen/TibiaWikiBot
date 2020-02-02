package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LootWrapper {

    private Loot loot2;
    @JsonProperty("loot2_rc")
    private Loot loot2Rc;
    @JsonIgnore
    private Loot mergedLoot;

    public Loot getMergedLoot() {
        if (mergedLoot == null) {
            mergedLoot = computeMergedLoot();
        }
        return mergedLoot;
    }

    private Loot computeMergedLoot() {

        if (loot2 == null && loot2Rc == null) {
            return null;
        } else if (loot2 == null) {
            return loot2Rc;
        } else if (loot2Rc == null) {
            return loot2;
        } else {
            // they are both non-null
            return Loot.builder()
                    .name(loot2.getName()) // assume loot2Rc does not have a different name
                    .pageName(loot2.getPageName()) // assume they both have the same pagename
                    .version(loot2.getVersion()) // just take the version of loot2, it doesn't really matter
                    .kills(sumKills())
                    .loot(sumLoot(loot2.getLoot(), loot2Rc.getLoot()))
                    .build();
        }
    }

    private String sumKills() {
        return String.valueOf(Integer.parseInt(loot2.getKills()) + Integer.parseInt(loot2Rc.getKills()));
    }

    private List<LootStatisticsItem> sumLoot(List<LootStatisticsItem> first, List<LootStatisticsItem> second) {

        Set<LootStatisticsItem> result = new HashSet<>();

        first.forEach(newItem -> {
            var existingItem = findItem(result, newItem.getItemName());
            if (existingItem.isEmpty()) {
                // item not in list yet, add it
                result.add(newItem);
            } else {
                // item already in list, merge it
                var mergedItem =existingItem.get().add(newItem);
                result.remove(existingItem.get());
                result.add(mergedItem);
            }
        });

        second.forEach(newItem -> {
            var existingItem = findItem(result, newItem.getItemName());
            if (existingItem.isEmpty()) {
                // item not in list yet, add it
                result.add(newItem);
            } else {
                // item already in list, merge it
                var mergedItem = existingItem.get().add(newItem);
                result.remove(existingItem.get());
                result.add(mergedItem);
            }
        });

        return new ArrayList<>(result);
    }

    private Optional<LootStatisticsItem> findItem(Set<LootStatisticsItem> items, String itemName) {
        return items.stream()
                .filter(i -> Objects.equals(i.getItemName(), itemName))
                .findAny();
    }
}

package com.wikia.tibia.objects;

public class LootItem {

    private String quantity;
    private String name;
    private String rariry;

    private LootItem() {
        // no args constructor for Jackson
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRariry() {
        return rariry;
    }

    public void setRariry(String rariry) {
        this.rariry = rariry;
    }
}

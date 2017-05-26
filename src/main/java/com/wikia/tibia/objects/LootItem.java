package com.wikia.tibia.objects;

public class LootItem {

    private String item;
    private String amount;
    private String rarity;

    private LootItem() {
        // no args constructor for Jackson
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}

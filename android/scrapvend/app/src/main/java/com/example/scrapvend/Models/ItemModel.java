package com.example.scrapvend.Models;

public class ItemModel {
    String itemName;
    String itemMeasure;
    float itemRate;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMeasure() {
        return itemMeasure;
    }

    public void setItemMeasure(String itemMeasure) {
        this.itemMeasure = itemMeasure;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }
}

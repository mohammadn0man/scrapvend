package com.example.scrapvend.Models;

public class Pricing_ItemModel {
    private String itemName = "";
    private String itemImage = "";
    private String itemRate = "";
    private String itemMeasure = "";
    private String itemid = "";

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    public String getItemMeasure() {
        return itemMeasure;
    }

    public void setItemMeasure(String itemMeasure) {
        this.itemMeasure = itemMeasure;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Pricing_ItemModel(String itemName, String itemRate , String itemMeasure)
    {
        this.itemName=itemName;
        this.itemRate=itemRate;
        this.itemMeasure=itemMeasure;
    }
}
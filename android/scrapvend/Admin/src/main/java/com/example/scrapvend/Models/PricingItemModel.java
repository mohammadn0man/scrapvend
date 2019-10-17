package com.example.scrapvend.Models;

import java.sql.Blob;

public class PricingItemModel {
    private String itemName = "";
    private String itemRate = "";
    private String itemMeasure = "";
    private String itemId = "";
    private Blob itemImage;

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    private byte[] byteImage;

    public PricingItemModel(String itemId, String itemName, String itemRate , String itemMeasure, Blob itemImage)
    {
        this.itemId =itemId;
        this.itemName=itemName;
        this.itemRate=itemRate;
        this.itemMeasure=itemMeasure;
        this.itemImage = itemImage;
    }
    public PricingItemModel(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Blob getItemImage() {
        return itemImage;
    }

    public void setItemImage(Blob itemImage) {
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
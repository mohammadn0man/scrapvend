package com.example.scrapvend.Models;

import android.graphics.Bitmap;

public class PricingItemModel {
    private String itemName = "";
    private String itemRate = "";
    private String itemMeasure = "";
    private String itemId = "";
    private Bitmap itemImage;
    private byte[] byteImage;


    public PricingItemModel(String itemId, String itemName, String itemRate , String itemMeasure, Bitmap itemImage)
    {
        this.itemId =itemId;
        this.itemName=itemName;
        this.itemRate=itemRate;
        this.itemMeasure=itemMeasure;
        this.itemImage = itemImage;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public PricingItemModel(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
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
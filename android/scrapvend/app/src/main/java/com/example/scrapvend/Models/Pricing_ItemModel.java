package com.example.scrapvend.Models;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.sql.Blob;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Pricing_ItemModel {
    private String itemName = "";
    private String itemRate = "";
    private String itemMeasure = "";
    private String itemid = "";
    private Blob itemImage;
    public Pricing_ItemModel(String itemName, String itemRate , String itemMeasure, Blob itemImage)
    {
        this.itemName=itemName;
        this.itemRate=itemRate;
        this.itemMeasure=itemMeasure;
        this.itemImage = itemImage;
    }
    public Pricing_ItemModel(){}

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

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

}
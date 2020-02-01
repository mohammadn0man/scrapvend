package com.example.login.Models;

import android.graphics.Bitmap;

import java.sql.Blob;

public class ItemQuantityModel {
    String itemName,itemRate, itemqty,itemId;
    private Bitmap itemImage;
    private byte[] byteImage;

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    private String editTextValue;

    public String getItemqty() {
        return itemqty;
    }

    public void setItemqty(String itemqty) {
        this.itemqty = itemqty;
    }

    public ItemQuantityModel(){};

    public ItemQuantityModel(String Itemid,String itemName,String itemRate,Bitmap itemImage)
    {    this.itemName = itemName;
        this.itemRate=itemRate;
        this.itemImage = itemImage;
        this.itemId=Itemid;
    }
    public String getItemId(){ return itemId;}

    public String getitemName() {
        return itemName;
    }

    public String getItemRate() {
        return itemRate;
    }


}




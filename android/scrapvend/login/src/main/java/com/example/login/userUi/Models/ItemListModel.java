package com.example.login.userUi.Models;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemListModel {
    int itemId,viewValue;
    String itemName,itemMeasure;

    float itemrate;

    private byte[] byteImage;

    private Bitmap itemImage;

    public ItemListModel(int itemId, float itemrate, String itemName, String itemMeasure, Bitmap itemImage)
    {
        this.itemId=itemId;
        this.itemrate=itemrate;
        this.itemName=itemName;
        this.itemMeasure=itemMeasure;
        this.itemImage=itemImage;
    }
    public String toString() {


        // TODO Auto-generated method stub


        return this.itemName;


    }
    public float getItemrate() {
        return itemrate;
    }

    public void setItemrate(float itemrate) {
        this.itemrate = itemrate;
    }
    public int getItemId() {
        return itemId;
}

    public void setItemId(int itemId) {
        this.itemId = itemId;
}

    public int getViewValue() {
        return viewValue;
}

    public void setViewValue(int viewValue) {
        this.viewValue = viewValue;
}

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

    public Bitmap getItemImage() {
        return itemImage;
}

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
}

    public byte[] getByteImage() {
        return byteImage;
}

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }
    public ItemListModel(Context context, int resource, ArrayList<ItemListModel> itemList)
    {

    }
}


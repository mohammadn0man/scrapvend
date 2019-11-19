package com.example.pickupperson.Models;

import java.sql.Blob;

public class ItemQuantityModel {
    String itemName,itemRate, itemqty;
    //String contact,address;
    private Blob itemImage;

    public String getItemqty() {
        return itemqty;
    }

    public ItemQuantityModel(String itemName, String itemRate, String itemqty) {
        this.itemName = itemName;
        this.itemRate = itemRate;
        this.itemqty = itemqty;
    }

    public void setItemqty(String itemqty) {
        this.itemqty = itemqty;
    }

    /*public ItemQuantityModel(String itemName,Blob itemImage)
        {    this.itemName = itemName;
             this.itemImage=itemImage;

        }*/
    public ItemQuantityModel(String itemName,String itemRate)
    {    this.itemName = itemName;
        this.itemRate=itemRate;

    }

    // public int getId() {
    //   return id; }


    public String getitemName() {
        return itemName;
    }

    public String getItemRate() {
        return itemRate;
    }

    /*   public String getAddress() {
        return address;
    }


    public String getContact() {
        return contact;
    }

    public String getDate() {
        return date;
    }


*/
}




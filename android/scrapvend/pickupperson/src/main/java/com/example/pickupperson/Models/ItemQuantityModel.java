package com.example.pickupperson.Models;

import java.sql.Blob;

public class ItemQuantityModel {
    String itemName,itemRate;
    //String contact,address;
    private Blob itemImage;

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




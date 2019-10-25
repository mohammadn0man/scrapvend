package com.example.scrapvend.Models;

public class PickupInfoModel {
    String catagoryName;

    public PickupInfoModel(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
}

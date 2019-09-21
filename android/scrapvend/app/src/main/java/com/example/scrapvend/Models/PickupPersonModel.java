package com.example.scrapvend.Models;

public class PickupPersonModel {
    String name, adhaar_no, status, Username;
    String id, rating;
    float salary;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAdhaar() {
        return adhaar_no;
    }

    public PickupPersonModel(String name, String id , String adhaar_no)
{
    this.name=name;
    this.id=id;
    this.adhaar_no=adhaar_no;
}
}
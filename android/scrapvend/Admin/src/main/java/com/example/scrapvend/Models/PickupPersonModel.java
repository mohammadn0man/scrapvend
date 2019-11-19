package com.example.scrapvend.Models;

import android.graphics.Bitmap;

import java.sql.Blob;

public class PickupPersonModel {
    String name, adhaar_no, status, Username;
    String id, rating,salary;
    Blob itemImage;
    private Bitmap bitmapImage;
    private byte[] byteImage;

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    public Blob getItemImage() {
        return itemImage;
    }

    public void setItemImage(Blob itemImage) {
        this.itemImage = itemImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdhaar_no() {
        return adhaar_no;
    }

    public void setAdhaar_no(String adhaar_no) {
        this.adhaar_no = adhaar_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public PickupPersonModel(String name, String adhaar_no , String id , Blob itemImage , String salary , String rating, Bitmap bitmapImage)
{
    this.name=name;
    this.id=id;
    this.adhaar_no=adhaar_no;
    this.itemImage = itemImage;
    this.salary = salary;
    this.rating=rating;
    this.bitmapImage = bitmapImage;
}
    public PickupPersonModel()
    {

    }

}
package com.example.scrapvend.Models;

import android.graphics.Bitmap;

import java.sql.Blob;

public class PickupPersonModel {
    String name, aadhar_no, status, Username;
    String id, rating,salary;
    int t1,t2,t3,t4;

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }

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

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
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
    this.aadhar_no=aadhar_no;
    this.itemImage = itemImage;
    this.salary = salary;
    this.rating=rating;
    this.bitmapImage = bitmapImage;
}
    public PickupPersonModel()
    {

    }
    public PickupPersonModel(String name,String id,int t1,int t2,int t3,int t4)
    {
        this.name=name;
        this.id=id;
        this.t1=t1;
        this.t2=t2;
        this.t3=t3;
        this.t4=t4;
    }

}
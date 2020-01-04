package com.example.login.Models;

public class Details {
    String userName,date;
    String contact,address;


    public Details(String username,String address, String contact, String date) {
        //    this.id = id;
        this.userName = username;
        this.contact = contact;
        this.address = address;
        this.date = date;

    }


    public String getName() {
        return userName;
    }

    public String getAddress() {
        return address;
    }


    public String getContact() {
        return contact;
    }

    public String getDate() {
        return date;
    }



}
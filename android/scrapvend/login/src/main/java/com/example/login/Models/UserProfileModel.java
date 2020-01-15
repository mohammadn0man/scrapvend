package com.example.login.Models;

import java.util.ArrayList;

public class UserProfileModel {

    int userId;
    String username;
    String password;
    String email;
    String contactNo;
    String name;
    ArrayList<UserAddressModel> addressModel;

    public ArrayList<UserAddressModel> getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(ArrayList<UserAddressModel> addressModel) {
        this.addressModel = addressModel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

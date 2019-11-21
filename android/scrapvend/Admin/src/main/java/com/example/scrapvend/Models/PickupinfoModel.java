package com.example.scrapvend.Models;

import android.graphics.Bitmap;

public class PickupinfoModel {
    private String username;
    private String CustomerName;
    private String pickupPersonName;
    private String userId;
    private String bookingId;
    private String location;
    private String pickupStatus;
    private String pickupPersonId;
    private String scheduleTime;
    private String schuduleDate;
    private String assignedDate;
    private String paymentStatus;
    private String paymentMode;
    private String paymentAmount;
    private String pickupDate;
    private String pickupTime;
    private String bookedDate;
    private String bookedTime;
    private String pickupRating;
    private String Address;

    public String getPickupRating() {
        return pickupRating;
    }

    public void setPickupRating(String pickupRating) {
        this.pickupRating = pickupRating;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PickupinfoModel() {
    }

    public PickupinfoModel(String username, String pickupPersonName, String userId, String bookingId, String pickupStatus, String pickupPersonId, String scheduleTime, String schuduleDate, String pickupDate, String pickupTime, String bookedDate, String bookedTime, String location, String address) {
        this.username = username;
        this.pickupPersonName = pickupPersonName;
        this.userId = userId;
        this.bookingId = bookingId;
        this.pickupStatus = pickupStatus;
        this.pickupPersonId = pickupPersonId;
        this.scheduleTime = scheduleTime;
        this.schuduleDate = schuduleDate;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.bookedDate = bookedDate;
        this.bookedTime = bookedTime;
        this.location = location;
        Address = address;
    }

    public PickupinfoModel(String username, String bookedDate, String schuduleDate, String location) {
        this.username = username;
        this.bookedDate = bookedDate;
        this.schuduleDate = schuduleDate;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPickupPersonName() {
        return pickupPersonName;
    }

    public void setPickupPersonName(String pickupPersonName) {
        this.pickupPersonName = pickupPersonName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public String getPickupPersonId() {
        return pickupPersonId;
    }

    public void setPickupPersonId(String pickupPersonId) {
        this.pickupPersonId = pickupPersonId;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getSchuduleDate() {
        return schuduleDate;
    }

    public void setSchuduleDate(String schuduleDate) {
        this.schuduleDate = schuduleDate;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(String bookedTime) {
        this.bookedTime = bookedTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}

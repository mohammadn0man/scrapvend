package com.example.login.Models;

public class HistoryDetails {

    String pickup_date;
    String pickup_time;
    String address;
    String booking_date;
    String booking_time;

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;
    int booking_id;

    public HistoryDetails() {
    }

    public HistoryDetails(int booking_id, String  address, String pickup_date, String Pickup_time) {
        this.pickup_date = pickup_date;
        this.pickup_time = Pickup_time;
        this.address = address;
        this.booking_id =booking_id;

    }


    public String getDate() {
        return pickup_date;
    }

    public String getAddress() {
        return address;
    }


    public int getBookingid() {
        return booking_id;
    }

    public String getTime() {
        return pickup_time;
    }



}
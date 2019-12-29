package com.example.pickupperson.Models;

public class HistoryDetails {

    String pickup_date,pickup_time,address;
    int booking_id;


    public HistoryDetails(int booking_id,String  address,String pickup_date,String Pickup_time) {
        //    this.id = id;
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
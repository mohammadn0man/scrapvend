package com.example.login.Models;

public class HistoryDetails {

    int booking_id;
    String pickup_date;
    String pickup_time;
    String address;
    String booking_date;
    String booking_time;
    String pickup_person_name;
    String pickup_person_contact_no;
    String booking_status;
    String amount;


    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPickup_person_name() {
        return pickup_person_name;
    }

    public void setPickup_person_name(String pickup_person_name) {
        this.pickup_person_name = pickup_person_name;
    }

    public String getPickup_person_contact_no() {
        return pickup_person_contact_no;
    }

    public void setPickup_person_contact_no(String pickup_person_contact_no) {
        this.pickup_person_contact_no = pickup_person_contact_no;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }



    public HistoryDetails() {
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

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
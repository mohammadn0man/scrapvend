package com.example.login.userUi.Models;


public class BookingStatusModel {
    int bid;
    String booking_date_time,status,schedule_date,schedule_time;


    public BookingStatusModel(int bid,String status,String booking_date_time,String schedule_date,String schedule_time)
    {
        this.bid=bid;
        this.status=status;
        this.booking_date_time=booking_date_time;
        this.schedule_date= schedule_date;
        this.schedule_time=schedule_time;
    }
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBooking_date_time() {
        return booking_date_time;
    }

    public void setBooking_date_time(String booking_date_time) {
        this.booking_date_time = booking_date_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }
}

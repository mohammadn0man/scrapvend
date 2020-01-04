package com.example.login.Models;

public class LeaveStatusModel {

  int t1,t2,t3,t4,status;
  String date;
    //String contact,address;



    public LeaveStatusModel(String date,int t1,int t2,int t3,int t4,int status)
    {
        this.date = date;
        this.t1 = t1;
        this.t2 =t2;
        this.t3 =t3;
        this.t4 =t4;
        this.status=status;

    }


    public String getDate() {
        return date;
    }

    public int getT1() {
        return t1;
    }
    public int getT2() {
        return t2;

    }
    public int getT3() {
        return t3;
    }
    public int getT4() {
        return t4;
    }

    public int getStatus() {
        return status;
    }

}




package com.example.pickupperson.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.LeaveStatusModel;
import com.example.pickupperson.R;
import com.example.pickupperson.ui.LeaveRequest.LeaveStatus;
import com.example.pickupperson.ui.MySQLConnector;
import com.example.pickupperson.ui.home.HomeFragment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LeaveStatusAdapter extends ArrayAdapter<LeaveStatusModel> {
    Context context;
    int resourse;
    ArrayList<LeaveStatusModel> historylist;


    public int getCount(){return super.getCount();}
    public LeaveStatusAdapter(@NonNull Context context, int resource, ArrayList<LeaveStatusModel> historylist) {

        super(context,resource,historylist);
        this.context=context;
        this.resourse=resource;
        this.historylist= historylist;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.leavestatuslistview, null);

        final TextView time1,time2,time3,time4,date,status;
        final Button cancel;
        final int t1,t2,t3,t4;
        final int s;

        time1=v.findViewById(R.id.timeSlot1);
        time2=v.findViewById(R.id.timeSlot2);
        time3=v.findViewById(R.id.timeSlot3);
        time4=v.findViewById(R.id.timeSlot4);
        date= v.findViewById(R.id.textViewDate);
        status=v.findViewById(R.id.textViewStatus);
        cancel=v.findViewById(R.id.Cancelbutton);
        final LeaveStatusModel employee=getItem(position);

        t1=employee.getT1();
        t2=employee.getT2();
        t3=employee.getT3();
        t4=employee.getT4();
        s=employee.getStatus();
        if(t1==0) {
            time1.setVisibility(View.INVISIBLE);
        }
        if(t2==0) {
            time2.setVisibility(View.INVISIBLE);
        }
        if(t3==0) {
            time3.setVisibility(View.INVISIBLE);
        }if(t4==0) {
            time4.setVisibility(View.INVISIBLE);
        }
        if(s==0) {
            status.setText("Pending");

        }
        else if(s==1) {
            status.setText("Accepted");
            cancel.setBackgroundColor(Color.GRAY);
        }
        else {
            status.setText("Canceled");
            cancel.setBackgroundColor(Color.GRAY);
        }


        date.setText(employee.getDate());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s==0)
                {

                    Toast.makeText(context, "Request Cancelled", Toast.LENGTH_SHORT).show();
                     new task().execute();

                }

            }
        });

        return v;
    }


    private class task extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySQLConnector connection = new MySQLConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();

                String query ="UPDATE `pickup_person_record` SET `Approval_status`=? WHERE Pickup_person_id=? && Date=\"123456\"";
                PreparedStatement preparedStatement1 = conn.prepareStatement(query);

                preparedStatement1.setInt(1, 2);
//                preparedStatement1.setString(2, Date);
                preparedStatement1.setInt(2, 1);
//                preparedStatement1.setInt(4, t2);
//                preparedStatement1.setInt(5, t3);
//                preparedStatement1.setInt(6, t4);
//                preparedStatement1.setInt(7, 0);


                preparedStatement1.execute();

                Log.d(TAG, "Leave query");
                Log.d(TAG, query);

                //ResultSet results = statement.executeQuery(query);
                Log.d(TAG, " leavestatus query executed");
                Log.d(TAG, "results.next()");


                //results.next();
                Log.d(TAG,"values inserted");

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
           /* Log.d(TAG, "inside post execute");
            padapter = new LeaveStatusAdapter(context, R.layout.leavestatuslistview, arrayOfEmp);

            Log.d(TAG, "add values");
            listView.setAdapter(padapter); */


            super.onPostExecute(aVoid);
//            getContext()
        }
    }
}


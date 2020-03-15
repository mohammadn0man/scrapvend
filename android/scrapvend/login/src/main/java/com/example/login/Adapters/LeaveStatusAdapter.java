package com.example.login.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.LeaveStatusModel;
import com.example.login.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.annotation.NonNull;

import static com.example.login.MainActivity.user;


public class LeaveStatusAdapter extends ArrayAdapter<LeaveStatusModel> {
    Context context;
    int t1,t2,t3,t4;
    int resourse;
    ArrayList<LeaveStatusModel> historylist;
    LeaveStatusAdapter adapter;
    LeaveStatusModel model;
    LeaveStatusModel employee;
    private final String TAG = "MyDBhome";

    public LeaveStatusAdapter(@NonNull Context context, int resource, ArrayList<LeaveStatusModel> historylist) {

        super(context,resource,historylist);
        this.context=context;
        this.resourse=resource;
        this.historylist= historylist;
    }

    public int getCount(){return super.getCount();}

    public View getView(int position, View convertView, ViewGroup parent){
        final View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.leavestatuslistview, null);

        final TextView time1,time2,time3,time4,date,status;
        final Button cancel;
        final int s;

        time1=v.findViewById(R.id.timeSlot1);
        time2=v.findViewById(R.id.timeSlot2);
        time3=v.findViewById(R.id.timeSlot3);
        time4=v.findViewById(R.id.timeSlot4);
        date= v.findViewById(R.id.textViewDate);
        status=v.findViewById(R.id.textViewStatus);
        cancel=v.findViewById(R.id.Cancelbutton);
        employee=getItem(position);

        Log.e(TAG, "----> " + employee.getDate());

        t1=employee.getT1();
        t2=employee.getT2();
        t3=employee.getT3();
        t4=employee.getT4();
        s=employee.getStatus();
        if(t1==1) {
            time1.setVisibility(View.VISIBLE);
            Log.e(TAG, "----> " + employee.getDate());
        }
        if(t2==1) {
            time2.setVisibility(View.VISIBLE);
        }
        if(t3==1) {
            time3.setVisibility(View.VISIBLE);
        }
        if(t4==1) {
            time4.setVisibility(View.VISIBLE);
        }
        if(s==0) {
            status.setText("Pending");

        }
        else if(s==1) {
            status.setText("Accepted");
            cancel.setBackgroundColor(Color.GRAY);
        }
        else if (s==2)
        {
            status.setText("Canceled");
            cancel.setBackgroundColor(Color.GRAY);
        }
        else {
            status.setText("Rejected");
            cancel.setBackgroundColor(Color.GRAY);
        }


        date.setText(employee.getDate());


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s==0)
                {

                     new task(employee.getDate()).execute();

                    Toast.makeText(context, "Request Cancelled", Toast.LENGTH_SHORT).show();
                    // new LeaveStatus().change();
//
//                    if(context instanceof LeaveStatus){
//                        ((LeaveStatus)context).change();

                }

            }
        });
        return v;
    }
    private class task extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
                String date;
        task(String date)
        {
            this.date=date;

            Log.d(TAG, "emploee date :"+date);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();
                String q="SELECT  Pickup_person_id FROM pickup_person_details where Username= \""+user+"\"";
                ResultSet results = statement.executeQuery(q);

                results.next();
                int pickup_id=results.getInt(1);

                Log.d(TAG, "emploee date :"+date);
                String query ="UPDATE `pickup_person_record` SET `Approval_status`=2 WHERE Pickup_person_id="+pickup_id+" and Date=\""+date+"\"";
                PreparedStatement preparedStatement1 = conn.prepareStatement(query);

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


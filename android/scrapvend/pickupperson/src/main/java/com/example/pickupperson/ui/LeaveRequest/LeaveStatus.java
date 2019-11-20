package com.example.pickupperson.ui.LeaveRequest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pickupperson.Adapters.DetailsAdapter;
import com.example.pickupperson.Adapters.LeaveStatusAdapter;
import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.LeaveStatusModel;
import com.example.pickupperson.R;
import com.example.pickupperson.ui.MySQLConnector;
import com.example.pickupperson.ui.home.ItemQuantity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LeaveStatus extends AppCompatActivity
{
    TextView date,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot,status;
    Button cancelBtn;
    Context context;
    int flag=0;
    ListView listView;
    LeaveStatusModel pmodel;
    LeaveStatusAdapter padapter;
final  ArrayList<LeaveStatusModel> arrayOfEmp = new ArrayList<>();
    private final String TAG = "MyDBpage2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leavestatuslayout);
        timeSlot1 = findViewById(R.id.timeSlot1);
        timeSlot2 = findViewById(R.id.timeSlot2);
        timeSlot3 = findViewById(R.id.timeSlot3);
        timeSlot4 = findViewById(R.id.timeSlot4);
        date = findViewById(R.id.textViewDate);
        timeSlot = findViewById(R.id.textViewTime);
        cancelBtn = findViewById(R.id.Cancelbutton);
        status = findViewById(R.id.textViewStatus);
        listView = (ListView)findViewById(R.id.LeaveStatusList);
//        listView.setItemsCanFocus(true);

//        context = this.getContext();


        new task().execute();
        context = this.getApplicationContext();

    }
    private class task extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySQLConnector connection = new MySQLConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();

                String query ="Select * from `pickup_person_record` where  Pickup_person_id=1";

                Log.d(TAG, "Leave query");
                Log.d(TAG, query);

                ResultSet results = statement.executeQuery(query);
                Log.d(TAG, " leavestatus query executed");
                Log.d(TAG, "results.next()");

                while (results.next()) {
                    Log.d(TAG,results.getString("Date")+" "+ results.getInt(3)+" "+results.getInt(4)+" "+ results.getInt(5)+" "+results.getInt(6)+" "+results.getInt(7));

                    pmodel = new LeaveStatusModel(results.getString("Date"), results.getInt(3), results.getInt(4), results.getInt(5),results.getInt(6),results.getInt(7));
                    arrayOfEmp.add(pmodel);
                }
                Log.d(TAG,"values inserted");

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            Log.d(TAG, "inside post execute");
            padapter = new LeaveStatusAdapter(context, R.layout.leavestatuslistview, arrayOfEmp);

            Log.d(TAG, "add values");
            listView.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}






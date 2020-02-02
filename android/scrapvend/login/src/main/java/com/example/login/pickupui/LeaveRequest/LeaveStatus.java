package com.example.login.pickupui.LeaveRequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.login.Adapters.LeaveStatusAdapter;
import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.LeaveStatusModel;
import com.example.login.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.example.login.MainActivity.user;

public class LeaveStatus extends AppCompatActivity
{
    TextView date,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot,status;
    Button cancelBtn;
    Context context;
    int flag=0;
    ListView listView;
    LeaveStatusModel pmodel;
    LeaveStatusAdapter padapter;
    SwipeRefreshLayout swipeRefreshLayout;
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
         swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.pullToRefresh);

//        context = this.getContext();
//        runOnUiThread(new Runnable() {
//            public void run() {
//                padapter.notifyDataSetChanged();
//            }
//        });


        new task().execute();
        context = this.getApplicationContext();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "add ref values");
                new task().execute();
                Log.d(TAG, "add ref values");
                padapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public class task extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();

                String query ="Select * from pickup_person_record INNER JOIN pickup_person_details ON pickup_person_record.Pickup_person_id = pickup_person_details.Pickup_person_id " +
                        " where pickup_person_details.Username = \'" + user + "\'";

                Log.d(TAG, "Leave query");
                Log.d(TAG, query);

                ResultSet results = statement.executeQuery(query);
                Log.d(TAG, " leavestatus query executed");
                Log.d(TAG, "results.next()");
                arrayOfEmp.clear();
                while (results.next()) {
                    Log.d(TAG,results.getString("Date")+" "+ results.getInt(3)+" "+results.getInt(4)+" "+ results.getInt(5)+" "+results.getInt(6)+" "+results.getInt(7));

                    pmodel = new LeaveStatusModel(results.getString("Date"), results.getInt("10:00AM-12:00PM"), results.getInt("12:00PM-02:00PM"), results.getInt("02:00PM-04:00PM"),results.getInt("04:00PM-06:00PM"),results.getInt("Approval_status"));

                    arrayOfEmp.add(pmodel);
                }
                Log.d(TAG,"values inserted");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            Log.d(TAG, "inside post execute");
            padapter = new LeaveStatusAdapter(context, R.layout.leavestatuslistview, arrayOfEmp);

            Log.d(TAG, "add values" + arrayOfEmp.size());

            listView.setAdapter(padapter);

//            padapter.notifyDataSetChanged();

            super.onPostExecute(aVoid);
        }
    }
}






package com.example.scrapvend.ui.pickupinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.scrapvend.Adapters.PickupinfoAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupinfoModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PickupinfoList extends AppCompatActivity {

    ListView listView;
    final static String TAG = "MyPickupinfoList";
    PickupinfoModel pickupinfoModel;
    PickupinfoAdapter pickupinfoAdapter;
    ArrayList<PickupinfoModel> pickupinfoModelArrayList = new ArrayList<>();
    String GET_PICKUPLIST_FLAG;
    String[] pickupinfoCategory;

    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.pickupinfo_list);
        listView = (ListView) findViewById(R.id.pickupinfo_listview);

        pickupinfoCategory = getResources().getStringArray(R.array.pickupinfo_category_name);

        Bundle bundle = getIntent().getExtras();
        GET_PICKUPLIST_FLAG = bundle.getString("GET_PICKUPINFO_FLAG");

        new PickupinfoTask().execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PickupinfoModel pickupinfoModel = pickupinfoModelArrayList.get(i);
                Log.e(TAG, "Booking id to transfer " + pickupinfoModel.getBookingId());
                if(GET_PICKUPLIST_FLAG.equals("Pickup Completed")) {
                    Intent intent = new Intent(getApplicationContext(), PickupInfoCompletedPickupView.class);
                    intent.putExtra("GET_PICKUPLIST_FLAG", GET_PICKUPLIST_FLAG);
                    intent.putExtra("ADDRESS", pickupinfoModel.getLocation());
                    intent.putExtra("BOOKING_ID", pickupinfoModel.getBookingId());
                    startActivity(intent);
                }
                else if(GET_PICKUPLIST_FLAG.equals("Pickup Person Assigned")) {
                    Intent intent = new Intent(getApplicationContext(), PickupInfoPickupPersonAssigned.class);
                    intent.putExtra("GET_PICKUPLIST_FLAG", GET_PICKUPLIST_FLAG);
                    intent.putExtra("ADDRESS", pickupinfoModel.getLocation());
                    intent.putExtra("BOOKING_ID", pickupinfoModel.getBookingId());
                    startActivity(intent);
                }
                else if(GET_PICKUPLIST_FLAG.equals("Pending Pickup")) {
                    Intent intent = new Intent(getApplicationContext(), PickupInfoPendingPickup.class);
                    intent.putExtra("GET_PICKUPLIST_FLAG", GET_PICKUPLIST_FLAG);
                    intent.putExtra("ADDRESS", pickupinfoModel.getLocation());
                    intent.putExtra("BOOKING_ID", pickupinfoModel.getBookingId());
                    intent.putExtra("USERNAME", pickupinfoModel.getUsername());
                    intent.putExtra("SCHEDULEDDATE", pickupinfoModel.getSchuduleDate());
                    startActivity(intent);
                }

            }
        });

    }

    private class PickupinfoTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT booking_details.Booking_date_time, booking_details.Scheduled_pickup_date_time, user_details.Username, address.House_no , address.Line_1, address.City, booking_details.Booking_id FROM booking_details INNER JOIN user_details ON user_details.User_id = booking_details.User_id INNER JOIN address ON booking_details.Address_id = address.Address_id where Pickup_status = \""+ GET_PICKUPLIST_FLAG + "\";");

                while (results.next()) {
                    Log.d(TAG, results.getString(1) + results.getString(2));
                    pickupinfoModel = new PickupinfoModel(results.getString(3), results.getString(1),results.getString(2),results.getString(4)+" "+results.getString(5)+ " " +results.getString(6));
                    pickupinfoModel.setBookingId(results.getString(7));
                    pickupinfoModelArrayList.add(pickupinfoModel);
                }


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

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            pickupinfoAdapter = new PickupinfoAdapter(getBaseContext(), R.layout.pickupinfo_list, pickupinfoModelArrayList);
            listView.setAdapter(pickupinfoAdapter);
        }

    }
}

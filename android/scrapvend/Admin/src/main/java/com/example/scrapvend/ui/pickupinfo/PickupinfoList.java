package com.example.scrapvend.ui.pickupinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    final static String TAG = "MyPickupinfolist";
    PickupinfoModel pickupinfoModel;
    PickupinfoAdapter pickupinfoAdapter;
    ArrayList<PickupinfoModel> pickupinfoModelArrayList = new ArrayList<>();
    String GET_PICKUPLIST_FLAG;


    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.pickupinfo_list);
        listView = (ListView) findViewById(R.id.pickupinfo_listview);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        GET_PICKUPLIST_FLAG = bundle.getString("GET_PICKUPINFO_FLAG");

        new PickupinfoTask().execute();

    }

    private class PickupinfoTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM `booking_details` where Pickup_status = \""+ GET_PICKUPLIST_FLAG + "\";");

                while (results.next()) {
                    Log.d(TAG, results.getString(1) + results.getString(2));
                    pickupinfoModel = new PickupinfoModel(results.getString(2), results.getString(3),results.getString(4),results.getString(6));
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

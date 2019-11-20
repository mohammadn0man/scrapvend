package com.example.scrapvend.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.Adapters.LeaverequestAdapter;
import com.example.scrapvend.Adapters.PickuppersonAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OnLeave extends AppCompatActivity {

    ListView leaverequest,onleave;
    View request,leave;
    TextView name;
    PickupPersonModel pmodel;
    LeaverequestAdapter padapter;
    Context context;
    ArrayList<PickupPersonModel> arr = new ArrayList<>();
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave);

        leaverequest =(ListView) findViewById(R.id.request);
        onleave =(ListView) findViewById(R.id.leave);
//        leave =(View) findViewById(R.id.onleave);
//        request =(View) findViewById(R.id.requestforleave);
        name = (TextView) findViewById(R.id.textView);
        new PickupPersonTask().execute();
        context = getApplicationContext();
    }
    private class PickupPersonTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();
                Log.d(TAG, "query :"+"SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.\"12:00PM-02:00PM\",pickup_person_record.\"02:00PM-04:00PM\",pickup_person_record.\"04:00PM-06:00PM\" FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id;");
                ResultSet results = statement.executeQuery("SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.`12:00PM-02:00PM`,pickup_person_record.`02:00PM-04:00PM`,pickup_person_record.`04:00PM-06:00PM` FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id;");
                while (results.next()) {
                    Log.d(TAG, results.getString(1));
                    pmodel = new PickupPersonModel(results.getString(1),results.getString(2),results.getInt(3),results.getInt(4),results.getInt(5),results.getInt(6));
                    arr.add(pmodel);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
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
            padapter = new LeaverequestAdapter(context, R.layout.leave_request_list_items, arr);
            onleave.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}

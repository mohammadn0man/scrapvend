package com.example.scrapvend.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.Adapters.LeaverequestAdapter;
import com.example.scrapvend.Adapters.OnleaveAdapter;
import com.example.scrapvend.Adapters.PickuppersonAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;

public class OnLeave extends AppCompatActivity {

    ListView leaverequest,onleave;
    TextView name,leave,request;
    PickupPersonModel pmodel,pickupPersonModel;
    LeaverequestAdapter padapter;
    OnleaveAdapter onleaveadapter;
    Context context;
    int flag,temp;
    ArrayList<PickupPersonModel> arr = new ArrayList<>();
    ArrayList<PickupPersonModel> onleavelist = new ArrayList<>();
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave);

        leaverequest =(ListView) findViewById(R.id.request);
        onleave =(ListView) findViewById(R.id.leave);
        name = (TextView) findViewById(R.id.textView);
        leave=(TextView) findViewById(R.id.textView8);
        request=(TextView) findViewById(R.id.textView15);


        new PickupPersonTask().execute();
        context = getApplicationContext();
    }


    private class PickupPersonTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                Statement statement = conn.createStatement();
                Log.d(TAG, "query :"+"SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.\"12:00PM-02:00PM\",pickup_person_record.\"02:00PM-04:00PM\",pickup_person_record.\"04:00PM-06:00PM\" FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id;");
                ResultSet results = statement.executeQuery("SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.`12:00PM-02:00PM`,pickup_person_record.`02:00PM-04:00PM`,pickup_person_record.`04:00PM-06:00PM` FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id where Approval_status=0;");
                if(results.next()) {
                    while (results.next()) {
                        Log.d(TAG, results.getString(1));
                        pmodel = new PickupPersonModel(results.getString(1), results.getString(2), results.getInt(3), results.getInt(4), results.getInt(5), results.getInt(6));
                        arr.add(pmodel);
                    }
                }
                else{
                        temp=1;
                }
                ResultSet result = statement.executeQuery("SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.`12:00PM-02:00PM`,pickup_person_record.`02:00PM-04:00PM`,pickup_person_record.`04:00PM-06:00PM` FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id where Date=\""+date+"\" and Approval_status =1;");
                Log.d(TAG, "query :"+"SELECT pickup_person_details.Name,pickup_person_details.Pickup_person_id,pickup_person_record.`10:00AM-12:00PM` ,pickup_person_record.`12:00PM-02:00PM`,pickup_person_record.`02:00PM-04:00PM`,pickup_person_record.`04:00PM-06:00PM` FROM `pickup_person_details` INNER JOIN `pickup_person_record` ON pickup_person_details.Pickup_person_id=pickup_person_record.Pickup_person_id where Date=\""+date+"\" and Approval_status =1");
                if(result.next()) {
                    while (result.next()) {
                        Log.d(TAG, result.getString(1));
                        pickupPersonModel = new PickupPersonModel(result.getString(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5), result.getInt(6));
                        onleavelist.add(pickupPersonModel);
                    }
                }
                else{
                        flag=1;
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
            if(temp==1)
            {
                request.setVisibility(GONE);
                onleave.setVisibility(GONE);
            }
            if(flag==1)
            {
                leave.setVisibility(GONE);

            }
            padapter = new LeaverequestAdapter(context, R.layout.leave_request_list_items, arr);
            onleave.setAdapter(padapter);
            onleaveadapter = new OnleaveAdapter(context, R.layout.onleave_list_items, onleavelist);
            leaverequest.setAdapter(onleaveadapter);


            super.onPostExecute(aVoid);
        }
    }

    //LEAVE GRANT BUTTON FUNCTIONALITY


    public static class Leavegrant extends AsyncTask<Void, Void, Void> {

        String id;
        int temp;
        int c1,c2,c3,c4;
         LeaverequestAdapter padapter;

        public Leavegrant(String id,int c1,int c2,int c3,int c4){
            this.id=id;
            this.c1=c1;
            this.c2=c2;
            this.c3=c3;
            this.c4=c4;

        }

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();

            try {

                Log.d(TAG, "query :"+id);
                Log.d(TAG,"update pickup_person_details set `10:00AM-12:00PM`=\""+temp+"\",`12:00PM-02:00PM`=\""+temp+"\",`02:00PM-04:00PM`=\" "+temp+"\",`04:00PM-06:00PM`=\" "+temp+"\"");
                String query ="update pickup_person_record set `10:00AM-12:00PM`="+c1+",`12:00PM-02:00PM`=\""+c2+"\",`02:00PM-04:00PM`=\""+c3+"\",`04:00PM-06:00PM`=\""+c4+"\",`Approval_status`=3 WHERE Pickup_person_id = "+id+"";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    padapter.notifyDataSetChanged();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
        }
    }
}



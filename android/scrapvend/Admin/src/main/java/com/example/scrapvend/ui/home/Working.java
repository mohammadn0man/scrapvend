package com.example.scrapvend.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.Adapters.WorkingAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;
import com.example.scrapvend.ui.pickupperson.PickuppersonFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Working extends AppCompatActivity {

    CalendarView calendarView;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    String selectedDate = sdf.format(new Date());
    ResultSet resultSetListOfPickupPerson;
    ResultSet resultSetLeaveRecord;
    ListView listView;
    WorkingAdapter workingAdapter;
    PickupPersonModel pickupPersonModel;
    ArrayList<PickupPersonModel> pickupPersonModelArrayList;
    private static String TAG = "MyActivityWorking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_person);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        listView = (ListView) findViewById(R.id.workingListView);
        new WorkingTask().execute();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + ++month + "-" + dayOfMonth;
                listView.setAdapter(null);
                new WorkingTask().execute();
                Toast.makeText(getApplicationContext(),"Showing for date " + selectedDate,Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });

    }

    private class WorkingTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Connection connection = null;

            try {
                MySqlConnector mySqlConnector = new MySqlConnector();
                connection = mySqlConnector.getMySqlConnection();
                Statement statement = connection.createStatement();
                String queryPickupPersonList = "select pickup_person_details.Name, pickup_person_details.Address from pickup_person_details " +
                        "where Pickup_person_id not in " +
                        "(select pickup_person_record.Pickup_person_id from pickup_person_record" +
                            " where pickup_person_record.Approval_status = 3" +
                            " and pickup_person_record.`10:00AM-12:00PM` = 1" +
                            " and pickup_person_record.`02:00PM-04:00PM` = 1" +
                            " and pickup_person_record.`12:00PM-02:00PM` = 1" +
                            " and pickup_person_record.`04:00PM-06:00PM` = 1" +
                            " and pickup_person_record.Date = \"" + selectedDate + "\")";

                Log.e(TAG, "query = " + queryPickupPersonList);

                resultSetListOfPickupPerson = statement.executeQuery(queryPickupPersonList);

                pickupPersonModelArrayList = new ArrayList<>();

                while (resultSetListOfPickupPerson.next()){
                    pickupPersonModel = new PickupPersonModel();
                    Log.e(TAG, resultSetListOfPickupPerson.getString(1));
                    pickupPersonModel.setName(resultSetListOfPickupPerson.getString(1));
                    pickupPersonModel.setAddress(resultSetListOfPickupPerson.getString(2));

                    pickupPersonModelArrayList.add(pickupPersonModel);
                }

            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);


            workingAdapter = new WorkingAdapter(getBaseContext(), R.layout.working_list_layout,pickupPersonModelArrayList);
            listView.setAdapter(workingAdapter);

        }

    }
}

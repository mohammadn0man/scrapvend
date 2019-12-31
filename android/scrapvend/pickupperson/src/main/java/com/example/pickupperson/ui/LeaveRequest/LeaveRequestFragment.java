
package com.example.pickupperson.ui.LeaveRequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pickupperson.R;
import com.example.pickupperson.ui.MySQLConnector;
import com.example.pickupperson.ui.home.DetailedPickupInfo;
import com.example.pickupperson.ui.home.ItemQuantity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class LeaveRequestFragment extends Fragment {

    private LeaveRequestViewModel leaveRequestModel;
    CalendarView calender;
    Context context;
    TextView timeSlot;
    CheckBox timeSlot1, timeSlot2, timeSlot3, timeSlot4;
    Button requestBtn,historyBtn;
    boolean validDate = false;
    String Date;
    private final String TAG = "MyLeaverRequestPage";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leaveRequestModel =
                ViewModelProviders.of(this).get(LeaveRequestViewModel.class);
        View root = inflater.inflate(R.layout.leaverequest, container, false);
        Log.d(TAG, "inside leaveRequest page");

        calender = root.findViewById(R.id.simpleCalendarView);
        timeSlot = root.findViewById(R.id.timeSlot);
        timeSlot1 = root.findViewById(R.id.checkBox1);
        timeSlot2 = root.findViewById(R.id.checkBox2);
        timeSlot3 = root.findViewById(R.id.checkBox3);
        timeSlot4 = root.findViewById(R.id.checkBox4);
        requestBtn = root.findViewById(R.id.requestBtn);
        historyBtn=root.findViewById(R.id.historyBtn);


        context = this.getContext();

        Calendar c = Calendar.getInstance();
        final int currDay = c.get(Calendar.DAY_OF_MONTH);
        final int currMonth = c.get(Calendar.MONTH);
        final int currYear = c.get(Calendar.YEAR);


        // Add Listener in calendar
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            // In this Listener have one method and in this method we will get the value of DAYS, MONTH, YEARS
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {// Store the value of date with format in String type Variable  Add 1 in month because month index is start with 0
                Date =  year + "-" + (month + 1) + "-" + dayOfMonth;

                //   timeSlot.setText(Date);


                if (currYear != year) {
                    validDate = year > currYear;
                } else if (currMonth != month) {
                    validDate = month > currMonth;
                } else
                    validDate = dayOfMonth > currDay;


            }
        });

        requestBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!validDate) {
                    Toast.makeText(getContext(), "Select a valid date!", Toast.LENGTH_SHORT).show();
                } else if (!timeSlot1.isChecked() && !timeSlot2.isChecked() && !timeSlot3.isChecked() && !timeSlot4.isChecked()) {
                    Toast.makeText(getContext(), "Select a time slot!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Request submitted !", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "request button clicked");

                    new InsertIntoDatabaseTask().execute();
                }

            }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity(), LeaveStatus.class);
                Log.d(TAG, "intent sent");
                 startActivity(i);

            }
        });

        context = this.getContext();


        return root;
    }


    private class InsertIntoDatabaseTask extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            MySQLConnector connection = new MySQLConnector();

            Connection conn = connection.getMySqlConnection();


            try {

                Log.d(TAG, "leave requestConnection established");
                int t1=0,t2=0,t3=0,t4=0;
                if(timeSlot1.isChecked())
                    t1=1;
                if(timeSlot2.isChecked())
                    t2=1;
                if(timeSlot3.isChecked())
                    t3=1;
                if(timeSlot4.isChecked())
                    t4=1;


                String query1 = "INSERT INTO `pickup_person_record`(`Pickup_person_id`, `Date`, `10:00AM-12:00PM`, `12:00PM-02:00PM`, `02:00PM-04:00PM`,`04:00PM-06:00PM`,`Approval_status`) VALUES (?,?,?,?,?,?,?)";
                Log.d(TAG, "query = " + query1);
                PreparedStatement preparedStatement1 = conn.prepareStatement(query1);

                preparedStatement1.setInt(1, 1);
                preparedStatement1.setString(2, Date);
                preparedStatement1.setInt(3, t1);
                preparedStatement1.setInt(4, t2);
                preparedStatement1.setInt(5, t3);
                preparedStatement1.setInt(6, t4);
                preparedStatement1.setInt(7, 0);


                preparedStatement1.execute();
                Log.d(TAG, "leave query Execute ");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "inside finally");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);


        }

    }
}



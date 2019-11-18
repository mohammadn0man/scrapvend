package com.example.pickupperson.ui.LeaveRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pickupperson.R;
import com.example.pickupperson.ui.home.DetailedPickupInfo;
import com.example.pickupperson.ui.home.ItemQuantity;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class LeaveRequestFragment extends Fragment {

    private LeaveRequestViewModel leaveRequestModel;
    CalendarView calender;
    Context context;
    TextView timeSlot;
    CheckBox timeSlot1,timeSlot2,timeSlot3,timeSlot4;
    Button requestBtn;
   boolean validDate=false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leaveRequestModel =
                ViewModelProviders.of(this).get(LeaveRequestViewModel.class);
        View root = inflater.inflate(R.layout.leaverequest, container, false);

        calender=root.findViewById(R.id.simpleCalendarView);
        timeSlot=root.findViewById(R.id.timeSlot);
        timeSlot1=root.findViewById(R.id.checkBox1);
        timeSlot2=root.findViewById(R.id.checkBox2);
        timeSlot3=root.findViewById(R.id.checkBox3);
        timeSlot4=root.findViewById(R.id.checkBox4);
        requestBtn=root.findViewById(R.id.requestBtn);

        context = this.getContext();

        Calendar c = Calendar.getInstance();
        final int currDay = c.get(Calendar.DAY_OF_MONTH);
        final int currMonth = c.get(Calendar.MONTH);
        final int currYear = c.get(Calendar.YEAR);


        // Add Listener in calendar
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            // In this Listener have one method and in this method we will get the value of DAYS, MONTH, YEARS
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,int dayOfMonth) {// Store the value of date with format in String type Variable  Add 1 in month because month index is start with 0
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;

                timeSlot.setText(Date);


                       if (currYear != year)
                       {
                            validDate= year>currYear;
                       }
                       else  if (currMonth != month) {
                             validDate= month >currMonth;
                         }
                         else
                         validDate= dayOfMonth>currDay;


            }
        });

        requestBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(validDate)
                {

                }
                else if(!timeSlot1.isChecked() && !timeSlot2.isChecked() && !timeSlot3.isChecked() && !timeSlot4.isChecked())
                {

                }
                else
                {

                }

            }
        });




        return root;
    }
}
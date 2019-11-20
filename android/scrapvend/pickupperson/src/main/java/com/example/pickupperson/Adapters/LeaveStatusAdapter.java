package com.example.pickupperson.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.LeaveStatusModel;
import com.example.pickupperson.R;
import com.example.pickupperson.ui.LeaveRequest.LeaveStatus;
import com.example.pickupperson.ui.home.HomeFragment;

import java.util.ArrayList;

public class LeaveStatusAdapter extends ArrayAdapter<LeaveStatusModel> {
    Context context;
    int resourse;
    ArrayList<LeaveStatusModel> historylist;
    public int getCount(){return super.getCount();}
    public LeaveStatusAdapter(@NonNull Context context, int resource, ArrayList<LeaveStatusModel> historylist) {

        super(context,resource,historylist);
        this.context=context;
        this.resourse=resource;
        this.historylist= historylist;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.leavestatuslistview, null);

        TextView time1,time2,time3,time4,date,status;
        final Button cancel;
        final int t1,t2,t3,t4,s;

        time1=v.findViewById(R.id.timeSlot1);
        time2=v.findViewById(R.id.timeSlot2);
        time3=v.findViewById(R.id.timeSlot3);
        time4=v.findViewById(R.id.timeSlot4);
        date= v.findViewById(R.id.textViewDate);
        status=v.findViewById(R.id.textViewStatus);
        cancel=v.findViewById(R.id.Cancelbutton);
        final LeaveStatusModel employee=getItem(position);

        t1=employee.getT1();
        t2=employee.getT2();
        t3=employee.getT3();
        t4=employee.getT4();
        s=employee.getStatus();
        if(t1==0) {
            time1.setVisibility(View.INVISIBLE);
        }
        if(t2==0) {
            time2.setVisibility(View.INVISIBLE);
        }
        if(t3==0) {
            time3.setVisibility(View.INVISIBLE);
        }if(t4==0) {
            time4.setVisibility(View.INVISIBLE);
        }
        if(s==0) {
            status.setText("Pending");

        }
        else if(s==1) {
            status.setText("Accepted");
            cancel.setBackgroundColor(Color.GRAY);
        }
        else {
            status.setText("Canceled");
            cancel.setBackgroundColor(Color.GRAY);
        }


        date.setText(employee.getDate());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s!=0)
                {




                }

            }
        });

        return v;
    }
}


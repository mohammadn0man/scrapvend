package com.example.scrapvend.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;
import com.example.scrapvend.ui.home.OnLeave;

import java.util.ArrayList;

public class LeaverequestAdapter extends ArrayAdapter<PickupPersonModel> {
    Context context;
    int resourse;
    ArrayList<PickupPersonModel> pickupperson;
    public LeaverequestAdapter(@NonNull Context context, int resource, ArrayList<PickupPersonModel> pickupperson) {

        super(context,resource,pickupperson);
        this.context=context;
        this.resourse=resource;
        this.pickupperson= pickupperson;
    }
    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.leave_request_list_items, null);

        final CheckBox checkBox1,checkBox2,checkBox3,checkBox4 ;
        final Button accept,reject;
        int t1;
        int t2;
        int t3;
        int t4;


        checkBox1=(CheckBox)v.findViewById(R.id.radioButton);
        checkBox2=(CheckBox)v.findViewById(R.id.radioButton2);
        checkBox3=(CheckBox)v.findViewById(R.id.radioButton3);
        checkBox4=(CheckBox)v.findViewById(R.id.radioButton4);
        accept=(Button) v.findViewById(R.id.accept);
        reject=(Button)v.findViewById(R.id.reject);
        final PickupPersonModel pickuppersonModel = getItem(position);

        t1=pickuppersonModel.getT1();
        t2=pickuppersonModel.getT2();
        t3=pickuppersonModel.getT3();
        t4=pickuppersonModel.getT4();
        if(t1==1) {
            checkBox1.setVisibility(View.VISIBLE);
        }
        if(t2==1) {
            checkBox2.setVisibility(View.VISIBLE);
        }
        if(t3==1) {
            checkBox3.setVisibility(View.VISIBLE);
        }if(t4==1) {
            checkBox4.setVisibility(View.VISIBLE);
        }
        final String pname;
        final TextView name = v.findViewById(R.id.name);
        name.setText(pickuppersonModel.getName());
        pname=pickuppersonModel.getName();



        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, pname +reject.getText().toString(), Toast.LENGTH_SHORT).show();
                int c1=0,c2=0,c3=0,c4=0;
                new OnLeave.Leavegrant(pickuppersonModel.getId(),c1,c2,c3,c4,3).execute();


            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, pname+accept.getText().toString(), Toast.LENGTH_SHORT).show();
                int c1=0,c2=0,c3=0,c4=0;

                if(checkBox1.isChecked())
                {
                    c1 =1;
                }
                if(checkBox2.isChecked())
                {
                    c2 =1;
                }
                if(checkBox3.isChecked())
                {
                    c3 =1;
                }
                if(checkBox4.isChecked())
                {
                    c4 =1;
                }
                new OnLeave.Leavegrant(pickuppersonModel.getId(),c1,c2,c3,c4,1).execute();
            }
        });
        return v;
    }
    //REFRESH
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }
}

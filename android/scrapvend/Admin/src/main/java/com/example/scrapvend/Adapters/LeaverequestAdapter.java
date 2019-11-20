package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

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

        CheckBox checkBox1,checkBox2,checkBox3,checkBox4 ;
        checkBox1=(CheckBox)v.findViewById(R.id.radioButton);
        checkBox2=(CheckBox)v.findViewById(R.id.radioButton2);
        checkBox3=(CheckBox)v.findViewById(R.id.radioButton3);
        checkBox4=(CheckBox)v.findViewById(R.id.radioButton4);
        int t1,t2,t3,t4;
        PickupPersonModel pickuppersonModel = getItem(position);

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

        TextView name = v.findViewById(R.id.name);
        name.setText(pickuppersonModel.getName());
        return v;
    }
}

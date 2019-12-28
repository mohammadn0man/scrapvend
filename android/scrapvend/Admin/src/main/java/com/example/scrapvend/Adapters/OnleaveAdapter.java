package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

public class OnleaveAdapter extends ArrayAdapter<PickupPersonModel> {
    Context context;
    int resourse;
    ArrayList<PickupPersonModel> pickupperson;
    public OnleaveAdapter(@NonNull Context context, int resource, ArrayList<PickupPersonModel> pickupperson) {

        super(context,resource,pickupperson);
        this.context=context;
        this.resourse=resource;
        this.pickupperson= pickupperson;
    }
    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.onleave_list_items, null);
        TextView name,time1,time2,time3,time4;
        int t1,t2,t3,t4;
        PickupPersonModel pickuppersonModel = getItem(position);
        name =(TextView) v.findViewById(R.id.pickupPersonName);
        time1 =(TextView) v.findViewById(R.id.textView20);
        time2 =(TextView) v.findViewById(R.id.textView21);
        time3 =(TextView) v.findViewById(R.id.textView22);
        time4 =(TextView) v.findViewById(R.id.textView23);
        name.setText(pickuppersonModel.getName());

            t1 = pickuppersonModel.getT1();
            t2 = pickuppersonModel.getT2();
            t3 = pickuppersonModel.getT3();
            t4 = pickuppersonModel.getT4();
            if (t1 == 1) {
                time1.setVisibility(View.VISIBLE);
            }
            if (t2 == 1) {
                time2.setVisibility(View.VISIBLE);
            }
            if (t3 == 1) {
                time3.setVisibility(View.VISIBLE);
            }
            if (t4 == 1) {
                time4.setVisibility(View.VISIBLE);
            }

    return v;
    }
    }

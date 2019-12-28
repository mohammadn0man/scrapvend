package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scrapvend.Models.PickupinfoModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class PickupinfoAdapter extends ArrayAdapter<PickupinfoModel> {

    Context context;
    int resource;
    ArrayList<PickupinfoModel> pickupinfoModelArrayList = new ArrayList<>();

    public PickupinfoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PickupinfoModel> pickupinfoModelArrayList) {
        super(context, resource, pickupinfoModelArrayList);
        this.context = context;
        this.resource = resource;
        this.pickupinfoModelArrayList = pickupinfoModelArrayList;
    }

    public int getCount(){
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.pickupinfo_list_layout, null);
        PickupinfoModel pickupinfoModel =  getItem(position);

        TextView username, scheduled_date_time, location, booked_date_time;

        username = (TextView) view.findViewById(R.id.username);
        scheduled_date_time = (TextView) view.findViewById(R.id.scheduled_date_time);
        location = (TextView) view.findViewById(R.id.location);
        booked_date_time = (TextView) view.findViewById(R.id.booked_date_time);

        username.setText(pickupinfoModel.getUsername());
        location.setText(pickupinfoModel.getLocation());
        scheduled_date_time.setText(pickupinfoModel.getSchuduleDate());
        booked_date_time.setText(pickupinfoModel.getBookedDate());

        return view;
    }

}

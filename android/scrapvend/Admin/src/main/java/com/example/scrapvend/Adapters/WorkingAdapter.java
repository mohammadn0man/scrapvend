package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class WorkingAdapter extends ArrayAdapter<PickupPersonModel> {
    Context context;
    int resourse;
    ArrayList<PickupPersonModel> pickupPerson;

    public WorkingAdapter(@NonNull Context context, int resource, ArrayList<PickupPersonModel> pickupPerson) {
        super(context, resource, pickupPerson);
        this.context = context;
        this.resourse = resource;
        this.pickupPerson = pickupPerson;
    }

    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.working_list_layout, null);

        PickupPersonModel pickupPersonModel = getItem(position);

        TextView personName = (TextView) v.findViewById(R.id.pickup_person_name);
        TextView personAddress = (TextView) v.findViewById(R.id.pickup_person_address);

        personName.setText(pickupPersonModel.getName());
        personAddress.setText(pickupPersonModel.getAddress());

        return v;
    }
}

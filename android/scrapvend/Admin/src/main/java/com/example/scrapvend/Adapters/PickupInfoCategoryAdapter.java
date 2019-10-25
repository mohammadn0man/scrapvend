package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scrapvend.Models.PickupInfoCategoryModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class PickupInfoAdapter extends ArrayAdapter<PickupInfoCategoryModel> {

    Context context;
    int resource;
    ArrayList<PickupInfoCategoryModel> pickupInfoCategoryModelArrarList;

    public PickupInfoAdapter(@NonNull Context context, int resource, ArrayList<PickupInfoCategoryModel> pickupInfoCategoryModelArrarList) {
        super(context, resource, pickupInfoCategoryModelArrarList);
        this.context = context;
        this.resource = resource;
        this.pickupInfoCategoryModelArrarList = pickupInfoCategoryModelArrarList;
    }


    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.pickupinfo_list_layout, null);

        PickupInfoCategoryModel pickupInfoCategoryModel = getItem(position);
        TextView name = v.findViewById(R.id.pickup_catagory);

        name.setText(pickupInfoCategoryModel.getCatagoryName());

        return v;
    }
}

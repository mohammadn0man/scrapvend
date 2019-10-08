package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.scrapvend.Models.Pricing_ItemModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

public class PricingAdapter extends ArrayAdapter<Pricing_ItemModel> {
    Context context;
    int resourse;
    ArrayList<Pricing_ItemModel> pricing;
    public PricingAdapter(@NonNull Context context, int resource, ArrayList<Pricing_ItemModel> pricing) {

        super(context,resource,pricing);
        this.context=context;
        this.resourse=resource;
        this.pricing= pricing;
    }
    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.pricing_list, null);

        Pricing_ItemModel pricingModel = getItem(position);
        TextView name = v.findViewById(R.id.textView12);
        TextView adhar = v.findViewById(R.id.textView13);
        TextView id = v.findViewById(R.id.textView14);

        name.setText(pricingModel.getItemName());
        adhar.setText(pricingModel.getItemRate());
        id.setText(pricingModel.getItemMeasure());

        return v;
    }
}

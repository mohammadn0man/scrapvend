package com.example.pickupperson.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.R;

import java.util.ArrayList;

public class DetailsAdapter extends ArrayAdapter<Details> {

    ArrayList<Details> emplist;
    Context context;
    int textViewResourceId;
    @NonNull
    @Override
    public int getCount() {return super.getCount();}
    public DetailsAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Details> emplist) {
        super(context, 0, emplist);
        this.emplist =emplist;
        this.context=context;
        this.textViewResourceId=textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.homelist_layout, null);

        Details employee=getItem(position);
        TextView textViewName = v.findViewById(R.id.textViewName);
        TextView textViewAddress = v.findViewById(R.id.textViewAddress);
        TextView textViewContact = v.findViewById(R.id.textViewContact);
        TextView textViewDate = v.findViewById(R.id.textViewDate);
        textViewName.setText(employee.getName());
        textViewAddress.setText(employee.getAddress());
        textViewDate.setText(employee.getDate());
        textViewContact.setText(employee.getContact());
        return v;
    }

}

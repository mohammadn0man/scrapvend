package com.example.pickupperson.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.HistoryDetails;
import com.example.pickupperson.R;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<HistoryDetails> {

    ArrayList<HistoryDetails> historylist;
    Context context;
    int textViewResourceId;
    @NonNull
    @Override
    public int getCount() {return super.getCount();}
    public HistoryAdapter(@NonNull Context context, int textViewResourceId, ArrayList<HistoryDetails> historylist) {
        super(context, 0, historylist);
        this.historylist = historylist;
        this.context=context;
        this.textViewResourceId=textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.historylist, null);

        HistoryDetails history=getItem(position);
        TextView textViewId = v.findViewById(R.id.textViewId);
        TextView textViewAddress = v.findViewById(R.id.textViewAddress);
        TextView textViewTime = v.findViewById(R.id.textViewTime);
        TextView textViewDate = v.findViewById(R.id.textViewDate);
        textViewId.setText(String.valueOf(history.getBookingid()));
        textViewAddress.setText(history.getAddress());
        textViewDate.setText(history.getDate());
        textViewTime.setText(history.getTime());
        return v;
    }

}

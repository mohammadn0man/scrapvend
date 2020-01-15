package com.example.login.userUi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.login.Models.HistoryDetails;
import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HistoryUserAdapter extends ArrayAdapter<HistoryDetails> {

    Context context;
    int resource;
    ArrayList<HistoryDetails> historyDetailsArrayList = new ArrayList<>();

    @Override
    public int getCount(){return super.getCount();}
    public HistoryUserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HistoryDetails> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.historyDetailsArrayList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.history_user_list_layout, null);

        TextView date = (TextView) view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView amount = (TextView) view.findViewById(R.id.amount);
        HistoryDetails historyDetails = getItem(position);
        date.setText(historyDetails.getBooking_date());
        time.setText(historyDetails.getBooking_time());
        amount.setText(historyDetails.getAmount());


        return view;
    }
}

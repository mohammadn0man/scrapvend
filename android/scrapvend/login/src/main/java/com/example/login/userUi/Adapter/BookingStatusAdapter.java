package com.example.login.userUi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.login.R;
import com.example.login.userUi.Models.BookingStatusModel;

import java.util.ArrayList;

public class BookingStatusAdapter extends ArrayAdapter<BookingStatusModel> {
    Context context;
    int resourse;
    ArrayList<BookingStatusModel> status;

    public BookingStatusAdapter(@NonNull Context context, int resource, ArrayList<BookingStatusModel> status) {

        super(context, resource, status);
        this.context = context;
        this.resourse = resource;
        this.status = status;
    }

    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.booking_list_item, null);

        BookingStatusModel bookingStatusModel = getItem(position);
        TextView id = (TextView) v.findViewById(R.id.bid);
        TextView status = (TextView) v.findViewById(R.id.bstatus);
        TextView bdate = (TextView)v.findViewById(R.id.bdate);

        Log.d("adapter","booking id"+bookingStatusModel.getBid());
        id.setText(Integer.toString(bookingStatusModel.getBid()));
        status.setText(bookingStatusModel.getStatus());
        bdate.setText(bookingStatusModel.getBooking_date_time());



        return v;
    }
}


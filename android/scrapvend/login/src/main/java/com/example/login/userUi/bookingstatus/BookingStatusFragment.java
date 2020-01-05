package com.example.login.userUi.bookingstatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.login.R;

public class BookingStatusFragment extends Fragment {

    private BookingStatusViewModel bookingStatusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingStatusViewModel =
                ViewModelProviders.of(this).get(BookingStatusViewModel.class);
        View root = inflater.inflate(R.xml.fragment_booking_status, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        bookingStatusViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
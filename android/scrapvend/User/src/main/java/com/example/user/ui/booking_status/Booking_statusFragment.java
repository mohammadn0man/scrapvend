package com.example.user.ui.booking_status;

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

import com.example.user.R;

public class Booking_statusFragment extends Fragment {

    private Booking_statusViewModel bookingstatusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingstatusViewModel =
                ViewModelProviders.of(this).get(Booking_statusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking_status, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        bookingstatusViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
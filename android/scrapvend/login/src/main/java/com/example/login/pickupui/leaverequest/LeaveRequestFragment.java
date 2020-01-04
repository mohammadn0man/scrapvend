package com.example.login.pickupui.leaverequest;

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

public class LeaveRequestFragment extends Fragment {

    private LeaveRequestViewModel leaveRequestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leaveRequestViewModel =
                ViewModelProviders.of(this).get(LeaveRequestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pickup_leaverequest, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        leaveRequestViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
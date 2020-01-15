package com.example.login.pickupui.pickupdetails;

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

public class PickupDetailsFragment extends Fragment {

    private PickupPersonViewModel pickupPersonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pickupPersonViewModel =
                ViewModelProviders.of(this).get(PickupPersonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pickup_details, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        pickupPersonViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
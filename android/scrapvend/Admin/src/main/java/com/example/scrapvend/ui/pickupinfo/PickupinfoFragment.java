package com.example.scrapvend.ui.pickupinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.scrapvend.R;

public class PickupinfoFragment extends Fragment {

    private PickupinfoViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = ViewModelProviders.of(this).get(PickupinfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pickupinfo, container, false);




        return root;
    }
}
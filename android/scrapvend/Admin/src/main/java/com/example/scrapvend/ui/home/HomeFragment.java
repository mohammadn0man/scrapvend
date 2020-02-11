package com.example.scrapvend.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.scrapvend.R;
import com.example.scrapvend.ui.pickupinfo.PickupinfoList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button working,leave,unschedule,schedule,payment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        working = (Button) root.findViewById(R.id.working);
        leave = (Button) root.findViewById(R.id.onleave);
        unschedule = (Button) root.findViewById(R.id.unschedule_pickups);
        schedule = (Button) root.findViewById(R.id.schedule_pickups);
        payment = (Button) root.findViewById(R.id.payment);

        working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Working.class);
                startActivity(intent);


            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PickupinfoList.class);
                intent.putExtra("GET_PICKUPINFO_FLAG", "Pickup Person Assigned");
                startActivity(intent);
            }
        });

        unschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PickupinfoList.class);
                intent.putExtra("GET_PICKUPINFO_FLAG", "Pending Pickup");
                Log.e("GET_PICKUPINFO_FLAG", "Pending Pickup");
                startActivity(intent);
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OnLeave.class);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Payment.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
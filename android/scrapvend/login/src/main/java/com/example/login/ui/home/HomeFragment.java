package com.example.login.ui.home;

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
import androidx.viewpager.widget.ViewPager;

import com.example.login.MainActivity;
import com.example.login.R;
import com.example.login.ui.Adapter.HomePageImageAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ViewPager viewPager;
    int images[] = {R.drawable.image1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
     HomePageImageAdapter homePageImageAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);

        homePageImageAdapter = new HomePageImageAdapter(getActivity(), images);
        viewPager.setAdapter(homePageImageAdapter);
        return root;
    }
}
package com.example.login.userUi.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.example.login.userUi.Adapter.HomePageImageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView textRequest,textList,textBooking;

    ViewPager viewPager;
    LinearLayout itemList;
    Timer timer;
    int images[] = {R.drawable.image1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    HomePageImageAdapter homePageImageAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.xml.fragment_home_user, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        textRequest=root.findViewById(R.id.requestPickup);
        textList=root.findViewById(R.id.itemList);
        itemList=root.findViewById(R.id.item_list_linear_layout);
        textBooking=root.findViewById(R.id.bookingStatus);


        homePageImageAdapter = new HomePageImageAdapter(getActivity(), images);
        viewPager.setAdapter(homePageImageAdapter);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%images.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

        itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), ItemListGirdView.class);
                startActivity(intent);

            }
        });

        return root;
    }


    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
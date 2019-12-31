package com.example.login.userUi.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.example.login.userUi.Adapter.HomePageImageAdapter;
import com.example.login.userUi.Adapter.SlidingImage_Adapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.image4,R.drawable.image5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    //  CirclePageIndicator indicator;


    ImageView img1,img2,img3;
    TextView textRequest,textList,textBooking;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mPager = (ViewPager) root.findViewById(R.id.pager);
        //   indicator = (CirclePageIndicator)root.findViewById(R.id.indicator);

        textRequest=root.findViewById(R.id.requestPickup);
        textList=root.findViewById(R.id.itemList);
        textBooking=root.findViewById(R.id.bookingStatus);
        img1=root.findViewById(R.id.imageView1);
        img2=root.findViewById(R.id.imageView2);
        img3=root.findViewById(R.id.imageView3);
        init();

        img1.setImageResource(R.drawable.image_1);
        img2.setImageResource(R.drawable.image2);
        img3.setImageResource(R.drawable.image3);

        textRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), RequestPickup.class);
                startActivity(intent);

            }
        });
        return root;
    }
    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        //mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(getContext(),ImagesArray));


        //CirclePageIndicator indicator = (CirclePageIndicator)
        //      findViewById(R.id.indicator);

        //     indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //    indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
   /*     indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
         */
    }

}

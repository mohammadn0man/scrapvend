package com.example.scrapvend.ui.pickupinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.scrapvend.Adapters.PickupInfoCategoryAdapter;
import com.example.scrapvend.Models.PickupInfoCategoryModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

public class PickupinfoFragment extends Fragment {

    private final String TAG = "MyPickupinfo";
//    private PickupinfoViewModel slideshowViewModel;
    private ListView listView;
    private String pickupinfoCategory[];
    private PickupInfoCategoryModel pickupInfoCategoryModel;
    private PickupInfoCategoryAdapter pickupInfoCategoryAdapter;
    private ArrayList<PickupInfoCategoryModel> pickupInfoCategoryModelArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel = ViewModelProviders.of(this).get(PickupinfoViewModel.class);

        View root = inflater.inflate(R.layout.fragment_pickupinfo, container, false);


        listView = (ListView) root.findViewById(R.id.list_view_pickup_catagory);

        pickupinfoCategory = getResources().getStringArray(R.array.pickupinfo_category_name);

        for (String i : pickupinfoCategory){
            Log.d(TAG, "inserting " + i);
            pickupInfoCategoryModel = new PickupInfoCategoryModel(i);
            pickupInfoCategoryModelArrayList.add(pickupInfoCategoryModel);
        }

        pickupInfoCategoryAdapter = new PickupInfoCategoryAdapter(getContext(), R.layout.pickupinfo_list_layout, pickupInfoCategoryModelArrayList);
        listView.setAdapter(pickupInfoCategoryAdapter);


        return root;
    }
}
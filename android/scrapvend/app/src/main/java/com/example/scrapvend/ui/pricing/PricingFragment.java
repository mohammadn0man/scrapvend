package com.example.scrapvend.ui.pricing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.scrapvend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PricingFragment extends Fragment {

    private PricingViewModel pricingViewModel;
    private FloatingActionButton floatingActionButtonAddItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pricingViewModel =
                ViewModelProviders.of(this).get(PricingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pricing, container, false);

        floatingActionButtonAddItem = (FloatingActionButton) root.findViewById(R.id.floatingActionButtonAddItem);

        floatingActionButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItemFragment();
            }
        });

//        final TextView textView = root.findViewById(R.id.text_gallery);
//        pricingViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    public void openAddItemFragment(){
        Intent intent = new Intent(getActivity(),AddItem.class);
        startActivity(intent);
    }

}
package com.example.scrapvend.ui.pricing;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.scrapvend.Adapters.PricingAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PricingFragment extends Fragment {

    public View rootView;
    ListView listview;
    TextView t1, t2, t3;
    ImageView img1;
    PricingAdapter pricingAdapter;
    PricingItemModel pricingItemModel;
    ArrayList<PricingItemModel> pricingItemModelsArrayList = new ArrayList<>();
    Context context;
    private static final String TAG = "MyActivity";
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

        listview = (ListView) root.findViewById(R.id.list_view01);
        t1 = (TextView) root.findViewById(R.id.textView12);
        t2 = (TextView) root.findViewById(R.id.textView13);
        t3 = (TextView) root.findViewById(R.id.textView14);
        img1 = (ImageView)root.findViewById(R.id.imageView3);

        new task().execute();
        Log.d(TAG, "back to oncreate again");
        context = this.getContext();
        return root;
    }

    public void openAddItemFragment(){
        Intent intent = new Intent(getActivity(),AddItem.class);
        startActivity(intent);
    }

    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM `item_details`;");

                while (results.next()) {
//                    Log.d(TAG, results.getString(2)+results.getString(3)+results.getBlob(5));
                    pricingItemModel = new PricingItemModel(results.getString(2),results.getString(3), results.getString(4),results.getBlob(5));
                    pricingItemModelsArrayList.add(pricingItemModel);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            pricingAdapter = new PricingAdapter(context, R.layout.pricing_list, pricingItemModelsArrayList);
            listview.setAdapter(pricingAdapter);

            super.onPostExecute(aVoid);
        }
    }



}
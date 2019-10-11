package com.example.scrapvend.ui.pickupperson;

import android.content.Context;
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

import com.example.scrapvend.Adapters.PickuppersonAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PickuppersonFragment extends Fragment {
    public View rootView;
    ListView listview;
    TextView t1, t2, t3;
    ImageView img;
    PickuppersonAdapter padapter;
    PickupPersonModel pmodel;
    ArrayList<PickupPersonModel> arr = new ArrayList<>();
    private PickuppersonViewModel toolsViewModel;
    Context context;
    private static final String TAG = "MyActivity";

    public View  onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(PickuppersonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pickupperson, container, false);
        listview = (ListView) root.findViewById(R.id.list_view);
        t1 = (TextView) root.findViewById(R.id.textView9);
        t2 = (TextView) root.findViewById(R.id.textView10);
        t3 = (TextView) root.findViewById(R.id.textView11);
        img = (ImageView) root.findViewById(R.id.imageView2);

        new task().execute();
        Log.d(TAG, "back to oncreate again");
        context = this.getContext();
        return root;
    }

    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM `pickup_person_details`;");

                while (results.next()) {
                    Log.d(TAG, results.getString(1)+results.getString(2));
                    pmodel = new PickupPersonModel(results.getString(1),results.getString(2), results.getString(3));
                    arr.add(pmodel);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            padapter = new PickuppersonAdapter(context, R.layout.add_item_pickupperson, arr);
            listview.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}

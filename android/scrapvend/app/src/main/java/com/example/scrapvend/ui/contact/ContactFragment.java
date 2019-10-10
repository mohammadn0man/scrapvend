package com.example.scrapvend.ui.contact;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View rootView;

    private TextView textView1, textView2, textView3, textView4;

    private static final String TAG = "MyActivity";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "inside shareFragment.java");

        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        //        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        rootView = inflater.inflate(R.layout.fragment_contact, container,
                false);

        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        textView2 = (TextView) rootView.findViewById(R.id.textView2);
        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        textView4 = (TextView) rootView.findViewById(R.id.textView4);

        Button button = (Button) rootView.findViewById(R.id.button);

        new MyTask().execute();

        Log.d(TAG, "before onClickListener");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "inside onClickListener");
                new MyTask().execute();
            }
        });
        return rootView;
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private String text1 = "", text2 = "", text3 = "", text4 = "";

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();

                Log.d(TAG, "Statement created");

                ResultSet results = statement.executeQuery("SELECT * FROM contact_us");

                Log.d(TAG, "query executed");

                results.next();

                // Get the data from the current row using the column index - column data are in the VARCHAR format

                text1 = results.getString("Author");
                text2 = results.getString(2);
                text3 = results.getString(3);
                text4 = results.getString(4);

                Log.d(TAG, text1);

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Log.d(TAG, "onPostExecute");
            textView1.setText(text1);
            textView2.setText(text2);
            textView3.setText(text3);
            textView4.setText(text4);

            super.onPostExecute(aVoid);
        }
    }
}
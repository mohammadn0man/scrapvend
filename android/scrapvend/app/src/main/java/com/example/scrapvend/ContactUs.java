package com.example.scrapvend;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scrapvend.DatabaseConnectivity.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    private static final String TAG = "MyActivityContact";

    private TextView textView1, textView2, textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact);

        Log.d(TAG, "inside contactFragment");

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "inside onClickListener");
                new MyTask().execute();

            }
        });

    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private String text1="", text2="", text3="", text4="";

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Connect connection = new Connect();

                Connection conn = connection.getMySqlConnection();

                Statement statement = conn.createStatement();

                final ResultSet results = statement.executeQuery("SELECT * FROM contact_us");

                results.next();

                // Get the data from the current row using the column index - column data are in the VARCHAR format

                text1 = results.getString(1);
                text2 = results.getString(2);
                text3 = results.getString(3);
                text4 = results.getString(4);

                System.out.println(text1);

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            textView1.setText(text1);
            textView2.setText(text2);
            textView3.setText(text3);
            textView4.setText(text4);

            super.onPostExecute(aVoid);
        }
    }
}



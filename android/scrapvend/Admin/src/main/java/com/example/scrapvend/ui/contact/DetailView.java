package com.example.scrapvend.ui.contact;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailView extends AppCompatActivity {

    private final String TAG = "DetailView";
    private TextView name, email, contact_no, subject, message;
    Button contactButton;
    int getId;
    String getemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_item);

        Log.d(TAG, "Inside addItem");
        name = (TextView) findViewById(R.id.author_name);
        email = (TextView) findViewById(R.id.email);
        subject = (TextView) findViewById(R.id.subject);
        contact_no = (TextView) findViewById(R.id.contact_no);
        message = (TextView) findViewById(R.id.message);
        message.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        getId = bundle.getInt("GETId");

        Log.d(TAG, "id = " + getId);

        new InsertDetails().execute();
        contactButton = (Button) findViewById(R.id.reply);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContactUs=new Intent(Intent.ACTION_SEND);
                String[] recipients={getemail};
                intentContactUs.putExtra(Intent.EXTRA_EMAIL, recipients);
                intentContactUs.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intentContactUs.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intentContactUs.setType("text/html");
                intentContactUs.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intentContactUs, "Send mail"));
            }
        });


    }

    private class InsertDetails extends AsyncTask<Void, Void, Void> {


        @SuppressLint("WrongThread")
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            Connection conn=null;
            try {
                MySqlConnector connection = new MySqlConnector();
                conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();

                String query = "SELECT * from `contact_us` WHERE contact_id = " + getId + " ";

                Log.d(TAG, "query created : " + query);

                ResultSet rs = statement.executeQuery(query);

                rs.next();

                name.setText(rs.getString(1));
                email.setText(rs.getString(2));
                getemail=rs.getString(2);
                contact_no.setText(rs.getString(3));
                subject.setText(rs.getString(4));
                message.setText(rs.getString(5));

                Log.d(TAG, "query executed");




            } catch (
                    SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //finally block used to close resources
                Log.d(TAG, "inside finally");

            }

            return null;

        }
        protected void onPostExecute(Void aVoid)
        {
            Log.d(TAG, "inside onpostexecute");
            super.onPostExecute(aVoid);
        }
    }
}
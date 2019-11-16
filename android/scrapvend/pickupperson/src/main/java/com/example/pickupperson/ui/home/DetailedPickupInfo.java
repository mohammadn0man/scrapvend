package com.example.pickupperson.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.R;
import com.example.pickupperson.ui.MySQLConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailedPickupInfo extends Activity implements AdapterView.OnItemSelectedListener
{   TextView textName;
    TextView textAddress;
    TextView textContact;
    TextView textbookingid;
    TextView textDate,textPrice;
    EditText edittext;
    Spinner spinner;
    Button editbutton,updatebutton;
    private final String TAG = "MyDBpage2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_pickup_list);
        textName =findViewById(R.id.textName);
        textAddress=findViewById(R.id.textAddress);
        textContact=findViewById(R.id.textContact);
        textbookingid=findViewById(R.id.textbookingid);
        textDate=findViewById(R.id.textDate);
        edittext=findViewById(R.id.editpickupDate);
        spinner=findViewById((R.id.spinner));
        editbutton=findViewById(R.id.buttonEdit);

        editbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(DetailedPickupInfo.this,ItemQuantity.class);
                Log.d(TAG, "intent sent");
                startActivity(i);

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pickup_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // Receiving value into activity using intent.

        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        textName.setText(TempHolder);
        // Setting up received value into EditText.
        new task().execute();
        //context = this.getContext();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String str = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class task extends AsyncTask<Void, Void, Void> {

        ResultSet results;
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySQLConnector connection = new MySQLConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();

                String value=getIntent().getStringExtra("ListViewClickedValue");
               // textPrice.setText(value);
//                String pending="Pending";
                String query = "SELECT"+" user_details.User_name,user_details.Address ,booking_details.Booking_id,booking_details.Scheduled_pickup_date_time,booking_details.Pickup_date_time"+
                        " FROM"+"(user_details"+ " INNER JOIN "+ "booking_details"+" ON "+ "user_details.User_id = booking_details.User_id)"+" WHERE " +"(booking_details.Pickup_status =\'Pending\' AND " +"user_details.Username= \'"+value+"\')" ;


                Log.d(TAG, query);

                results = statement.executeQuery(query);
                Log.d(TAG, "query executed");
                results.next();
                Log.d(TAG, results.getString(1) + results.getString(2));

                //problem


                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            try {
                textName.setText(results.getString(1));
                textAddress.setText(results.getString(2));
                textbookingid.setText(results.getString(3));
                textDate.setText(results.getString(4));
                edittext.setText(results.getString(5));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Log.d(TAG,"values inserted");


            super.onPostExecute(aVoid);
        }
    }
}


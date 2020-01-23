package com.example.login.pickupui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Adapters.ItemQuantityAdapter;
import com.example.login.R;
import com.example.login.DatabaseConnection.MySqlConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailedPickupInfo extends Activity implements AdapterView.OnItemSelectedListener
{   TextView textName;
    TextView textAddress;
    String ad;
    TextView textContact;
    TextView textbookingid;
    TextView textDate,textTime,textPrice;
    EditText editDate,editTime;
    String bookingId;
    Spinner spinner;
    Button editbutton,updateButton;
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
        textTime=findViewById(R.id.textTime);
        editDate=findViewById(R.id.editPickupDate);
        editTime=findViewById(R.id.editPickupTime);
        spinner=findViewById((R.id.spinner));
        editbutton=findViewById(R.id.buttonEdit);
        updateButton=findViewById(R.id.buttonUpdate);
        textPrice=findViewById(R.id.textPrice);

        editbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(DetailedPickupInfo.this,ItemQuantity.class);
                Log.d(TAG, "intent sent");
               // startActivity(i);
                startActivityForResult(i, 2);// Activity is started with requestCode 2
            }


        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(DetailedPickupInfo.this,AmountVerification.class);
                 startActivity(i);

            }
        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pickup_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // Receiving value into activity using intent.

        String TempHolder = getIntent().getStringExtra("ListViewClickedValue");
        bookingId = getIntent().getStringExtra("id");
        textName.setText(TempHolder);
        // Setting up received value into EditText.
        new task().execute();
        //context = this.getContext();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("TotalAmount");
            Log.d(TAG, message);
            textPrice.setText(message);



        }
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

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();

//                String value=getIntent().getStringExtra("ListViewClickedValue");
               // textPrice.setText(value);
//                String pending="Pending";

//                String query = "SELECT"+" user_details.User_name,user_details.Address ,booking_details.Booking_id,booking_details.Scheduled_pickup_date_time,booking_details.Pickup_date_time"+
//                        " FROM"+"(user_details"+ " INNER JOIN "+ "booking_details"+" ON "+ "user_details.User_id = booking_details.User_id)"+" WHERE " +"(booking_details.Pickup_status =\'Pending\' AND " +"user_details.Username= \'"+value+"\')" ;

                String query = "SELECT user_details.Name, address.House_no, address.Line_1, address.City, address.State, address.Zip_code,\n" +
                        "       booking_details.Booking_id, booking_details.Scheduled_pickup_date, Scheduled_time_slot, booking_details.Pickup_date_time,\n" +
                        "       user_details.Username\n" +
                        "From  user_details\n" +
                        "    INNER JOIN booking_details on user_details.User_id = booking_details.User_id\n" +
                        "    INNER JOIN address on booking_details.Address_id = address.Address_id\n" +
                        "WHERE booking_details.Pickup_status = 'Pickup Person Assigned'\n" +
                        "  AND booking_details.Booking_id = "+ bookingId + " ;";

                Log.d(TAG, query);

                ResultSet results = statement.executeQuery(query);
                Log.d(TAG, "query executed");
                Log.d(TAG, "results.next()");

                results.next();
                Log.d(TAG, "after result.next()");

                Log.d(TAG, results.getString(1) + " " + results.getString(2) + " "+results.getString(3)+" "+results.getString(4)+" "+results.getString(5));

                Log.d(TAG, "problem");
                String slot[] = slot = getResources().getStringArray(R.array.slots);
                Log.d(TAG, results.getString(1));
                String scheduledDate=results.getString("Scheduled_pickup_date");
                String scheduledTime= slot[Integer.parseInt(results.getString("Scheduled_time_slot")) - 1] ;
                String pickupDate=results.getString("Pickup_date_time").substring(0,11);
                String pickupTime=results.getString("Pickup_date_time").substring(12);
                textName.setText(results.getString("Name"));
                ad = results.getString("House_no") + ", " + results.getString("Line_1") + "\n " + results.getString("City") + ", " + results.getString("State");
                textbookingid.setText(results.getString("Booking_id"));
                textDate.setText(scheduledDate);
                textTime.setText(scheduledTime);
                editDate.setText(pickupDate);
                editTime.setText(pickupTime);

//                String totalAmount = getIntent().getStringExtra("TotalAmount");
//                if(totalAmount==null)
//                     textPrice.setText("00");
//                 else
//                textPrice.setText(totalAmount);


                Log.d(TAG,"values inserted");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            textAddress.setText(ad);
            if (ItemQuantityAdapter.flag){
                for(int i=0; i< ItemQuantityAdapter.itemlist.size(); i++)
                {
                    textPrice.setText(textPrice.getText()+" "+ItemQuantityAdapter.itemlist.get(i).getItemqty()+System.getProperty("line.separator"));
                }
            }

            super.onPostExecute(aVoid);
        }
    }
}


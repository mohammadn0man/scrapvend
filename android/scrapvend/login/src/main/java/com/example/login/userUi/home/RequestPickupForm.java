package com.example.login.userUi.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.MainActivity;
import com.example.login.R;
import com.example.login.userUi.Models.ItemListModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.login.MainActivity.user;
import static com.example.login.userUi.home.RequestPickup.multiSelectionAdapter;


public class RequestPickupForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView itemname,addressv,contactno;
    ListView itemListView;
    EditText quantityEditText, dateEditText;
    Spinner timeSpinner;
    Button bookedpickup;
    String quantity, date, time;
    String  contact_no,userid,addressid,address;
    public static String[] list;
     ArrayList<ItemListModel> mArrayProducts;
    ArrayAdapter<String> listAdapter;
    public static String TAG="BP";
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestpickup_layout);

//      DEFINING VARIABLE
        itemname=(TextView) findViewById(R.id.selecteditem);
        itemListView =(ListView) findViewById(R.id.selectedlist);
        quantityEditText =(EditText)findViewById(R.id.estimated_weight);
        dateEditText =(EditText)findViewById(R.id.pickupdate);
        timeSpinner = findViewById(R.id.pickuptime);
        bookedpickup=findViewById(R.id.bookpickup);
        addressv=(TextView)findViewById(R.id.address);
        contactno=(TextView) findViewById(R.id.contactno);

//      RETRIEVING PICKUP TIME FROM SELECTED SPINNER
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.slots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);
        timeSpinner.setOnItemSelectedListener(this);

        //RETRIEVING LIST DATA FROM SELECTED ITEM LIST
        if(multiSelectionAdapter != null) {

            list= new String[0];
            mArrayProducts = multiSelectionAdapter.getCheckedItems();
            Log.d("form", "inside send button");

            Log.d("form", "Selected Items: " + mArrayProducts.toString());

            Toast.makeText(getApplicationContext(), "Selected Items: " + mArrayProducts.toString(), Toast.LENGTH_LONG).show();
            list = new String[mArrayProducts.size()];
            for (i = 0; i < mArrayProducts.size(); i++) {
                list[i] = mArrayProducts.get(i).toString();

            }

        }
//      INSERT SELECTED ITEM INTO SELECTED LIST
        listAdapter = new ArrayAdapter<String>(this, R.layout.selecteditem, R.id.selecteditem,list );
        itemListView.setAdapter(listAdapter);

        new InsertIntoDatabaseTask().execute();

        bookedpickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new InsertDataIntoDB().execute();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class InsertIntoDatabaseTask extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();

            try {
                Log.d(TAG, "inside request pickup");
                //FETCHING DETAILS


                Log.d(TAG, " ");

                Statement statement = conn.createStatement();
                Log.d(TAG, "");
                ResultSet results = statement.executeQuery("select l.contact_no,a.City,a.House_no,a.Line_1,a.State,a.Zip_code,a.Address_id,a.User_id from login_info l inner join user_details u on\n" +
                        "l.Username = u.Username  inner join address a on\n" +
                        "u.User_id = a.User_id where l.Username= \""+user+"\";");
                results.next();
                Log.d(TAG, "address " + results.getString(4)+results.getString(3)+results.getString(2)+results.getString(5)+results.getString(6));
                Log.d(TAG, "user name = " + user);
                address=results.getString(4)+","+results.getString(3)+","+results.getString(2)+","+results.getString(5)+","+results.getString(6);
                addressv.setText(address);
                contactno.setText(results.getString(3));
//
                addressid = results.getString(7);
                userid = results.getString(8);
                contact_no = results.getString(3);

                Log.d(TAG, "query Execute "+address);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    Log.d(TAG, "user name = " + user);
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;


        }
    }

    //BOOKING PICKUP
    private class InsertDataIntoDB extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            int bookingid = 0;

            try {
                Log.d(TAG, "inside request pickup");
                //FETCHING DETAILS


                Log.d(TAG, " ");

                Statement statement = conn.createStatement();
                Log.d(TAG, "");
                ResultSet result=null;

                Log.d(TAG, "user name = " + user);

                quantity = quantityEditText.getText().toString();
                date = dateEditText.getText().toString();
                time = timeSpinner.getSelectedItem().toString();


                //INSERTING DETAILS INTO CONTACT TABLE
                String query1 = "insert into booking_details (User_id, Address_id, Scheduled_time_slot, Scheduled_pickup_date,Total_quantity) VALUE(?,?,?,?,?); ";
                Log.d(TAG, "query = " + query1);
                PreparedStatement preparedStatement1 = conn.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);

                preparedStatement1.setString(1, userid);
                preparedStatement1.setString(2, addressid);
                preparedStatement1.setString(3, time);
                preparedStatement1.setString(4, date);
                preparedStatement1.setString(5, quantity);
                preparedStatement1.execute();
                Log.d(TAG, "insert query1 executed");
                result = preparedStatement1.getGeneratedKeys();
                if(result != null && result.next()){
                    bookingid=result.getInt(1);
                    Log.d(TAG,"Booking Id: "+result.getInt(1));
                }
                for(int i=0;i<list.length;i++)
                {
                    ResultSet results = statement.executeQuery("select Item_id from item_details where Item_name= \""+list[i]+"\";");
                    results.next();
                    int itemid=results.getInt(1);
                    Log.d(TAG,"Item Id: "+results.getInt(1));
                    String query2 = "insert into booked_item_list (Item_id, Booking_id) VALUE(?,?); ";
                    PreparedStatement preparedStatement2 = conn.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);

                    preparedStatement2.setString(1, String.valueOf(itemid));
                    preparedStatement2.setString(2, String.valueOf(bookingid));
                    preparedStatement2.execute();
                    Log.d(TAG, "insert query2 executed");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    Log.d(TAG, "user name = " + user);
                    conn.close();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;


        }
    }
}


package com.example.login.userUi.home;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import static com.example.login.MainActivity.user;

public class RequestPickup extends Activity implements AdapterView.OnItemSelectedListener {
    EditText editQty;
    TextView editAddress, editContact;
    Spinner spinner;
    Button confirmButton;
    ImageButton imgBtn;
    TextView textDate;
    Calendar c;
    Context context;
    DatePickerDialog datePickerDialog;
    private final String TAG = "sp";
    //public  int year, month, day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestpickup_layout);

        editAddress = findViewById(R.id.address);
        editContact = findViewById(R.id.contact_no);
        //editZip = findViewById(R.id.editZipCode);
        editQty = findViewById(R.id.editQuantity);
        spinner = findViewById(R.id.spinner);
        confirmButton = findViewById(R.id.ConfirmButton);
        imgBtn = findViewById(R.id.imageButton);
        textDate = findViewById(R.id.textDate);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.slots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Toast.makeText(RequestPickup.this, "Request submitted !", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "request button clicked");

                 new InsertIntoDatabaseTask().execute();


            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                int cyear = c.get(Calendar.YEAR);
                int cmonth = c.get(Calendar.MONTH);
                int cday = c.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(RequestPickup.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {

                                textDate.setText(day + "-" + (month + 1) + "-" + year);

                            }
                        }, cyear, cmonth, cday);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());


                datePickerDialog.show();


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
                ResultSet results = statement.executeQuery("select l.contact_no,a.City,a.House_no,a.Line_1,a.State,a.Zip_code from login_info l inner join user_details u on\n" +
                        "l.Username = u.Username  inner join address a on\n" +
                        "u.User_id = a.User_id where l.Username= \""+user+"\";");
                results.next();
                Log.d(TAG, "address " + results.getString(4)+results.getString(3)+results.getString(2)+results.getString(5)+results.getString(6));
                Log.d(TAG, "user name = " + user);
                String address=results.getString(4)+","+results.getString(3)+","+results.getString(2)+","+results.getString(5)+","+results.getString(6);
//                String name, email, contact_no;
//                name = results.getString(1);
//                email = results.getString(2);
//                contact_no = results.getString(3);


                //INSERTING DETAILS INTO CONTACT TABLE
//                String query1 = "INSERT INTO contact_us (Author, Email, Contact_no, Subject, Message)VALUE(?,?,?,?,?); ";
//                Log.d(TAG, "query = " + query1);
//                PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
//
//                preparedStatement1.setString(1, name);
//                preparedStatement1.setString(2, email);
//                preparedStatement1.setString(3, contact_no);
//                preparedStatement1.setString(4,subject);
//                preparedStatement1.setString(5,message);
//                preparedStatement1.execute();
                Log.d(TAG, "query Execute "+address);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    Log.d(TAG, "user name = " + user);
                    conn.close();
                    //Intent intent = new Intent(this, MainActivity.class);
                    //startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;


        }
    }
}

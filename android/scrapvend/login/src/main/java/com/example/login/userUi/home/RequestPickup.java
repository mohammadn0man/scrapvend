package com.example.login.userUi.home;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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

import com.example.login.R;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class RequestPickup extends Activity implements AdapterView.OnItemSelectedListener {
    EditText editAddress, editContact, editZip, editQty;
    Spinner spinner;
    Button confirmButton;
    ImageButton imgBtn;
    TextView textDate;
    Calendar c;
    Context context;
    DatePickerDialog datePickerDialog;
    //public  int year, month, day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestpickup_layout);

        editAddress = findViewById(R.id.editPickupAddress);
        editContact = findViewById(R.id.editContact);
        editZip = findViewById(R.id.editZipCode);
        editQty = findViewById(R.id.editQuantity);
        spinner = findViewById(R.id.spinner);
        confirmButton = findViewById(R.id.ConfirmButton);
        imgBtn=findViewById(R.id.imageButton);
        textDate=findViewById(R.id.textDate);

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

                // new InsertIntoDatabaseTask().execute();


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

}

package com.example.scrapvend.ui.pricing;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateItem  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MyAddItem";
    private EditText itemNameEditText, itemRateEditText;
    Spinner itemSpinner;
    Button SaveEditItem, DeleteItem;
    String GetItemName, GetItemRate, GetItemMeasure, GetItemId;
    PricingItemModel itemModel = new PricingItemModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_item_details);

        itemNameEditText = findViewById(R.id.editText2);
        itemRateEditText = findViewById(R.id.editText3);
        itemSpinner = findViewById(R.id.spinner_measure);
        SaveEditItem = findViewById(R.id.button3);
        DeleteItem = findViewById(R.id.button4);
        itemSpinner = findViewById(R.id.spinner_measure);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.item_measure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(this);

        //FETCHING ID OF THE LIST
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        GetItemName = bundle.getString("GETName");
        GetItemRate = bundle.getString("GETRate");
        GetItemMeasure = bundle.getString("GETMeasure");
        GetItemId = bundle.getString("GETId");

        Log.d(TAG, "Selected Item  =" + GetItemName + GetItemRate);
        itemNameEditText.setText(GetItemName);
        itemRateEditText.setText(GetItemRate);

        SaveEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new UpdateItemData().execute();
            }
        });

        DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DeleteItemData().execute();


            }
        });

    }

    private class UpdateItemData extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();

                String Name, Rate, Measure;
                Rate = itemRateEditText.getText().toString();
                Measure = itemSpinner.getSelectedItem().toString();
                Name = itemNameEditText.getText().toString();
                Log.d(TAG, "data " + Name + Rate);

                String query = "UPDATE `item_details` SET `Item_name`=\"" + Name + "\" ,`Item_rate`=" + Rate + " WHERE Item_id = " + GetItemId + " ";

                Log.d(TAG, "query created : " + query);

                statement.executeUpdate(query);

                Log.d(TAG, "query executed");

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                Log.d(TAG, "inside finally");
                Intent intent = new Intent(getApplicationContext(),PricingFragment.class);
                startActivity(intent);

            }

            return null;

        }
    }

    private class DeleteItemData extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();
                String query = "DELETE FROM `item_details` WHERE Item_id = " + GetItemId + " ";

                Log.d(TAG, "query created : " + query);

                statement.executeUpdate(query);

                Log.d(TAG, "query executed");

                conn.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources

                Log.d(TAG, "inside finally");
                Intent intent = new Intent(getApplicationContext(),PricingFragment.class);
                startActivity(intent);

            }

            return null;

        }


    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

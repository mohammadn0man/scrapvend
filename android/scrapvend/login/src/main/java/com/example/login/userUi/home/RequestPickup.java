package com.example.login.userUi.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.userUi.Adapter.MultiSelectionAdapter;
import com.example.login.userUi.Models.ItemListModel;
import com.example.login.R;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RequestPickup extends AppCompatActivity {
    private final String TAG="rp";
    ItemListModel itemListModel;
    Button nextpage;
    ListView itemlist;
    public static MultiSelectionAdapter multiSelectionAdapter;

    ArrayList<ItemListModel> arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestpickup_list);
        Log.d(TAG,"Inside Request Pickup");
        itemlist =(ListView)findViewById(R.id.pickuprequest);
        nextpage =(Button)findViewById(R.id.nextpage);

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"inside send button");
                //selecteditem = new String();
                Intent intent = new Intent(getApplicationContext(), RequestPickupForm.class);
                startActivity(intent);

            }
        });


        itemlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        new viewitemlistdata().execute();



    }
    private class viewitemlistdata extends AsyncTask<Void,Void,Void> {
        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG,"inside viewitemlistdata");

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Log.d(TAG,"connection Establishing");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from item_details where View_value=1");
                Log.d(TAG,"query executed");
                while (resultSet.next())
                {
                    Blob bp = resultSet.getBlob(5);
                    Bitmap btm;
                    try {
                        int boblength = (int) bp.length();
                        byte[] blobAsByte = bp.getBytes(1,boblength);
                        btm= BitmapFactory.decodeByteArray(blobAsByte,0,blobAsByte.length);
                        Log.d(TAG,"data"+resultSet.getInt(1)+resultSet.getFloat(3)+resultSet.getString(2)+resultSet.getString(4)+btm);
                        itemListModel = new ItemListModel(resultSet.getInt(1),resultSet.getFloat(3),resultSet.getString(2),resultSet.getString(4),btm);
                        Log.d(TAG,"data inserted into itemlistmodel");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    arr.add(itemListModel);

                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){

            Log.d(TAG,"inside onpost execution");
            multiSelectionAdapter = new MultiSelectionAdapter(getApplicationContext(),R.layout.request_pickup_list_item,arr);
            itemlist.setAdapter(multiSelectionAdapter);
            super.onPostExecute(aVoid);
        }
    }



    }

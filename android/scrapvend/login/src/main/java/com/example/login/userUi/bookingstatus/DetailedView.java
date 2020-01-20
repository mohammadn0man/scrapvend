package com.example.login.userUi.bookingstatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.MainActivity;
import com.example.login.R;
import com.example.login.User;
import com.example.login.userUi.Models.ItemListModel;
import com.example.login.userUi.home.HomeFragment;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DetailedView extends AppCompatActivity {
    TextView bookingid,bookingstatus,scheduledate,scheduletime,bookingdt,pnotassign,pname,pcontact,ptime;
    ImageView pimage;
    LinearLayout pdetails;
    ListView itemlist;
    ArrayAdapter<String> listAdapter;
    Button okbutton;
    public static final String TAG = "status detail view";
    String bid;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.booking_status_detail_view);
        bookingid=findViewById(R.id.bid2);
        bookingstatus=findViewById(R.id.bs);
        scheduledate=findViewById(R.id.sd);
        scheduletime=findViewById(R.id.st);
        bookingdt=findViewById(R.id.bdt);
        itemlist=findViewById(R.id.ilist);
        pnotassign=findViewById(R.id.p_not_assign);
        pdetails=(LinearLayout) findViewById(R.id.pickup_person_detail);
        pname=findViewById(R.id.pname);
        pcontact=findViewById(R.id.pcontact);
        pimage=findViewById(R.id.pimage);
        ptime=findViewById(R.id.ptime);
        okbutton=(Button) findViewById(R.id.okbutton);

        //FETCHING DETAILS
        Bundle bundle;
        bundle = getIntent().getExtras();
        bid=bundle.getString("GETID");
        bookingid.setText(bundle.getString("GETID"));
        bookingstatus.setText(bundle.getString("GETStatus"));
        scheduletime.setText(bundle.getString("GETSTime"));
        scheduledate.setText(bundle.getString("GETSDate"));
        bookingdt.setText(bundle.getString("GETDateTime"));


        //RETRIEVING LIST
            new viewlistdata().execute();

        if(bundle.getString("GETStatus").equals("Pickup Person Assigned"))
        {
            new viewitemlistdata().execute();
            pnotassign.setVisibility(View.INVISIBLE);
            pdetails.setVisibility(View.VISIBLE);
        }
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User.class);
                startActivity(intent);

            }
        });
    }

    private class viewlistdata extends AsyncTask<Void, Void, Void> {

        ArrayList<String> ilist = new ArrayList<String>();
        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside viewitemlistdata");

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Log.d(TAG, "connection Establishing");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT item_details.Item_name FROM item_details JOIN booked_item_list ON item_details.Item_id=booked_item_list.Item_id where booked_item_list.Booking_id=" + bid + ";");
                Log.d(TAG, "query executed");

                while (resultSet.next()) {

                    ilist.add(resultSet.getString(1));
                    //Log.d(TAG, "item list "+ilist[i]);


                }

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
        @Override
        protected void onPostExecute(Void aVoid){

            //Log.d(TAG,"inside onpost execution"+ilist[0]);
            listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.selecteditem, R.id.selecteditem,ilist );
            itemlist.setAdapter(listAdapter);

            super.onPostExecute(aVoid);
        }

    }
    private class viewitemlistdata extends AsyncTask<Void,Void,Void> {
        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside viewitemlistdata");

            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Log.d(TAG, "connection Establishing");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT pd.Name,pd.Person_Image,li.contact_no,ba.Assign_slot FROM pickup_person_details pd JOIN  booking_assigned ba ON pd.Pickup_person_id = ba.Pickup_person_id JOIN login_info li  ON li.Username = pd.Username where ba.Booking_id=" + bid + ";");
                Log.d(TAG, "query executed");
                resultSet.next();

                Blob bp = resultSet.getBlob(2);
                Bitmap btm;
                try {
                    int boblength = (int) bp.length();
                    byte[] blobAsByte = bp.getBytes(1, boblength);
                    btm = BitmapFactory.decodeByteArray(blobAsByte, 0, blobAsByte.length);
                    Log.d(TAG, "data" + resultSet.getString(1) + resultSet.getString(3));
                    pname.setText(resultSet.getString(1));
                    pcontact.setText(resultSet.getString(3));
                    int time = resultSet.getInt(4);
                    if (time == 1) {
                        ptime.setText("10:00AM-12:00PM");
                    } else if (time == 2) {
                        ptime.setText("12:00PM-02:00PM");
                    } else if (time == 3) {
                        ptime.setText("02:00PM-04:00PM");
                    } else {
                        ptime.setText("04:00PM-06:00PM");
                    }
                    pimage.setImageBitmap(btm);
                    Log.d(TAG, "data inserted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    }

    }



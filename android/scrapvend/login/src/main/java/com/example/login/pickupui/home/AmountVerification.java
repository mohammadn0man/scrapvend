package com.example.login.pickupui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.Adapters.ItemQuantityAdapter;
import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.MainActivity;
import com.example.login.R;
import com.example.login.login.registration_user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AmountVerification extends AppCompatActivity {
    TextView textView;
    Button sendOtpButton, updateButton;
    EditText editOtp;
    ProgressBar pBar;
    String amount;
    String bookingId,contact_num;
    Context context;
    float total_quantity=0,total_amount=0;
    int randomNumber;
    private final String TAG = "MyDBpage2";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_verification_layout);

        textView=findViewById(R.id.textViewI);
        updateButton=findViewById(R.id.updateButton);
        sendOtpButton=findViewById(R.id.sendOtpButton);
        editOtp=findViewById(R.id.editOtp);
        pBar=findViewById(R.id.progressBar);

        context = this.getApplicationContext();

        bookingId = getIntent().getStringExtra("id");
        contact_num=getIntent().getStringExtra("contact_num");
        amount=getIntent().getStringExtra("amount");

        sendOtpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d(TAG, "otpsend button clicked");
                new OtpTask().execute();

                Toast.makeText(getApplicationContext(), "otp send", Toast.LENGTH_SHORT).show();


            }


        });
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user_otp=editOtp.getText().toString();
                if(user_otp.equals(""))
                {   Log.d(TAG,"inside if");
                    Toast.makeText(getApplicationContext(), "Please enter otp", Toast.LENGTH_SHORT).show();
                }
                else if(randomNumber == Integer.valueOf(user_otp))
                {    pBar.setVisibility(v.VISIBLE);
                    new UpdateTask().execute();
                    Toast.makeText(getApplicationContext(), "Successfully updated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "otp not matched..Please try again!", Toast.LENGTH_SHORT).show();

                }

            }


        });
    }

    class OtpTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                // Construct data

                Log.d(TAG, "INSIDE OTP TRY");
                String apiKey = "apikey=" + "Zc0isGFes+4-emDqtMH1gjl4hqZXiiU0FdcB5nx2vb";
                Random random = new Random();
                randomNumber = random.nextInt(9999);
                String message = "&message=" + "Hey " +"Your total amount is -"+amount+" and  OTP is " + randomNumber + ". Thankyou, Team Scrapvend";
                String sender = "&sender=" + "TXTLCL";
                String numbers = "&numbers=91" + contact_num;

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                Log.d(TAG, "AFTER CONN IN OTP");
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();

//                    return stringBuffer.toString();
            } catch (Exception e) {
                System.out.println("url Error SMS "+e);
//                    return "Error "+e;
                Toast.makeText(getApplicationContext(), "ERROR SMS" + e, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();


            }
            Log.d(TAG, "AFTER CATCH IN OTP");

            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            return null;
        }
    }

    private class UpdateTask extends AsyncTask<Void, Void, Void> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();
            Log.d(TAG, "inside updateTask");

            Connection conn = connection.getMySqlConnection();
            try {
              //  Statement statement = conn.createStatement();
                int b_id=Integer.parseInt(bookingId);
                String query = "delete from booked_item_list where `Booking_id`=" + b_id ;
                Log.d(TAG, query);
                PreparedStatement statement = conn.prepareStatement(query);
                statement.executeUpdate(query);
                Log.d(TAG, "delete query executed");

                for(int i=0; i< ItemQuantityAdapter.itemlist.size(); i++)
                {
                   float itemQty=Float.parseFloat(ItemQuantityAdapter.itemlist.get(i).getItemqty());
                   float itemAmt=Float.parseFloat(ItemQuantityAdapter.itemlist.get(i).getItemRate());
                   int itemId=Integer.parseInt(ItemQuantityAdapter.itemlist.get(i).getItemId());
                    total_quantity+=itemQty;
                    total_amount+=(itemAmt*itemQty);
                    if(itemQty==0.0)
                   {
                       continue;
                   }
                    String query1 = "INSERT INTO `booked_item_list` (Item_id, Booking_id, Item_qty, Item_amount) VALUES (?,?,?,?)";
                    PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
                    Log.e(TAG,itemQty +" "+itemAmt+" "+itemId + " " + b_id);

                    preparedStatement1.setInt(1, itemId);
                    preparedStatement1.setInt(2, b_id);
                    preparedStatement1.setFloat(3,itemQty);
                    preparedStatement1.setFloat(4, itemAmt);

                    Log.d(TAG, "query = " + query1);

                    preparedStatement1.execute();
                    Log.d(TAG, "insert query executed");
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date date = new Date();
                String query2="UPDATE `booking_details` SET `Pickup_status`= "+"\"Pickup Completed\""+" ,`Pickup_date_time`= \""+dtf.format(now)+"\", `Total_quantity`="+total_quantity+" where Booking_id="+b_id+" " ;

                PreparedStatement preparedStatement = conn.prepareStatement(query2);

                Log.d(TAG, "query = " + query2);

                preparedStatement.execute();
                Log.d(TAG, "update query executed");

                String query3 = "INSERT INTO `payment_details` (Payment_amount , Booking_id) VALUES (?,?)";
                PreparedStatement pStatement = conn.prepareStatement(query3);
                pStatement.setFloat(1,total_amount);
                pStatement.setInt(2, b_id);

                Log.d(TAG, "query = " + query3);

                pStatement.execute();
                Log.d(TAG, "update query executed");



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
            pBar.setVisibility(View.GONE);
            Intent i = new Intent(AmountVerification.this, MainActivity.class);
            startActivity(i);
            super.onPostExecute(aVoid);

        }
    }
}
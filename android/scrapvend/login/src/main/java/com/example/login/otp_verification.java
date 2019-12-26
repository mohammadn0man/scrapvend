package com.example.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.main.MySqlConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class otp_verification extends AppCompatActivity {
    private EditText name, number;
    private EditText email,password;
    private EditText editText;
    private Button submit;
    private TextView textView;
    int randomNumber;

    @Override
    protected void onCreate(final Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.otp_verification);
        getSupportActionBar().hide();
        //number = findViewById(R.id.number);
        name = findViewById(R.id.name);
        editText = findViewById(R.id.editText);
        email =findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        textView = findViewById(R.id.mnv);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        new OtpTask().execute();

        Toast.makeText(getApplicationContext(), "OTP SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();


        submit.setOnClickListener(new View.OnClickListener() {
            boolean isSuccess = false;
            @Override
            public void onClick(View v) {
                String OTP = editText.getText().toString();
                if (OTP.equals("")) {
                    Toast.makeText(otp_verification.this, "Please enter the OTP", Toast.LENGTH_SHORT).show();

                } else if (randomNumber == Integer.valueOf(OTP)) {

                    try {

                        MySqlConnector connection = new MySqlConnector();

                        Connection con = connection.getMySqlConnection();


                        if (con == null) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Please check your INTERNET connection",
                                    Toast.LENGTH_SHORT);

                            toast.show();
                        } else {
                            String query1 = "INSERT INTO `login_info`(`Username`, `Role`, `password`, `email`, `contact_no`) VALUES (\"" + name + number + "\", '2',\"" + password + "\",\"" + email + "\",\"" + number + "\")";
                            String query2 = "INSERT INTO `user_details`(`Name`, `Username`) VALUES (\"" + name + "\",\"" + name + number + "\")";
                            Log.d("Reg", "query = " + query1);
                            Statement statement = con.createStatement();
//                    statement.executeUpdate(query1);
//                    statement.executeUpdate(query2);
//
//                    Toast toast = Toast.makeText(getApplicationContext(),
//                            "Register successful",
//                            Toast.LENGTH_SHORT);
//                    toast.show();

                            isSuccess = true;

                        }

                    } catch (SQLException e) {
                        isSuccess = false;
                    }
                    textView.setText("OTP Verified");
                    textView.setTextColor(Color.GREEN);
                    editText.setTextColor(Color.GREEN);
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    textView.setText("X Incorrect OTP");
                    textView.setTextColor(Color.RED);
                    editText.setTextColor(Color.RED);
                }
            }


        });
    }

    class OtpTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                // Construct data
                Log.d("url area conn", "before");
                String apiKey = "apikey=" + "18sUVSjxhSo-cPH0wdHNHsF3Li6ctemNOmPrCktsNX";
                Random random = new Random();
                randomNumber = random.nextInt(9999);
                String message = "&message=" + "Hey " + name + " Your OTP is " + randomNumber + ". Thankyou, Team Scrapvend";
                String sender = "&sender=" + "TXTLCL";
                String numbers = "&numbers=91" + registration_user.number;

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                Log.d("url area", "null");
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

            return null;
        }
    }
}



package com.example.login.pickupui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.login.registration_user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class AmountVerification extends AppCompatActivity {
    TextView textView;
    Button sendOtpButton, updateButton;
    EditText editOtp;
    Context context;
    int randomNumber;
    private final String TAG = "MyDBpage2";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_verification_layout);

        textView=findViewById(R.id.textViewI);
        updateButton=findViewById(R.id.updateButton);
        sendOtpButton=findViewById(R.id.sendOtpButton);
        editOtp=findViewById(R.id.editOtp);

        context = this.getApplicationContext();

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
                String message = "&message=" + "Hey " +" Your OTP is " + randomNumber + ". Thankyou, Team Scrapvend";
                String sender = "&sender=" + "TXTLCL";
                String numbers = "&numbers=91" + "9760215764";

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
}
package com.example.login.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.MainActivity;
import com.example.login.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class otp_verification extends AppCompatActivity {
    private static String TAG ="REG";
    private String name, username;
    private String number;
    private EditText editText;
    private String email,password;
    private Button submit;
    MySqlConnector connection;
    Connection con;
    private TextView textView;
    int randomNumber;

    @Override
    protected void onCreate(final Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.otp_verification);
        //getSupportActionBar().hide();
        //number = findViewById(R.id.number);

        editText=findViewById(R.id.editText);

        name = getIntent().getStringExtra("name");
        number =getIntent().getStringExtra("number");
        email =getIntent().getStringExtra("email");
        password =getIntent().getStringExtra("password");
        username=getIntent().getStringExtra("username");
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

                } else if (randomNumber== Integer.valueOf(OTP)) {
                    new InsertIntoDatabaseTask().execute();
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


                Log.d("mysql", "before" + number);
                String apiKey = "apikey=" + "Nt7ekXfjdK8-fwssKs7i70lNTqJePUcXcn8mEeCJTp";
                Random random = new Random();
                randomNumber = random.nextInt(9999);
                String message = "&message=" + "Hey  Your OTP is " + randomNumber + ". Thankyou, Team Scrapvend";
                String sender = "&sender=" + "TXTLCL";
                String numbers = "&numbers=91" + number;

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                Log.d("mysql", "null");
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

                    //return stringBuffer.toString();
            } catch (Exception e) {
                System.out.println("url Error SMS "+e);
//                    return "Error "+e;
                Toast.makeText(getApplicationContext(), "ERROR SMS" + e, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();


            }

            return null;
        }
    }

    private class InsertIntoDatabaseTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();

            try {

                Log.d(TAG, "Connection established");

                Log.d("Reg", " name = " + name+ " username "+ username+" password"+password);
                String query1;
                query1 = "INSERT INTO `login_info`(`Username`, `Role`, `password`, `email`, `contact_no`) VALUES (\"" + username+ "\", '2',\"" + password + "\",\"" + email + "\",\"" + number + "\")";
                Log.d(TAG, "query = " + query1);
                String query2 = "INSERT INTO `user_details`(`Name`, `Username`) VALUES (\"" + name + "\",\"" + username + "\")";
                Log.d(TAG, "query2 = " + query2);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query1);
                Log.d(TAG, "query1 EXECUTED");
                statement.executeUpdate(query2);
                Log.d(TAG, "query2 executed " );
                Log.d("Reg", "query executed ");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"inside finally");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){


        }

    }
}



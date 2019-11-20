package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.main.MySqlConnector;
import com.mysql.jdbc.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
private Button sign_up,login;
public EditText _email,_password;
public String email,password;
public CheckBox _remember;
public TextView _forgetp;
public Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        _email = (EditText) findViewById(R.id.email);
        _password = (EditText) findViewById(R.id.password);
        _remember = (CheckBox) findViewById(R.id.remember);
        _forgetp = (TextView) findViewById(R.id.forgetp);

        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = _email.getText().toString();
                password = _password.getText().toString();
                new CheckLogin().execute();
            }

        });

        sign_up=(Button) findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openregistration();
            }

            public void openregistration(){
                Intent intent = new Intent(MainActivity.this,registration_user.class);
                startActivity(intent);
            }});

    }
    
         class CheckLogin extends AsyncTask<String, String, String> {
            String z = "";
            Boolean isSuccess = false;

            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                if (isSuccess) {
                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                if (email.trim().equals("") || password.trim().equals("")) {
                    z = "Please enter the username and password";
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:mysql://mohammadnoman.co:3306/mohammad_Scrapvend", "mohammad_user", "nompooaissai123");
                        if (connection == null) {
                            z = "Check your internet Connection";
                        } else {
                            String query = "SELECT * FROM `login_info` WHERE `Username`=\""+email+"\" and `password`=\""+password+"\"";
                            PreparedStatement prest = connection.prepareStatement(query);
                            ResultSet rs = prest.executeQuery(query);
                            if (rs.next()) {
                                z = "Login Successful...";
                                isSuccess = true;
                                Intent in = new Intent(MainActivity.this, homepage.class);
                                startActivity(in);

                            } else {
                                z = "Invalid Credentials...";
                                isSuccess = false;
                            }
                        }

                    } catch (ClassNotFoundException e) {
                        z = e.getMessage();
                    } catch (SQLException e) {
                        z = e.getMessage();
                    } catch (Exception e) {
                        isSuccess = false;
                        z = e.getMessage();
                    }

                }

                return z;
            }
        }


}

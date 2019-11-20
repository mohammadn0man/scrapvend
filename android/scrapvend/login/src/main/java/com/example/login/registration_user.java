package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.main.MySqlConnector;
import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.CachedResultSetMetaData;
import com.mysql.jdbc.ExceptionInterceptor;
import com.mysql.jdbc.Extension;
import com.mysql.jdbc.Field;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.MysqlIO;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.ServerPreparedStatement;
import com.mysql.jdbc.SingleByteCharsetConverter;
import com.mysql.jdbc.StatementImpl;
import com.mysql.jdbc.StatementInterceptorV2;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.Executor;

public class registration_user extends AppCompatActivity {
    private Button register;
    public EditText _name, _email, _password, _re_password, _number;
    public String name;
    public String email;
    public String password;
    public String re_password;
    public static String number;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.registration_user);
        getSupportActionBar().hide();

        _name = (EditText) findViewById(R.id.name);
        _email = (EditText) findViewById(R.id.email);
        _number = (EditText) findViewById(R.id.number);
        _password = (EditText) findViewById(R.id.password);
        _re_password = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.register);

        progressBar = new ProgressBar(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = _number.getText().toString();
                Toast.makeText(getApplicationContext(), number, Toast.LENGTH_SHORT).show();

                name = _name.getText().toString();
                email = _email.getText().toString();
                password = _password.getText().toString();
                re_password = _re_password.getText().toString();
                if (name.trim().equals("") || email.trim().equals("") || number.trim().equals("") || password.trim().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please enter all the fields",
                            Toast.LENGTH_SHORT);

                    toast.show();
                } else if (password.compareTo(re_password) != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Password should be same in both the fields",
                            Toast.LENGTH_SHORT);

                    toast.show();
                } else {
                    new Openlogin().execute();
                    Intent intent = new Intent(getBaseContext(), otp_verification.class);
                    startActivity(intent);
                }

            }
        });
    }

    public class Openlogin extends AsyncTask<String, String, String> {
        boolean isSuccess = false;

        @Override
        public String doInBackground(String... params) {
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

            return null;
    }


}



}
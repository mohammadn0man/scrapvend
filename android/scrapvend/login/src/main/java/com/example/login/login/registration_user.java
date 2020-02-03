package com.example.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.login.otp_verification;

public class registration_user extends AppCompatActivity {
    private Button register;
    public EditText _name, _email, _password, _re_password, _number,username;
    public String name;
    public String email;
    public String password;
    public String re_password,Username;
    public String number;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.registration_user);
        //getSupportActionBar().hide();

        _name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
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

                name = _name.getText().toString();
                Username=username.getText().toString();
                email = _email.getText().toString();
                password = _password.getText().toString();
                number=_number.getText().toString();
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
//                    new Openlogin().execute();
                    Intent intent = new Intent(getBaseContext(), otp_verification.class);
                    intent.putExtra("name",name);
                    intent.putExtra("username",Username);
                    intent.putExtra("email",email);
                    intent.putExtra("number",number);
                    intent.putExtra("password",password);

                    startActivity(intent);
                }

            }
        });
    }

//    public class Openlogin extends AsyncTask<String, String, String> {
//        boolean isSuccess = false;

      //  @Override
//        public String doInBackground(String... params) {
//            try {
//
//                MySqlConnector connection = new MySqlConnector();
//
//                Connection con = connection.getMySqlConnection();
//
//
//                if (con == null) {
//                    Toast toast = Toast.makeText(getApplicationContext(),
//                            "Please check your INTERNET connection",
//                            Toast.LENGTH_SHORT);
//
//                    toast.show();
//                } else {
//                    String query1 = "INSERT INTO `login_info`(`Username`, `Role`, `password`, `email`, `contact_no`) VALUES (\"" + name + number + "\", '2',\"" + password + "\",\"" + email + "\",\"" + number + "\")";
//                    String query2 = "INSERT INTO `user_details`(`Name`, `Username`) VALUES (\"" + name + "\",\"" + name + number + "\")";
//                    Log.d("Reg", "query = " + query1);
//                    Statement statement = con.createStatement();
//                    statement.executeUpdate(query1);
//                    statement.executeUpdate(query2);
//
//                    Toast toast = Toast.makeText(getApplicationContext(),
//                            "Register successful",
//                            Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    isSuccess = true;
//
//                }
//
//            } catch (SQLException e) {
//                isSuccess = false;
//                con.close();
//            }

//            return null;
//    }


//}


}
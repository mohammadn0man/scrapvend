package com.example.login.userUi.contactus;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.MainActivity;
import com.example.login.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.login.MainActivity.user;

public class ContactUsFragment extends Fragment {

    Button send;
    EditText msg,sub;
    private final String TAG = "sp";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        send = (Button) root.findViewById(R.id.send);
        msg = (EditText) root.findViewById(R.id.message);
        sub = (EditText)root.findViewById(R.id.subject);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendDataTask().execute();
            }
        });

        return root;
    }

    private class SendDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();

            try {
                Log.d(TAG, "inside contact fragment");
                //FETCHING DETAILS

                String subject=sub.getText().toString();
                String message=msg.getText().toString();
                Log.d(TAG, "message and subject = "+message+subject);

                Statement statement = conn.createStatement();
                Log.d(TAG, "SELECT user_details.Name,login_info.email,login_info.contact_no FROM login_info INNER JOIN user_details ON login_info.Username=user_details.Username;");
                ResultSet results = statement.executeQuery("SELECT user_details.Name,login_info.email,login_info.contact_no FROM login_info INNER JOIN user_details ON login_info.Username=user_details.Username  where login_info.Username= \""+user+"\";");
                results.next();
                Log.d(TAG,"author name "+results.getString(1));
                Log.d(TAG, "user name = " + user);
                String name,email,contact_no;
                name = results.getString(1);
                email=results.getString(2);
                contact_no=results.getString(3);


                //INSERTING DETAILS INTO CONTACT TABLE
                String query1="INSERT INTO contact_us (Author, Email, Contact_no, Subject, Message)VALUE(?,?,?,?,?); ";
                Log.d(TAG, "query = " + query1);
                PreparedStatement preparedStatement1 = conn.prepareStatement(query1);

                preparedStatement1.setString(1,name);
                preparedStatement1.setString(2,email);
                preparedStatement1.setString(3,contact_no);
                preparedStatement1.setString(4,subject);
                preparedStatement1.setString(5,message);
                preparedStatement1.execute();
                Log.d(TAG, "query Execute ");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    Log.d(TAG, "user name = " + user);
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }
}
package com.example.login.pickupui.home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.login.Adapters.DetailsAdapter;
import com.example.login.Models.Details;
import com.example.login.R;
import com.example.login.DatabaseConnection.MySqlConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    ListView listView;
    Details pmodel;
    DetailsAdapter padapter;

    Context context;
    TextView textViewName, textViewAddress, textViewDate, textViewContact;
    private final String TAG = "MyDBhome";
    DetailsAdapter adapter;
    Details details;
    ArrayList<Details> arrayOfEmp = new ArrayList<>();

    private HomeViewModel homeViewModel;
  //  private  AdapterView<>;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "Hello from Home");


        listView = (ListView) root.findViewById(R.id.listViewEmployees);
        textViewName = (TextView) root.findViewById(R.id.textViewName);
        textViewContact = (TextView) root.findViewById(R.id.textViewContact);
        textViewAddress = (TextView) root.findViewById(R.id.textViewAddress);
        textViewDate = (TextView) root.findViewById(R.id.textViewDate);

        new task().execute();
        context = this.getContext();
        Log.d(TAG, "before intent in home");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                // Getting listview click value into String variable.

                details=arrayOfEmp.get(position);
                Intent intent = new Intent(getActivity(), DetailedPickupInfo.class);

                // Sending value to another activity using intent.
                intent.putExtra("ListViewClickedValue", details.getName());

                startActivity(intent);

            }
        });

        return root;
    }

   
    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();

                int value=1;
//                String query = " SELECT"+" user_details.Username"+" , "+"login_info.contact_no"+",user_details.Address"+",booking_details.Pickup_date_time"+
//                " FROM"+"((user_details"+ " INNER JOIN "+ "booking_details"+" ON "+ "user_details.User_id = booking_details.User_id)"+"INNER JOIN "+
//                "login_info "+"ON"+" user_details.Username = login_info.Username)"+" WHERE " +"booking_details.Pickup_person_id ="+ value ;

                String query = "SELECT login_info.Username, login_info.contact_no, address.City, booking_assigned.Assigned_date " +
                        "from login_info INNER JOIN user_details on login_info.Username = user_details.Username " +
                        "INNER JOIN booking_details ON user_details.User_id = booking_details.User_id " +
                        "INNER JOIN address ON booking_details.Address_id = address.Address_id " +
                        "INNER JOIN booking_assigned ON booking_details.Booking_id = booking_assigned.Booking_id " +
                        "where booking_assigned.Pickup_person_id =" + value;
                Log.d(TAG, query);
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    Log.d(TAG, results.getString("Username") + results.getString("contact_no"));
                    pmodel = new Details(results.getString("Username"), results.getString("contact_no"), results.getString("City"), results.getString("Pickup_date_time"));
                    arrayOfEmp.add(pmodel);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            padapter = new DetailsAdapter(context, R.layout.homelist_layout, arrayOfEmp);
            listView.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}
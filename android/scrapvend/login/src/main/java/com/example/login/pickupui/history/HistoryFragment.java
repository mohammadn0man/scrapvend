package com.example.login.pickupui.history;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.login.Adapters.HistoryAdapter;
import com.example.login.Models.HistoryDetails;
import com.example.login.R;
import com.example.login.DatabaseConnection.MySqlConnector;
import static com.example.login.MainActivity.user;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {

    ListView listView;
    HistoryDetails pmodel;
    HistoryAdapter padapter;
    TextView textViewId, textViewAddress, textViewDate, textViewTime;

    Context context;
    HistoryDetails details;
    ArrayList<HistoryDetails> arrayOfEmp = new ArrayList<>();



    private final String TAG = "MyDBhome";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        listView = (ListView) root.findViewById(R.id.HistoryListView);
        textViewId = (TextView) root.findViewById(R.id.textViewId);
        textViewTime = (TextView) root.findViewById(R.id.textViewTime);
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

            }
        });

        return root;
    }


    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();

                int value=1;
                String query ="select booking_details.Booking_id, booking_details.Pickup_date_time,  address.Line_1, address.City " +
                        "from login_info INNER JOIN pickup_person_details  on login_info.Username = pickup_person_details.Username " +
                        "    INNER join booking_assigned on pickup_person_details.Pickup_person_id = booking_assigned.Pickup_person_id " +
                        "    INNER join booking_details on booking_assigned.Booking_id = booking_details.Booking_id " +
                        "    INNER JOIN address on booking_details.Address_id = address.Address_id " +
                        "where booking_details.Pickup_status=\"Pickup Completed\" and login_info.Username = \"" + user + "\"";

                Log.d(TAG, query);
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    // Log.d(TAG, results.getString("Username") + results.getString("contact_no"));

                    String pickupDate=results.getString("Pickup_date_time").substring(0,10);
                    String pickupTime=results.getString("Pickup_date_time").substring(12);

                    Log.d(TAG, String.valueOf(results.getInt("Booking_id")));
                    pmodel = new HistoryDetails(results.getInt("Booking_id"), results.getString("Line_1") + results.getString("City"),pickupDate,pickupTime);
                    arrayOfEmp.add(pmodel);
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
        protected void onPostExecute(Void aVoid)
        {
            padapter = new HistoryAdapter(context, R.layout.historylist, arrayOfEmp);
            listView.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}




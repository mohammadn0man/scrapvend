package com.example.login.userUi.history;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.HistoryDetails;
import com.example.login.R;
import com.example.login.userUi.Adapter.HistoryUserAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.login.MainActivity.user;

public class HistoryFragment extends Fragment {

//    private HistoryViewModel historyViewModel;
    HistoryDetails historyDetails;
    ArrayList<HistoryDetails> historyDetailsArrayList = new ArrayList<>();
    ListView listView;
    HistoryUserAdapter historyUserAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_user, container, false);
        listView = (ListView) root.findViewById(R.id.history_list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryDetails historyDetailsIntent = historyDetailsArrayList.get(i);
                Intent intent = new Intent(getContext(), DetailedHistoryView.class);
                intent.putExtra("BOOKING_ID", historyDetailsIntent.getBooking_id());
                intent.putExtra("AMOUNT", historyDetailsIntent.getAmount());
                startActivity(intent);
            }
        });

        new HistoryTask().execute();


        return root;
    }

    public class HistoryTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {
                Statement statement = connection.createStatement();
                String query = "SELECT booking_details.Booking_date_time, payment_details.Payment_Amount, booking_details.Booking_id " +
                        "from payment_details INNER JOIN booking_details ON payment_details.Booking_id = booking_details.Booking_id " +
                        "INNER JOIN user_details ON booking_details.User_id = user_details.User_id " +
                        "where user_details.Username = \""+user+"\"";

                Log.e("MyUserHistory query = ", query);

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){

                    historyDetails = new HistoryDetails();
                    historyDetails.setBooking_date(resultSet.getString(1).substring(0,10));
                    historyDetails.setBooking_time(resultSet.getString(1).substring(11));
                    historyDetails.setAmount(resultSet.getString(2));
                    historyDetails.setBooking_id(resultSet.getInt(3));

                    historyDetailsArrayList.add(historyDetails);


                }

            } catch (SQLException e){

            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            historyUserAdapter = new HistoryUserAdapter(getContext(), R.layout.history_user_list_layout, historyDetailsArrayList);
            listView.setAdapter(historyUserAdapter);


        }
    }
}
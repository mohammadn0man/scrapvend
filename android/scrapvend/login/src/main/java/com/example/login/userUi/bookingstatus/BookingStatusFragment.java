package com.example.login.userUi.bookingstatus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.R;
import com.example.login.userUi.Adapter.BookingStatusAdapter;
import com.example.login.userUi.Models.BookingStatusModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.login.MainActivity.user;

public class BookingStatusFragment extends Fragment {

    private BookingStatusViewModel bookingStatusViewModel;
    ListView bookinglist;
//    TextView id,status,bdate;
    BookingStatusAdapter bookingStatusAdapter;
    BookingStatusModel bookingStatusModel;
    ArrayList<BookingStatusModel> arr = new ArrayList<>();
    Context context;
    private static final String TAG ="Booking Status";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingStatusViewModel =
                ViewModelProviders.of(this).get(BookingStatusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking_status, container, false);

        bookinglist = root.findViewById(R.id.statuslist);
        context =this.getContext();

        bookinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onclick list 1"+position+id);

                 BookingStatusModel bookingStatusModel = arr.get(position);
                Log.d(TAG, "id to transfer : "+bookingStatusModel.getBid());
                Intent intent = new Intent(getActivity(),DetailedView.class);
                intent.putExtra("GETID",Integer.toString(bookingStatusModel.getBid()));
                intent.putExtra("GETDateTime",bookingStatusModel.getBooking_date_time());
                intent.putExtra("GETStatus",bookingStatusModel.getStatus());
                intent.putExtra("GETSDate",bookingStatusModel.getSchedule_date());
                intent.putExtra("GETSTime",bookingStatusModel.getSchedule_time());
                startActivity(intent);

            }
        });
        new task().execute();

        return root;
    }
    private class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Connection conn = null;
            MySqlConnector connection = new MySqlConnector();

            conn = connection.getMySqlConnection();

            try {
                Statement statement = conn.createStatement();

                String query = "select Booking_id,Booking_date_time,Pickup_status,Scheduled_time_slot,Scheduled_pickup_date from booking_details bd INNER JOIN user_details ud  ON ud.User_id=bd.User_id where Pickup_status!=\"Pending Completed \" and ud.Username=\""+user+"\";";

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    Log.d(TAG, results.getInt(1)+results.getString(3)+results.getString(2));
                    bookingStatusModel = new BookingStatusModel(results.getInt(1) ,results.getString(3),results.getString(2),results.getString(5),results.getString(4));
                    arr.add(bookingStatusModel);
                }

            } catch (SQLException e) {

                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            bookingStatusAdapter = new BookingStatusAdapter(context, R.layout.booking_list_item, arr);
            bookinglist.setAdapter(bookingStatusAdapter);

            // finding its location

            super.onPostExecute(aVoid);
        }
    }



}
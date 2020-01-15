package com.example.login.userUi.history;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.HistoryDetails;
import com.example.login.Models.ItemQuantityModel;
import com.example.login.R;
import com.example.login.userUi.Adapter.ItemListHistoryAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedHistoryView extends AppCompatActivity {

    ViewHolder viewHolder = new ViewHolder();
    Dialog dialog;
    HistoryDetails historyDetails = new HistoryDetails();
    ArrayList<ItemQuantityModel> itemQuantityModelArrayList = new ArrayList<>();
    ItemQuantityModel itemQuantityModel;
    ItemListHistoryAdapter itemListHistoryAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detailed_view);
        dialog = new Dialog(this);
        Bundle bundle = getIntent().getExtras();
        historyDetails.setBooking_id(bundle.getInt("BOOKING_ID"));
        historyDetails.setAmount(bundle.getString("AMOUNT"));

        viewHolder.bookingId = (TextView) findViewById(R.id.booking_id_history);
        viewHolder.bookingDate = (TextView) findViewById(R.id.booking_date);
        viewHolder.bookingStatus = (TextView) findViewById(R.id.booking_status);
        viewHolder.amount = (TextView) findViewById(R.id.amount);
        viewHolder.bookingTime = (TextView) findViewById(R.id.booking_time);
        viewHolder.pickupDate = (TextView) findViewById(R.id.pickup_date);
        viewHolder.pickupTime = (TextView) findViewById(R.id.pickup_time);
        viewHolder.pickupPersonName = (TextView) findViewById(R.id.pickup_person_name);
        viewHolder.pickupPersonContactNo = (TextView) findViewById(R.id.pickup_person_contact_no);
        viewHolder.itemList = (Button) findViewById(R.id.item_list_button);
        viewHolder.done = (Button) findViewById(R.id.done);

        viewHolder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new DetailedHistoryTask().execute();

        viewHolder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.item_list_pop_history);

                viewHolder.itemListListView = dialog.findViewById(R.id.item_list_list_view);
                viewHolder.X = dialog.findViewById(R.id.txtclose);

                viewHolder.X.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                itemListHistoryAdapter = new ItemListHistoryAdapter(getBaseContext(), R.layout.history_item_list_listview_layout, itemQuantityModelArrayList);
                viewHolder.itemListListView.setAdapter(itemListHistoryAdapter);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

    }


    public class DetailedHistoryTask extends AsyncTask<Object, Object, Object>{

        @Override
        protected Object doInBackground(Object[] objects) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {
                Statement statement = connection.createStatement();
                String query = "SELECT * " +
                        " FROM booking_details INNER JOIN booking_assigned ON booking_details.Booking_id = booking_assigned.Booking_id " +
                        " INNER JOIN pickup_person_details ON booking_assigned.Pickup_person_id = pickup_person_details.Pickup_person_id " +
                        " INNER JOIN login_info ON pickup_person_details.Username = login_info.Username " +
                        " WHERE booking_details.Booking_id = " + historyDetails.getBooking_id();

                Log.e("MyDetailedHistory", "query = " +query );

                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    historyDetails.setBooking_date(resultSet.getString("Booking_date_time").substring(0,10));
                    historyDetails.setBooking_time(resultSet.getString("Booking_date_time").substring(11));
                    historyDetails.setPickup_date(resultSet.getString("Pickup_date_time").substring(0,10));
                    historyDetails.setPickup_time(resultSet.getString("Pickup_date_time").substring(11));
                    historyDetails.setBooking_status(resultSet.getString("Pickup_Status"));
                    historyDetails.setPickup_person_name(resultSet.getString("Name"));
                    historyDetails.setPickup_person_contact_no(resultSet.getString("contact_no"));
                }

                Statement statement1 = connection.createStatement();

                ResultSet resultSet1 = statement1.executeQuery("SELECT booked_item_list.Item_qty, item_details.Item_name FROM booked_item_list " +
                        " INNER JOIN item_details ON booked_item_list.Item_id = item_details.Item_id" +
                        " where booked_item_list.Booking_id = "  + historyDetails.getBooking_id());

                while (resultSet1.next()) {
                    itemQuantityModel = new ItemQuantityModel();
                    itemQuantityModel.setItemqty(resultSet1.getString(1));
                    itemQuantityModel.setItemName(resultSet1.getString(2));
                    itemQuantityModelArrayList.add(itemQuantityModel);
                }

            } catch (SQLException e){
                e.printStackTrace();
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
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            viewHolder.bookingDate.setText(historyDetails.getBooking_date());
            viewHolder.bookingTime.setText(historyDetails.getBooking_time());
            viewHolder.pickupTime.setText(historyDetails.getPickup_time());
            viewHolder.pickupDate.setText(historyDetails.getPickup_time());
            viewHolder.amount.setText(historyDetails.getAmount());
            viewHolder.bookingStatus.setText(historyDetails.getBooking_status());
            viewHolder.pickupPersonName.setText(historyDetails.getPickup_person_name());
            viewHolder.pickupPersonContactNo.setText(historyDetails.getPickup_person_contact_no());
            viewHolder.bookingId.setText(historyDetails.getBooking_id()+" ");

        }
    }

    public class ViewHolder{
        public TextView bookingId;
        public TextView bookingDate;
        public TextView bookingTime;
        public TextView pickupDate;
        public TextView pickupTime;
        public TextView amount;
        public TextView bookingStatus;
        public TextView pickupPersonName;
        public TextView pickupPersonContactNo;
        public TextView X;
        public Button itemList;
        public Button done;
        public ListView itemListListView;

    }
}

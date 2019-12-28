package com.example.scrapvend.ui.pickupinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupinfoModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class PickupInfoPendingPickup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    PickupInfoPendingPickup.ViewHolder viewHolder = new PickupInfoPendingPickup.ViewHolder();

    PickupinfoModel pickupinfoModel = new PickupinfoModel();
    String GET_PICKUPLIST_FLAG;
    String query;
    final String TAG = "MyPickupinfoPending";
    String[] pickupinfoCategory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickupinfo_pending_pickup);

        Log.d(TAG, "From PickupinfoView");
        viewHolder.setCustomerNameTextView((TextView) findViewById(R.id.customer_name));
        viewHolder.setScheduledDateTextView((TextView) findViewById(R.id.scheduled_date_time));
        viewHolder.setAddressTextView((TextView) findViewById(R.id.location));
//        viewHolder.setUserNameTextView((TextView) findViewById(R.id.user_name));
//        viewHolder.setAssignedDateTimeTextView((TextView) findViewById(R.id.booking_assign_date_time));
//        viewHolder.setBookingDateTimeTextView((TextView) findViewById(R.id.booked_date_time));
//        viewHolder.setPickupStatusTextView((TextView) findViewById(R.id.pickup_status));
//        viewHolder.setBookingIdTextView((TextView) findViewById(R.id.booking_id));
//        viewHolder.setPickupPersonIdTextView((TextView) findViewById(R.id.pickup_person_id));
//        viewHolder.setPickupPersonNameTextView((TextView) findViewById(R.id.pickup_person_name));

        Bundle bundle = getIntent().getExtras();
        pickupinfoModel.setAddress(bundle.getString("ADDRESS"));
        viewHolder.getAddressTextView().setText(pickupinfoModel.getAddress());
        pickupinfoModel.setBookingId(bundle.getString("BOOKING_ID"));
//        viewHolder.getBookingIdTextView().setText(pickupinfoModel.getBookingId());
        GET_PICKUPLIST_FLAG = bundle.getString("GET_PICKUPLIST_FLAG");
        pickupinfoModel.setPickupStatus(GET_PICKUPLIST_FLAG);
        pickupinfoCategory = getResources().getStringArray(R.array.pickupinfo_category_name);
//        viewHolder.getPickupStatusTextView().setText(pickupinfoModel.getPickupStatus());

        new PickupInfoPendingPickup.PickupInfoPendingPickupTask().execute();

    }

    private class PickupInfoPendingPickupTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();

                query = "SELECT booking_details.Booking_date_time, booking_details.Scheduled_pickup_date_time, " +
                        "user_details.Username, booking_details.Pickup_status, user_details.Name, " +
                        "booking_assigned.Assigned_date, pickup_person_details.Pickup_person_id, " +
                        "pickup_person_details.Name " +
                        "FROM booking_details " +
                        "INNER JOIN user_details ON user_details.User_id = booking_details.User_id " +
                        "INNER JOIN booking_assigned ON booking_assigned.Booking_id = booking_details.Booking_id " +
                        "INNER JOIN pickup_person_details ON booking_assigned.Pickup_person_id = pickup_person_details.Pickup_person_id " +
                        "where booking_details.Booking_id =" + pickupinfoModel.getBookingId();

                Log.d(TAG, "query == "+ query);

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    Log.d(TAG, results.getString(1) + results.getString(2));
                    pickupinfoModel.setBookedDate(results.getString(1));
                    pickupinfoModel.setSchuduleDate(results.getString(2));
                    pickupinfoModel.setUsername(results.getString(3));
                    pickupinfoModel.setCustomerName(results.getString(5));
                    pickupinfoModel.setAssignedDate(results.getString(6));
                    pickupinfoModel.setPickupPersonId(results.getString(7));
                    pickupinfoModel.setPickupPersonName(results.getString(8));

                    Log.d(TAG, "name"+pickupinfoModel.getUsername());
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textViewFiller(GET_PICKUPLIST_FLAG);

        }
    }

    private void textViewFiller(String get_pickuplist_flag){

        viewHolder.getCustomerNameTextView().setText(pickupinfoModel.getCustomerName());
        viewHolder.getScheduledDateTextView().setText(pickupinfoModel.getSchuduleDate());
//        viewHolder.getBookingDateTimeTextView().setText(pickupinfoModel.getBookedDate());
//        viewHolder.getUserNameTextView().setText(pickupinfoModel.getUsername());
//        viewHolder.getAssignedDateTimeTextView().setText(pickupinfoModel.getAssignedDate());
//        viewHolder.getPickupPersonIdTextView().setText(pickupinfoModel.getPickupPersonId());
//        viewHolder.getPickupPersonNameTextView().setText(pickupinfoModel.getPickupPersonName());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class ViewHolder{
        TextView userNameTextView;
        TextView bookingIdTextView;
        TextView customerNameTextView;
        TextView addressTextView;
        TextView ScheduledDateTextView;
        TextView bookingDateTimeTextView;
        TextView assignedDateTimeTextView;
        TextView pickupStatusTextView;
        TextView pickupPersonNameTextView;
        TextView pickupPersonIdTextView;



        public TextView getScheduledDateTextView() {
            return ScheduledDateTextView;
        }

        public void setScheduledDateTextView(TextView scheduledDateTextView) {
            ScheduledDateTextView = scheduledDateTextView;
        }

        public TextView getBookingDateTimeTextView() {
            return bookingDateTimeTextView;
        }

        public void setBookingDateTimeTextView(TextView bookingDateTimeTextView) {
            this.bookingDateTimeTextView = bookingDateTimeTextView;
        }

        public TextView getAssignedDateTimeTextView() {
            return assignedDateTimeTextView;
        }

        public void setAssignedDateTimeTextView(TextView assignedDateTimeTextView) {
            this.assignedDateTimeTextView = assignedDateTimeTextView;
        }

        public TextView getPickupStatusTextView() {
            return pickupStatusTextView;
        }

        public void setPickupStatusTextView(TextView pickupStatusTextView) {
            this.pickupStatusTextView = pickupStatusTextView;
        }

        public TextView getPickupPersonNameTextView() {
            return pickupPersonNameTextView;
        }

        public void setPickupPersonNameTextView(TextView pickupPersonNameTextView) {
            this.pickupPersonNameTextView = pickupPersonNameTextView;
        }

        public TextView getPickupPersonIdTextView() {
            return pickupPersonIdTextView;
        }

        public void setPickupPersonIdTextView(TextView pickupPersonIdTextView) {
            this.pickupPersonIdTextView = pickupPersonIdTextView;
        }

        public TextView getUserNameTextView() {
            return userNameTextView;
        }

        public void setUserNameTextView(TextView userNameTextView) {
            this.userNameTextView = userNameTextView;
        }

        public TextView getBookingIdTextView() {
            return bookingIdTextView;
        }

        public void setBookingIdTextView(TextView bookingIdTextView) {
            this.bookingIdTextView = bookingIdTextView;
        }

        public TextView getCustomerNameTextView() {
            return customerNameTextView;
        }

        public void setCustomerNameTextView(TextView customerNameTextView) {
            this.customerNameTextView = customerNameTextView;
        }

        public TextView getAddressTextView() {
            return addressTextView;
        }

        public void setAddressTextView(TextView addressTextView) {
            this.addressTextView = addressTextView;
        }
    }


}
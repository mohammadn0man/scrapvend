package com.example.scrapvend.ui.pickupinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.DymanicSwitch.Switcher;
import com.example.scrapvend.Models.PickupinfoModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class PickupInfoView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ViewHolder viewHolder = new ViewHolder();

    PickupinfoModel pickupinfoModel = new PickupinfoModel();
    String GET_PICKUPLIST_FLAG;
    String query;
    final String TAG = "MyPickupinfoView";
    String[] pickupinfoCategory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickupinfo_detailed_view);
        Log.e(TAG, "From PickupinfoView");
        viewHolder.setUserNameTextView((TextView) findViewById(R.id.user_name));
        viewHolder.setBookingIdTextView((TextView) findViewById(R.id.booking_id));
        viewHolder.setAddressTextView((TextView) findViewById(R.id.Address));
        viewHolder.setCustomerNameTextView((TextView) findViewById(R.id.customer_name));
        viewHolder.setAssignedDateTimeTextView((TextView) findViewById(R.id.booking_assign_date_time));
        viewHolder.setBookingDateTimeTextView((TextView) findViewById(R.id.booked_date_time));
        viewHolder.setPaymentModeTextView((TextView) findViewById(R.id.payment_mode));
        viewHolder.setPaymentAmountTextView((TextView) findViewById(R.id.payment_amount));
        viewHolder.setPaymentStatusTextView((TextView) findViewById(R.id.payment_status));
        viewHolder.setScheduledDateTextView((TextView) findViewById(R.id.scheduled_date_time));
        viewHolder.setPickupStatusTextView((TextView) findViewById(R.id.pickup_status));
        viewHolder.setPickupPersonIdTextView((TextView) findViewById(R.id.pickup_person_id));
        viewHolder.setPickupPersonNameTextView((TextView) findViewById(R.id.pickup_person_name));
        viewHolder.setPickupDateTimeTextView((TextView) findViewById(R.id.pickup_date_time));

        Bundle bundle = getIntent().getExtras();
        pickupinfoModel.setAddress(bundle.getString("ADDRESS"));
        pickupinfoModel.setBookingId(bundle.getString("BOOKING_ID"));
        GET_PICKUPLIST_FLAG = bundle.getString("GET_PICKUPLIST_FLAG");

        viewHolder.getAddressTextView().setText(pickupinfoModel.getAddress());
        viewHolder.getBookingIdTextView().setText(pickupinfoModel.getBookingId());

        Switcher switcher = new Switcher();
        pickupinfoCategory = getResources().getStringArray(R.array.pickupinfo_category_name);
        query = generateQuery(GET_PICKUPLIST_FLAG);
        new PickupInfoViewTask().execute();

        /*  Dynamic switch implemntation
        for (final String categories : pickupinfoCategory){

            switcher.addCaseCommand(categories, new Command() {
                @Override
                public void execute() {
                    query = generateQuery(categories);
                }
            });

        }
        switcher.on(GET_PICKUPLIST_FLAG);
        */




    }

    private class PickupInfoViewTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {


            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    Log.d(TAG, results.getString(1) + results.getString(2));
                    pickupinfoModel.setBookedDate(results.getString(1));
                    pickupinfoModel.setSchuduleDate(results.getString(2));
                    pickupinfoModel.setUsername(results.getString(3));
                    pickupinfoModel.setBookingId(results.getString(4));
                    pickupinfoModel.setCustomerName(results.getString(5));
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
    private String generateQuery(String get_pickuplist_flag) {
        String str = "";
        if (get_pickuplist_flag.equals(pickupinfoCategory[0])){
            str = "SELECT booking_details.Booking_date_time, booking_details.Scheduled_pickup_date_time, user_details.Username, booking_details.Booking_id, user_details.Name FROM booking_details INNER JOIN user_details ON user_details.User_id = booking_details.User_id where Pickup_status = \"Pending Pickup\" and Booking_id = "+ pickupinfoModel.getBookingId() +";";
        } else if (get_pickuplist_flag.equals(pickupinfoCategory[1])){
            str = "x";
        } else if (get_pickuplist_flag.equals(pickupinfoCategory[2])){
            str = "x";
        } else if (get_pickuplist_flag.equals(pickupinfoCategory[3])){
            str = "x";
        } else {
            str = "Invalid Parameter Query not generated";
        }

        return str;
    }

    private void textViewFiller(String get_pickuplist_flag)    {

        if (get_pickuplist_flag.equals(pickupinfoCategory[0])){

            viewHolder.getBookingDateTimeTextView().setText(pickupinfoModel.getBookedDate());
            viewHolder.getScheduledDateTextView().setText(pickupinfoModel.getSchuduleDate());
            viewHolder.getUserNameTextView().setText(pickupinfoModel.getUsername());
            viewHolder.getCustomerNameTextView().setText(pickupinfoModel.getCustomerName());

        } else if (get_pickuplist_flag.equals(pickupinfoCategory[1])){

        } else if (get_pickuplist_flag.equals(pickupinfoCategory[2])){

        } else if (get_pickuplist_flag.equals(pickupinfoCategory[3])){

        } else {

        }

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
        TextView paymentStatusTextView;
        TextView paymentAmountTextView;
        TextView paymentModeTextView;
        TextView pickupDateTimeTextView;

        public TextView getPaymentAmountTextView() {
            return paymentAmountTextView;
        }

        public void setPaymentAmountTextView(TextView paymentAmountTextView) {
            this.paymentAmountTextView = paymentAmountTextView;
        }

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

        public TextView getPaymentStatusTextView() {
            return paymentStatusTextView;
        }

        public void setPaymentStatusTextView(TextView paymentStatusTextView) {
            this.paymentStatusTextView = paymentStatusTextView;
        }

        public TextView getPaymentModeTextView() {
            return paymentModeTextView;
        }

        public void setPaymentModeTextView(TextView paymentModeTextView) {
            this.paymentModeTextView = paymentModeTextView;
        }

        public TextView getPickupDateTimeTextView() {
            return pickupDateTimeTextView;
        }

        public void setPickupDateTimeTextView(TextView pickupDateTimeTextView) {
            this.pickupDateTimeTextView = pickupDateTimeTextView;
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

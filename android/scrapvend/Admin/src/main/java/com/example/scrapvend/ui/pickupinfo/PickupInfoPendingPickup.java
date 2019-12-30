package com.example.scrapvend.ui.pickupinfo;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrapvend.Adapters.WorkingAdapter;
import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.Models.PickupinfoModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PickupInfoPendingPickup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ViewHolder viewHolder = new ViewHolder();
    ListView listView;
    PickupinfoModel pickupinfoModel = new PickupinfoModel();
    ArrayList<PickupPersonModel> pickupPersonModelArrayList = new ArrayList<>();
    PickupPersonModel pickupPersonModel, pickupPersonModelIntent;
    String GET_PICKUPLIST_FLAG;
    WorkingAdapter workingAdapter;
    String selectedDate;
    final String TAG = "MyPickupinfoPending";
    String[] pickupinfoCategory;
    String selectedSlot;
    Dialog myDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickupinfo_pending_pickup);
        myDialog = new Dialog(this);

        Log.d(TAG, "From PickupinfoView");
        viewHolder.setCustomerNameTextView((TextView) findViewById(R.id.customer_name));
        viewHolder.setScheduledDateTextView((TextView) findViewById(R.id.scheduled_date_time));
        viewHolder.setAddressTextView((TextView) findViewById(R.id.location));
        listView = (ListView) findViewById(R.id.workingListView);
//        viewHolder.setUserNameTextView((TextView) findViewById(R.id.user_name));
//        viewHolder.setAssignedDateTimeTextView((TextView) findViewById(R.id.booking_assign_date_time));
//        viewHolder.setBookingDateTimeTextView((TextView) findViewById(R.id.booked_date_time));
//        viewHolder.setPickupStatusTextView((TextView) findViewById(R.id.pickup_status));
//        viewHolder.setBookingIdTextView((TextView) findViewById(R.id.booking_id));
//        viewHolder.setPickupPersonIdTextView((TextView) findViewById(R.id.pickup_person_id));
//        viewHolder.setPickupPersonNameTextView((TextView) findViewById(R.id.pickup_person_name));

        Bundle bundle = getIntent().getExtras();
        GET_PICKUPLIST_FLAG = bundle.getString("GET_PICKUPLIST_FLAG");
        pickupinfoModel.setPickupStatus(GET_PICKUPLIST_FLAG);
        pickupinfoModel.setBookingId(bundle.getString("BOOKING_ID"));
        pickupinfoModel.setUsername(bundle.getString("USERNAME"));
        pickupinfoModel.setSchuduleDate(bundle.getString("SCHEDULEDDATE"));
        selectedDate = pickupinfoModel.getSchuduleDate().substring(0,10);
        pickupinfoModel.setAddress(bundle.getString("ADDRESS"));

        viewHolder.getCustomerNameTextView().setText(pickupinfoModel.getUsername());
        viewHolder.getScheduledDateTextView().setText(pickupinfoModel.getSchuduleDate());
        viewHolder.getAddressTextView().setText(pickupinfoModel.getAddress());
        pickupinfoCategory = getResources().getStringArray(R.array.pickupinfo_category_name);
//        viewHolder.getPickupStatusTextView().setText(pickupinfoModel.getPickupStatus());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pickupPersonModelIntent = pickupPersonModelArrayList.get(i);
                TextView txtclose, name, date;
                Button submit;
                final RadioButton slot1, slot2, slot3, slot4;
                myDialog.setContentView(R.layout.custompopup);

                slot1 = (RadioButton) myDialog.findViewById(R.id.slot_1);
                slot2 = (RadioButton) myDialog.findViewById(R.id.slot_2);
                slot3 = (RadioButton) myDialog.findViewById(R.id.slot_3);
                slot4 = (RadioButton) myDialog.findViewById(R.id.slot_4);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                name = (TextView) myDialog.findViewById(R.id.pickup_person_name);
                date = (TextView) myDialog.findViewById(R.id.working_date);
                submit = (Button) myDialog.findViewById(R.id.btnfollow);

                name.setText(pickupPersonModelIntent.getName());
                date.setText(date.getText()+selectedDate);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (slot1.isChecked()) {
                            selectedSlot = "1";
                        } else if (slot2.isChecked()) {
                            selectedSlot = "2";
                        } else if (slot3.isChecked()) {
                            selectedSlot = "3";
                        } else if (slot4.isChecked()) {
                            selectedSlot = "4";
                        }
                        Toast.makeText(getApplicationContext(), selectedSlot, Toast.LENGTH_LONG).show(); // print the value of selected super star

                        new AssignPickupPersonTask().execute();
                        myDialog.dismiss();

                    }
                });

                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });

        new PickupInfoPendingPickup.PickupInfoPendingPickupTask().execute();

    }

    private class PickupInfoPendingPickupTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();
            try {
                Statement statement = conn.createStatement();

                String query = "select pickup_person_details.Name, pickup_person_details.Address, pickup_person_details.Pickup_person_id " +
                        " from pickup_person_details where Pickup_person_id not in " +
                        "(select pickup_person_record.Pickup_person_id from pickup_person_record" +
                        " where pickup_person_record.Approval_status = 3" +
                        " and pickup_person_record.`10:00AM-12:00PM` = 1" +
                        " and pickup_person_record.`02:00PM-04:00PM` = 1" +
                        " and pickup_person_record.`12:00PM-02:00PM` = 1" +
                        " and pickup_person_record.`04:00PM-06:00PM` = 1" +
                        " and pickup_person_record.Date = \"" + selectedDate + "\")";

                Log.d(TAG, "query == "+ query);

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    Log.d(TAG, results.getString(1) + results.getString(2));
                    pickupPersonModel = new PickupPersonModel();
                    Log.e(TAG, results.getString(1));
                    pickupPersonModel.setName(results.getString(1));
                    pickupPersonModel.setAddress(results.getString(2));
                    pickupPersonModel.setId(results.getString(3));

                    pickupPersonModelArrayList.add(pickupPersonModel);

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


            workingAdapter = new WorkingAdapter(getBaseContext(), R.layout.working_list_layout,pickupPersonModelArrayList);
            listView.setAdapter(workingAdapter);

        }
    }

    private class AssignPickupPersonTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Connection connection;
            MySqlConnector mySqlConnector = new MySqlConnector();
            connection = mySqlConnector.getMySqlConnection();

            try{
                PreparedStatement preparedStatement = connection.prepareStatement("insert into booking_assigned (Pickup_person_id, Booking_id,Assign_slot) values (?, ?, ?);");
                preparedStatement.setString(1, pickupPersonModelIntent.getId());
                preparedStatement.setString(2, pickupinfoModel.getBookingId());
                preparedStatement.setString(3, selectedSlot);

                preparedStatement.execute();

                PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE booking_details t SET t.Pickup_status = 'Pickup Person Assigned' WHERE Booking_id =? ");
                preparedStatement1.setString(1, pickupinfoModel.getBookingId());

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
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
//            finish();
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
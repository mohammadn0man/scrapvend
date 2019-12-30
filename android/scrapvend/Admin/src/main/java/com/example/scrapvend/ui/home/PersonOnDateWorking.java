package com.example.scrapvend.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class PersonOnDateWorking extends AppCompatActivity  {

    String date;
    PickupPersonModel pickupPersonModel =new PickupPersonModel();
    private static String TAG = "MyPersonOnDate";
    ViewHolder viewHolder = new ViewHolder();
    String[] availablity = {"Available", "Unavailable"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_on_date_working);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        date = bundle.getString("DATE");
        pickupPersonModel.setId(bundle.getString("PERSON_ID"));
        pickupPersonModel.setName(bundle.getString("PERSON_NAME"));
        pickupPersonModel.setAddress(bundle.getString("PERSON_ADDRESS"));

        viewHolder.setDate((TextView) findViewById(R.id.working_date));
        viewHolder.setPersonIdTextView((TextView) findViewById(R.id.pickup_person_id));
        viewHolder.setPersonNameTextView((TextView) findViewById(R.id.pickup_person_name));
        viewHolder.setPersonAddress((TextView) findViewById(R.id.pickup_person_address));
        viewHolder.setSlot1((TextView) findViewById(R.id.slot_1));
        viewHolder.setSlot2((TextView) findViewById(R.id.slot_2));
        viewHolder.setSlot3((TextView) findViewById(R.id.slot_3));
        viewHolder.setSlot4((TextView) findViewById(R.id.slot_4));

        viewHolder.getDate().setText(viewHolder.getDate().getText()+date);
        viewHolder.getPersonIdTextView().setText(pickupPersonModel.getId());
        viewHolder.getPersonNameTextView().setText(pickupPersonModel.getName());
        viewHolder.getPersonAddress().setText(pickupPersonModel.getAddress());

        Button backButton = (Button)this.findViewById(R.id.done);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new OnDateWorkingTask().execute();


    }

    class OnDateWorkingTask extends AsyncTask<Void, Void, Void>{

        Connection connection;

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            connection = mySqlConnector.getMySqlConnection();

            try {
                Statement statement = connection.createStatement();
                String query = "select pickup_person_record.`10:00AM-12:00PM`, pickup_person_record.`12:00PM-02:00PM`, " +
                        "pickup_person_record.`02:00PM-04:00PM`, pickup_person_record.`04:00PM-06:00PM` " +
                        "from pickup_person_record where pickup_person_record.Pickup_person_id = " + pickupPersonModel.getId() +
                        " and pickup_person_record.Date = \""+date+"\"";
                Log.d(TAG, "query == " + query);

                ResultSet resultSet =  statement.executeQuery(query);

                if(resultSet.next()){
                    pickupPersonModel.setT1(resultSet.getInt(1));
                    pickupPersonModel.setT2(resultSet.getInt(2));
                    pickupPersonModel.setT3(resultSet.getInt(3));
                    pickupPersonModel.setT4(resultSet.getInt(4));
                } else {
                    pickupPersonModel.setT1(0);
                    pickupPersonModel.setT2(0);
                    pickupPersonModel.setT3(0);
                    pickupPersonModel.setT4(0);
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            viewHolder.getSlot1().setText(availablity[pickupPersonModel.getT1()]);
            viewHolder.getSlot2().setText(availablity[pickupPersonModel.getT2()]);
            viewHolder.getSlot3().setText(availablity[pickupPersonModel.getT3()]);
            viewHolder.getSlot4().setText(availablity[pickupPersonModel.getT4()]);

        }
    }

    class ViewHolder{
        private TextView personNameTextView;
        private TextView personIdTextView;
        private TextView personAddress;
        private TextView date;
        private TextView slot1;
        private TextView slot2;
        private TextView slot3;
        private TextView slot4;

        public TextView getPersonNameTextView() {
            return personNameTextView;
        }

        public void setPersonNameTextView(TextView personNameTextView) {
            this.personNameTextView = personNameTextView;
        }

        public TextView getPersonIdTextView() {
            return personIdTextView;
        }

        public void setPersonIdTextView(TextView personIdTextView) {
            this.personIdTextView = personIdTextView;
        }

        public TextView getPersonAddress() {
            return personAddress;
        }

        public void setPersonAddress(TextView personAddress) {
            this.personAddress = personAddress;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        public TextView getSlot1() {
            return slot1;
        }

        public void setSlot1(TextView slot1) {
            this.slot1 = slot1;
        }

        public TextView getSlot2() {
            return slot2;
        }

        public void setSlot2(TextView slot2) {
            this.slot2 = slot2;
        }

        public TextView getSlot3() {
            return slot3;
        }

        public void setSlot3(TextView slot3) {
            this.slot3 = slot3;
        }

        public TextView getSlot4() {
            return slot4;
        }

        public void setSlot4(TextView slot4) {
            this.slot4 = slot4;
        }
    }
}


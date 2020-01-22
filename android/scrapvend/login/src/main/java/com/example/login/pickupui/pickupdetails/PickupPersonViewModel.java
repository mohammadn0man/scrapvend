package com.example.login.pickupui.pickupdetails;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.PickupPersonProfileModel;
import com.example.login.Models.UserAddressModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.login.MainActivity.user;

public class PickupPersonViewModel extends ViewModel {

  //  private MutableLiveData<String> mText;
    public PickupPersonProfileModel pickupPersonProfileModel = new PickupPersonProfileModel();


    private MutableLiveData<PickupPersonProfileModel> pickupPersonProfileModelMutableLiveData;

    public LiveData<PickupPersonProfileModel> getText() {
        return pickupPersonProfileModelMutableLiveData;

    }
    public PickupPersonViewModel() {

        pickupPersonProfileModelMutableLiveData = new MutableLiveData<>();
        new ProfileTask().execute();
//
    }
    public class ProfileTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pickupPersonProfileModelMutableLiveData.setValue(pickupPersonProfileModel);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {
                Statement statement = connection.createStatement();
                String query;
                query = "SELECT * from login_info INNER JOIN pickup_person_details ON login_info.Username = pickup_person_details.Username" +
                        " WHERE login_info.Username = \"" + user + "\" ";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()){
                    pickupPersonProfileModel.setUsername(resultSet.getString("login_info.Username"));
                    pickupPersonProfileModel.setName(resultSet.getString("Name"));
                    pickupPersonProfileModel.setContactNo(resultSet.getString("contact_no"));
                    pickupPersonProfileModel.setEmail(resultSet.getString("email"));
                    pickupPersonProfileModel.setPassword(resultSet.getString("password"));
                    pickupPersonProfileModel.setUserId(resultSet.getInt("User_id"));
                }

                Log.e("MyProfile", pickupPersonProfileModel.getName());

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
    }

}
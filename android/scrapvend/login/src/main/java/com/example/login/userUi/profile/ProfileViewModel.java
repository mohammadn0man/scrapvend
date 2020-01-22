package com.example.login.userUi.profile;

import android.os.AsyncTask;
import android.util.Log;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.UserAddressModel;
import com.example.login.Models.UserProfileModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.example.login.MainActivity.user;

public class ProfileViewModel extends ViewModel {

    public UserProfileModel userProfileModel = new UserProfileModel();
    public ArrayList<UserAddressModel> userAddressModelArrayList = new ArrayList<>();
    public UserAddressModel userAddressModel;
    private MutableLiveData<UserProfileModel> userProfileModelMutableLiveData;

    public LiveData<UserProfileModel> getText() {
        return userProfileModelMutableLiveData;
    }

    public ProfileViewModel() {
        userProfileModelMutableLiveData = new MutableLiveData<>();
        new UserProfileTask().execute();
//        userProfileModelMutableLiveData.setValue(userProfileModel);
//        userProfileModelMutableLiveData.setValue("This is profile fragment");
    }

    public class UserProfileTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userProfileModelMutableLiveData.setValue(userProfileModel);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {
                Statement statement = connection.createStatement();
                String query;
                query = "SELECT * from login_info INNER JOIN user_details ON login_info.Username = user_details.Username" +
                        " WHERE login_info.Username = \"" + user + "\" ";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()){
                    userProfileModel.setUsername(resultSet.getString("login_info.Username"));
                    userProfileModel.setName(resultSet.getString("Name"));
                    userProfileModel.setContactNo(resultSet.getString("contact_no"));
                    userProfileModel.setEmail(resultSet.getString("email"));
                    userProfileModel.setPassword(resultSet.getString("password"));
                    userProfileModel.setUserId(resultSet.getInt("User_id"));
                }

                Log.e("MyProfile", userProfileModel.getName());

                Statement statement1 = connection.createStatement();
                query = "SELECT * from address where View_value = 1 AND User_id = \"" + userProfileModel.getUserId() + "\"";
                ResultSet resultSet1 = statement1.executeQuery(query);

                while (resultSet1.next()) {

                    userAddressModel = new UserAddressModel();
                    userAddressModel.setAddressId(resultSet1.getInt("Address_id"));
                    userAddressModel.setCity(resultSet1.getString("City"));
                    userAddressModel.setHouseNo(resultSet1.getString("House_no"));
                    userAddressModel.setLine1(resultSet1.getString("Line_1"));
                    userAddressModel.setState(resultSet1.getString("State"));
                    userAddressModel.setZipCode(resultSet1.getString("Zip_code"));
                    userAddressModelArrayList.add(userAddressModel);

                }

                userProfileModel.setAddressModel(userAddressModelArrayList);


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
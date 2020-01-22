package com.example.login.userUi.profile;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.UserAddressModel;
import com.example.login.Models.UserProfileModel;
import com.example.login.R;
import com.example.login.userUi.Adapter.AddressListAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.login.MainActivity.user;

public class ProfileFragment extends Fragment {

    Dialog dialog;
    String changedPassword;
    public UserProfileModel userProfileModel;
    private ProfileViewModel profileViewModel;
    public UserAddressModel userAddressModel = new UserAddressModel();
    public ArrayList<UserAddressModel> userAddressModelArrayList;
    public ArrayList<UserAddressModel> userAddressModelArrayListSelected;
    public static AddressListAdapter addressListAdapter;
    public ViewHolder viewHolder = new ViewHolder();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        dialog = new Dialog(getContext());

        viewHolder.username = root.findViewById(R.id.username);
        viewHolder.name = root.findViewById(R.id.name);
        viewHolder.contactNo = root.findViewById(R.id.contact_no);
        viewHolder.email = root.findViewById(R.id.email);
        viewHolder.addressListView = root.findViewById(R.id.address_list);
        viewHolder.delete = root.findViewById(R.id.delete);
        viewHolder.addNew = root.findViewById(R.id.add_new);
        viewHolder.changePassword = root.findViewById(R.id.change_password);

        profileViewModel.getText().observe(this, new Observer<UserProfileModel>() {
            @Override
            public void onChanged(@Nullable UserProfileModel s) {
                viewHolder.username.setText(s.getUsername());
                viewHolder.name.setText(s.getName());
                viewHolder.contactNo.setText(s.getContactNo());
                viewHolder.email.setText(s.getEmail());
                userAddressModelArrayList = s.getAddressModel();
                if(userAddressModelArrayList.size()>0) {
                    addressListAdapter = new AddressListAdapter(getContext(), R.layout.address_list_view_layout, userAddressModelArrayList);
                    viewHolder.addressListView.setAdapter(addressListAdapter);
                    viewHolder.addressListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                }else{
                    // no address to show
                }

                userProfileModel = s;

                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (addressListAdapter != null){

                            userAddressModelArrayListSelected = addressListAdapter.getCheckedItems();

                            if (userAddressModelArrayListSelected.size() == 0 ){
                                Toast toast = Toast.makeText(getContext(),
                                        "Please select atleast one Address",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                new DeleteAddressTask().execute();
                            }

                        }

                    }
                });

                viewHolder.changePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.setContentView(R.layout.change_password_profile_popup);
                        System.out.println("here");
                        viewHolder.oldPassword = dialog.findViewById(R.id.old_password);
                        viewHolder.newPassword = dialog.findViewById(R.id.new_password);
                        viewHolder.confirmPassword = dialog.findViewById(R.id.confirm_password);
                        viewHolder.changePasswordButton = dialog.findViewById(R.id.change_password_button);
                        viewHolder.X = dialog.findViewById(R.id.txtclose);

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                        viewHolder.X.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        viewHolder.changePasswordButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(viewHolder.oldPassword.getText().toString().equals(userProfileModel.getPassword())){
                                    if(viewHolder.newPassword.getText().toString().equals(viewHolder.confirmPassword.getText().toString())){

                                        changedPassword = viewHolder.newPassword.getText().toString();
                                        new ChangePasswordTask().execute();

                                    } else {
                                        Toast toast = Toast.makeText(getContext(),
                                                "New Passwords doesn't match ",
                                                Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                } else {
                                    Toast toast = Toast.makeText(getContext(),
                                            "Old Password doesn't match",
                                            Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                            }
                        });


                    }
                });

                viewHolder.addNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.setContentView(R.layout.add_address_popup_profile);

                        viewHolder.houseNo = dialog.findViewById(R.id.house_no);
                        viewHolder.line1 = dialog.findViewById(R.id.line1);
                        viewHolder.city = dialog.findViewById(R.id.city);
                        viewHolder.state = dialog.findViewById(R.id.state);
                        viewHolder.zipCode = dialog.findViewById(R.id.zip_code);
                        viewHolder.addAddress = dialog.findViewById(R.id.add_address);
                        viewHolder.X = dialog.findViewById(R.id.txtclose);

                        viewHolder.X.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });


                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();


                        viewHolder.addAddress.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userAddressModel.setHouseNo(viewHolder.houseNo.getText().toString());
                                userAddressModel.setLine1(viewHolder.line1.getText().toString());
                                userAddressModel.setCity(viewHolder.city.getText().toString());
                                userAddressModel.setState(viewHolder.state.getText().toString());
                                userAddressModel.setZipCode(viewHolder.zipCode.getText().toString());

                                new AddAddressTask().execute();

                            }
                        });

                    }
                });


            }
        });
        return root;
    }

    public class DeleteAddressTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {


                for(int i = 0; i < userAddressModelArrayListSelected.size(); i++) {
                    String query = "UPDATE `address` SET `View_value` = 0 WHERE Address_id =  " + userAddressModelArrayListSelected.get(i).getAddressId();
                    PreparedStatement statement = connection.prepareStatement(query);
                    Log.e("Myquery = ", query);
                    statement.executeUpdate(query);
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



        }
    }

    public class ChangePasswordTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {


                    String query = "UPDATE `login_info` SET `password` = \""+ changedPassword + "\" WHERE Username =  \"" + user + "\"";
                    PreparedStatement statement = connection.prepareStatement(query);
                    Log.e("Myquery = ", query);
                    statement.executeUpdate(query);
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
            dialog.dismiss();

        }
    }

    public class AddAddressTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector mySqlConnector = new MySqlConnector();
            Connection connection = mySqlConnector.getMySqlConnection();

            try {

                String query = "INSERT INTO address (House_no, Line_1, City, State, Zip_code, User_id) VALUES (?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userAddressModel.getHouseNo());
                preparedStatement.setString(2, userAddressModel.getLine1());
                preparedStatement.setString(3, userAddressModel.getCity());
                preparedStatement.setString(4, userAddressModel.getState());
                preparedStatement.setString(5, userAddressModel.getZipCode());
                preparedStatement.setInt(6, userProfileModel.getUserId());

                preparedStatement.executeUpdate();


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

            dialog.dismiss();


        }
    }

    public class ViewHolder{

        public TextView username;
        public TextView name;
        public TextView contactNo;
        public TextView email;
        public ListView addressListView;
        public Button delete;
        public Button addNew;
        public TextView changePassword;
        public EditText houseNo;
        public EditText line1;
        public EditText city;
        public EditText state;
        public EditText zipCode;
        public TextView X;
        public Button addAddress;
        public EditText oldPassword;
        public EditText newPassword;
        public EditText confirmPassword;
        public Button changePasswordButton;

    }
}
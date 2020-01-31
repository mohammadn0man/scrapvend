package com.example.login.pickupui.pickupdetails;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.Models.PickupPersonProfileModel;
import com.example.login.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.login.MainActivity.user;

public class PickupDetailsFragment extends Fragment {

    private PickupPersonViewModel pickupPersonViewModel;
    Dialog dialog;
    String changedPassword;

    public PickupPersonProfileModel pickupPersonProfileModel=new PickupPersonProfileModel();
    public ViewHolder viewHolder = new ViewHolder();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pickupPersonViewModel =
                ViewModelProviders.of(this).get(PickupPersonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pickup_details, container, false);

        dialog = new Dialog(getContext());
        viewHolder.name=root.findViewById(R.id.name);
        viewHolder.contactNo=root.findViewById(R.id.contact_no);
        viewHolder.email=root.findViewById(R.id.email);
        viewHolder.changePassword=root.findViewById(R.id.change_password);
        viewHolder.address=root.findViewById(R.id.address);




        pickupPersonViewModel.getText().observe(this, new Observer<PickupPersonProfileModel>() {
            @Override
            public void onChanged(@Nullable PickupPersonProfileModel s) {
                viewHolder.username.setText(s.getUsername());
                viewHolder.name.setText(s.getName());
                viewHolder.contactNo.setText(s.getContactNo());
                viewHolder.email.setText(s.getEmail());

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

                        if(viewHolder.oldPassword.getText().toString().equals("aish")){
                            if(viewHolder.newPassword.getText().toString().equals(viewHolder.confirmPassword.getText().toString())){

                                changedPassword =viewHolder.newPassword.getText().toString();
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

        return root;
    }

    public class ChangePasswordTask extends AsyncTask<Void, Void, Void> {

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

    public class ViewHolder{

        public TextView username;
        public TextView name;
        public TextView contactNo;
        public TextView email;
        public TextView address;
        public TextView changePassword;
        public TextView X;
        public EditText oldPassword;
        public EditText newPassword;
        public EditText confirmPassword;
        public Button changePasswordButton;

    }

}
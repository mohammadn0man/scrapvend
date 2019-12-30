package com.example.scrapvend.ui.pickupperson;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPickupPerson extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageView itemImageView;
    Button chooseImage,addpickupperson;
    Uri imageUri;
    Bitmap bmp;

    private EditText nameEditText, usernameEditText,adhaarnunber,salary,contact,email;
    PickupPersonModel model = new PickupPersonModel();

    private static final int PICK_IMAGE = 100;
    private final String TAG = "MyAddItem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pickupperson);
        Log.d(TAG, "Inside addItem");
        itemImageView = (ImageView) findViewById(R.id.pickup_person_image);
        chooseImage = (Button) findViewById(R.id.choose_pickup_person_image);
        nameEditText =(EditText) findViewById(R.id.name);
        usernameEditText =(EditText) findViewById(R.id.username);
        adhaarnunber =(EditText) findViewById(R.id.adhaarnumber);
        salary =(EditText) findViewById(R.id.salary);
        email=(EditText) findViewById(R.id.email);
        contact=(EditText) findViewById(R.id.contact);





        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        addpickupperson = (Button) findViewById(R.id.add_pickup_person);
        addpickupperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertIntoDatabaseTask().execute();

            }
        });
    }

    private class InsertIntoDatabaseTask extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();

            try {

                Log.d(TAG, "Connection established");

                String name = nameEditText.getText().toString();
                String personemail = email.getText().toString();
                String contactnumber = contact.getText().toString();
                String uname=usernameEditText.getText().toString();
                String adhaar = adhaarnunber.getText().toString();
                String personsalary = salary.getText().toString();

                bmp = ((BitmapDrawable)itemImageView.getDrawable()).getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 0, bos);
                byte[] bArray = bos.toByteArray();


                Log.d(TAG, "retrieve = " + name );




                String query1="INSERT INTO `login_info`(`Username`, `Role`, `password`, `email`, `contact_no`) VALUES (?,?,?,?,?)";
                Log.d(TAG, "query = " + query1);
                PreparedStatement preparedStatement1 = conn.prepareStatement(query1);

                preparedStatement1.setString(1,uname);
                preparedStatement1.setString(2,"1");
                preparedStatement1.setString(3,"pickup");
                preparedStatement1.setString(4,personemail);
                preparedStatement1.setString(5,contactnumber);


                preparedStatement1.execute();
                Log.d(TAG, "query Execute ");
                String query = "INSERT INTO `pickup_person_details`(`Name`, `Aadhar_no`, `Salary`,  `Username`, `Person_image`) VALUES (?,?,?,?,?)";

                Log.d(TAG, "query = " + query );
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1,name);
                preparedStatement.setString(2,adhaar);
                preparedStatement.setString(3,personsalary);
                preparedStatement.setString(4, uname);
                preparedStatement.setBytes(5,bArray);


                Log.d(TAG, "query created : " + query);

                preparedStatement.execute();

                Log.d(TAG, "query executed");

            } catch (SQLException e) {
                e.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"inside finally");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){

            Toast.makeText(getApplicationContext(),model.getName() + " added successfully.",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Toast executed");
            finish();

        }

    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int request, int resultCode, Intent data){
        super.onActivityResult(request, resultCode, data);
        if(resultCode == RESULT_OK && request == PICK_IMAGE){
            imageUri = data.getData();
            itemImageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String str = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Intent intent = new Intent(this.getBaseContext(), PickuppersonFragment.class);
        startActivity(intent);
    }
}

package com.example.scrapvend.ui.pickupperson;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatePickupPerson extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private final String TAG = "MyUpdatePickupPerson";
    private static final int PICK_IMAGE = 100;
    private EditText personNameEditText, adhaarEditText,salaryEditText,ratingEditText;
    Uri imageUri;
    Button saveEditItem, deleteItem, editImage;
    Bitmap getItemImage;
    ImageView itemImageView;
    String getName, getRating, getadhaar, getsalary,getId;
    PickupPersonModel pickupPersonModel = new PickupPersonModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_pickupperson_details);

        personNameEditText = findViewById(R.id.editText2);
        adhaarEditText = findViewById(R.id.editText3);
        salaryEditText = findViewById(R.id.editText);
        ratingEditText = findViewById(R.id.editText4);
        saveEditItem = findViewById(R.id.button3);
        deleteItem = findViewById(R.id.button4);
        itemImageView = findViewById(R.id.item_image);

        //FETCHING ID OF THE LIST
        Bundle bundle;
        bundle = getIntent().getExtras();

        getName = bundle.getString("GETName");
        getRating = bundle.getString("GETRating");
        getsalary = bundle.getString("GETSalary");
        getadhaar = bundle.getString("GETAdhaar");
        getId = bundle.getString("GETId");
        pickupPersonModel.setId(getId);
        byte[] byteArray = getIntent().getByteArrayExtra("GETImage");
        getItemImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        Log.d(TAG, "Selected Item " + getName + getRating + getadhaar);
        personNameEditText.setText(getName);
        ratingEditText.setText(getRating);
        salaryEditText.setText(getsalary);
        adhaarEditText.setText(getId);
        itemImageView.setImageBitmap(getItemImage);

       //UPDATE BUTTON
        saveEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickupPersonModel.setName(personNameEditText.getText().toString());
                pickupPersonModel.setAadhar_no(adhaarEditText.getText().toString());
                pickupPersonModel.setRating(ratingEditText.getText().toString());
                pickupPersonModel.setSalary(salaryEditText.getText().toString());
//                pickupPersonModel.setItemImage(((BitmapDrawable)itemImageView.getDrawable()).getBitmap());
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                pickupPersonModel.getItemImage().compress(Bitmap.CompressFormat.JPEG, 0, bos);
//                pickupPersonModel.setByteImage(bos.toByteArray());
Log.e(TAG,"ad = "+ pickupPersonModel.getAadhar_no()+" id = "+pickupPersonModel.getId());
                pickupPersonModel.setBitmapImage(((BitmapDrawable)itemImageView.getDrawable()).getBitmap());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                pickupPersonModel.getBitmapImage().compress(Bitmap.CompressFormat.JPEG, 0, bos);
                pickupPersonModel.setByteImage(bos.toByteArray());
//                pickupPersonModel.setItemImage(((BitmapDrawable)itemImageView.getDrawable()).getBitmap());
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                pickupPersonModel.getItemImage().compress(Bitmap.CompressFormat.JPEG, 0, bos);
//                pickupPersonModel.setByteImage(bos.toByteArray());
Log.e(TAG,"ad = "+ pickupPersonModel.getAadhar_no()+" id = "+pickupPersonModel.getId());
                new UpdateItemData().execute();

            }
        });
        //EDIT BUTTON

        editImage = (Button) findViewById(R.id.button2);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        //DELETE BUTTON

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DeleteItemData().execute();

            }
        });
    }

    private class UpdateItemData extends AsyncTask<Void, Void, Void> {
        @SuppressLint("WrongThread")
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();

            try {


                Log.d(TAG, "Connection established inside update pickup person");

                String query = "UPDATE `pickup_person_details` SET `Name`=\""+pickupPersonModel.getName()+"\",`Aadhar_no`=\""+pickupPersonModel.getAadhar_no()+"\",`Salary`="+pickupPersonModel.getSalary()+",`Rating`=" + pickupPersonModel.getRating() + " ,`Person_image`=(?)  WHERE Pickup_person_id = " + pickupPersonModel.getId() + " ";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
//
                preparedStatement.setBytes(1, pickupPersonModel.getByteImage());

                Log.d(TAG, "query created : " + query);

                preparedStatement.execute();

                Log.d(TAG, "query executed");


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //finally block used to close resources
                Log.d(TAG, "inside finally");
//                Intent intent = new Intent(getApplicationContext(),PickuppersonFragment.class);
//                startActivity(intent);
                finish();
            }

            return null;

        }
    }


    private class DeleteItemData extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");
            MySqlConnector connection = new MySqlConnector();

            Connection conn = connection.getMySqlConnection();

            try {

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();
                String query = "UPDATE `pickup_person_details` SET `View_value` = 0 " + " WHERE Pickup_person_id = \"" + pickupPersonModel.getId() + "\"  ";

                Log.d(TAG, "query created : " + query);

                statement.executeUpdate(query);

                Log.d(TAG, "query executed");



            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "inside finally");
                Intent intent = new Intent(getBaseContext(), PickuppersonFragment.class);
                startActivity(intent);

            }

            return null;

        }
    }



    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        Log.d(TAG, "open profile");
    }

    @Override
    protected void onActivityResult(int request, int resultCode, Intent data){
        super.onActivityResult(request, resultCode, data);
        Log.d(TAG, "request ="+request);
        if(resultCode == RESULT_OK && request == PICK_IMAGE){
            imageUri = data.getData();
            Log.d(TAG, "imageUri = " + imageUri);
            itemImageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

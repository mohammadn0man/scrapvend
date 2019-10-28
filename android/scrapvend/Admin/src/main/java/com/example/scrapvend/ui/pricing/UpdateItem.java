package com.example.scrapvend.ui.pricing;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class UpdateItem  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MyAddItem";
    private static final int PICK_IMAGE = 100;
    private EditText itemNameEditText, itemRateEditText;
    Spinner itemSpinner;
    Uri imageUri;
    Button saveEditItem, deleteItem, editImage;
    Bitmap getItemImage;
    ImageView itemImageView;
    String getItemName, getItemRate, getItemMeasure, getItemId;
    PricingItemModel itemModel = new PricingItemModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_item_details);

        itemNameEditText = findViewById(R.id.editText2);
        itemRateEditText = findViewById(R.id.editText3);
        itemSpinner = findViewById(R.id.spinner_measure);
        saveEditItem = findViewById(R.id.button3);
        deleteItem = findViewById(R.id.button4);
        itemImageView = findViewById(R.id.item_image);
        itemSpinner = findViewById(R.id.spinner_measure);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.item_measure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(this);

        //FETCHING ID OF THE LIST
        Bundle bundle;
        bundle = getIntent().getExtras();

        getItemName = bundle.getString("GETName");
        getItemRate = bundle.getString("GETRate");
        getItemMeasure = bundle.getString("GETMeasure");
        getItemId = bundle.getString("GETId");
        byte[] byteArray = getIntent().getByteArrayExtra("GETImage");
        getItemImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        itemModel.setItemId(getItemId);


        Log.d(TAG, "Selected Item  = {}, {}, {}" + getItemName + getItemRate + getItemMeasure);
        itemNameEditText.setText(getItemName);
        itemRateEditText.setText(getItemRate);
        itemImageView.setImageBitmap(getItemImage);
        itemSpinner.post(new Runnable() {
            @Override
            public void run() {
                String itemMeasure[] = getResources().getStringArray(R.array.item_measure);
                Log.d(TAG, "array " + itemMeasure[0]);
                itemSpinner.setSelection(Arrays.asList(itemMeasure).indexOf(getItemMeasure));
                Log.d(TAG, "index = " + Arrays.asList(itemMeasure).indexOf(getItemMeasure));
            }
        });

        saveEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemModel.setItemName(itemNameEditText.getText().toString());
                itemModel.setItemMeasure(itemSpinner.getSelectedItem().toString());
                itemModel.setItemRate(itemRateEditText.getText().toString());
                itemModel.setItemImage(((BitmapDrawable)itemImageView.getDrawable()).getBitmap());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                itemModel.getItemImage().compress(Bitmap.CompressFormat.JPEG, 0, bos);
                itemModel.setByteImage(bos.toByteArray());

                new UpdateItemData().execute();
            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DeleteItemData().execute();

            }
        });

        editImage = (Button) findViewById(R.id.button2);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


    }

    private class UpdateItemData extends AsyncTask<Void, Void, Void> {
        @SuppressLint("WrongThread")
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();

                String query = "UPDATE `item_details` SET `Item_name`=\"" + itemModel.getItemName() +"\" ,`Item_rate`=" + itemModel.getItemRate() + ",`Item_measure`=\"" + itemModel.getItemMeasure() +"\" ,`Item_image`=(?) WHERE Item_id = " + itemModel.getItemId() + " ";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setBytes(1, itemModel.getByteImage());

                Log.d(TAG, "query created : " + query);

                preparedStatement.execute();
//                statement.executeUpdate(query);

                Log.d(TAG, "query executed");

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                Log.d(TAG, "inside finally");
                Intent intent = new Intent(getApplicationContext(),PricingFragment.class);
                startActivity(intent);

            }

            return null;

        }
    }


    private class DeleteItemData extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();
                String query = "UPDATE `item_details` SET `View_value` = 0 " + " WHERE Item_id = " + itemModel.getItemId() + " ";

                Log.d(TAG, "query created : " + query);

                statement.executeUpdate(query);

                Log.d(TAG, "query executed");

                conn.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources

                Log.d(TAG, "inside finally");
                Intent intent = new Intent(getBaseContext(),PricingFragment.class);
                startActivity(intent);

            }

            return null;

        }


    }



    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        Log.d(TAG, "open gallery");
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

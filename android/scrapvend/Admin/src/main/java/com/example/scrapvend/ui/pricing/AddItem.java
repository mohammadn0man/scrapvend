package com.example.scrapvend.ui.pricing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.MainActivity;
import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MyAddItem";
    ImageView itemImageView;
    Button chooseImage;
    Button addItem;
    Uri imageUri;
    Bitmap bmp;
    byte byteArray[];
    private static final int PICK_IMAGE = 100;
    private EditText itemNameEditText, itemRateEditText;
    Spinner itemSpinner;
    PricingItemModel itemModel = new PricingItemModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Log.d(TAG, "Inside addItem");
        itemImageView = (ImageView) findViewById(R.id.item_image);
        chooseImage = (Button) findViewById(R.id.choose_item_image);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        itemSpinner = findViewById(R.id.spinner_measure);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.item_measure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(this);
        itemNameEditText = (EditText) findViewById(R.id.item_name);
        itemRateEditText = (EditText) findViewById(R.id.item_rate);

        addItem = (Button) findViewById(R.id.add_item_submit);
        addItem.setOnClickListener(new View.OnClickListener() {
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

                Statement statement = conn.createStatement();

                String name = itemNameEditText.getText().toString();

                Log.d(TAG, "retrieve = " + name );

                bmp = ((BitmapDrawable)itemImageView.getDrawable()).getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 0, bos);
                byte[] bArray = bos.toByteArray();

                itemModel.setItemRate(itemRateEditText.getText().toString());
                itemModel.setItemMeasure(itemSpinner.getSelectedItem().toString());
                itemModel.setItemName(itemNameEditText.getText().toString());
                itemModel.setItemImage(bmp);
                itemModel.setByteImage(bArray);

                ResultSet resultSet = statement.executeQuery("select * from `item_details` where `Item_name`=\""+itemModel.getItemName()+"\"");
                String query;

                if(resultSet.next()){
                    query = "UPDATE `item_details` SET `Item_name`=(?) ,`Item_rate`=(?),`Item_measure`=(?) ,`Item_image`=(?),`View_value`=1 WHERE Item_name = \"" + itemModel.getItemName() + "\" ;";
                }else {
                    query = "INSERT INTO `item_details`(`Item_name`, `Item_rate`, `Item_measure`, `Item_image`) VALUES (?,?,?,?)";
                }


                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, itemModel.getItemName());
                preparedStatement.setString(2, itemModel.getItemRate());
                preparedStatement.setString(3,itemModel.getItemMeasure());
                preparedStatement.setBytes(4,itemModel.getByteImage());

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

            Toast.makeText(getApplicationContext(),itemModel.getItemName() + " added successfully.",Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this.getBaseContext(), PricingFragment.class);
        startActivity(intent);
    }

}

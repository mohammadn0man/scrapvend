package com.example.scrapvend.ui.pricing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.scrapvend.DatabaseConnect.MySqlConnector;
import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MyAddItem";
    ImageView itemImageView;
    Button chooseImage;
    Button addItem;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    private EditText itemNameEditText, itemRateEditText;
    Spinner itemSpinner;
    PricingItemModel itemModel = new PricingItemModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_details);
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

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "inside doInBackground");

            try {
                MySqlConnector connection = new MySqlConnector();

                Connection conn = connection.getMySqlConnection();

                Log.d(TAG, "Connection established");

                Statement statement = conn.createStatement();

                String name = itemNameEditText.getText().toString();

                Log.d(TAG, "retrive" + name + itemRateEditText.getText().toString());

                itemModel.setItemRate(itemRateEditText.getText().toString());
                itemModel.setItemMeasure(itemSpinner.getSelectedItem().toString());
                itemModel.setItemName(itemNameEditText.getText().toString());

                String query = "INSERT INTO `item_details`(`Item_name`, `Item_rate`, `Item_measure`) VALUES (\'" + itemModel.getItemName() + "\' ," + itemModel.getItemRate() + ", \'" + itemModel.getItemMeasure() + "\')";

                Log.d(TAG, "query created : " + query);

                statement.executeUpdate(query);

                Log.d(TAG, "query executed");

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                Log.d(TAG,"inside finally");
            }

            return null;
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

    }
}

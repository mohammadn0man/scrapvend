package com.example.scrapvend.ui.pricing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scrapvend.R;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView imageView;
    Button chooseImage;
    Button addItem;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        imageView = (ImageView) findViewById(R.id.item_image);
        chooseImage = (Button) findViewById(R.id.choose_item_image);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        Spinner spinner = findViewById(R.id.spinner_measure);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.item_measure, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



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
            imageView.setImageURI(imageUri);
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

package com.example.scrapvend.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scrapvend.R;

class DetailView extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private final String TAG = "myActivity";

    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "inside onCreate DetailView");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_list_item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

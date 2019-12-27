package com.example.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.homepage);
        getSupportActionBar().hide();
    }
}

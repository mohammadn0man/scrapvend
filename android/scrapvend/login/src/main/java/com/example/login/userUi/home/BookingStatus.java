package com.example.login.userUi.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.login.userUi.bookingstatus.BookingStatusFragment;

public class BookingStatus extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BookingStatusFragment.class);
        startActivity(intent);
    }
}

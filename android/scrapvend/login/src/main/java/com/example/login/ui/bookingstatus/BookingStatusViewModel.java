package com.example.login.ui.bookingstatus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookingStatusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BookingStatusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
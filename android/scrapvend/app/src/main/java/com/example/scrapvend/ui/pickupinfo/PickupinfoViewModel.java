package com.example.scrapvend.ui.pickupinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PickupinfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PickupinfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
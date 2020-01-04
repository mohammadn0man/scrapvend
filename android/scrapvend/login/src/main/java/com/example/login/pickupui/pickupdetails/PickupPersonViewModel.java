package com.example.login.pickupui.pickupdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PickupPersonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PickupPersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
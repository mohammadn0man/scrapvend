package com.example.login.pickupui.LeaveRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeaveRequestViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeaveRequestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.scrapvend.ui.pickupperson;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PickuppersonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PickuppersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
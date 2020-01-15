package com.example.login.userUi.logout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login.logout;

public class LogoutFragment extends Fragment {

    private static String tag="sp";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = null;
        Log.d(tag,"inside logout fragment");
        Intent intent = new Intent(getActivity(), logout.class);
        startActivity(intent);
        return null;
    }
}
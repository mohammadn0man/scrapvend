package com.example.login.userUi.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.login.Models.UserAddressModel;
import com.example.login.R;

import java.util.ArrayList;

public class AddressListAdapter extends BaseAdapter {

    Context mContext;
    int resource;
    LayoutInflater mInflater;
    ArrayList<UserAddressModel> mList = new ArrayList<>();
    SparseBooleanArray mSparseBooleanArray;

    public AddressListAdapter(Context context, int resource, ArrayList<UserAddressModel> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mList = list;
        this.resource = resource;
        mSparseBooleanArray = new SparseBooleanArray();
    }

    public ArrayList<UserAddressModel> getCheckedItems() {
        ArrayList<UserAddressModel> mTempArry = new ArrayList<UserAddressModel>();
        for(int i=0;i<mList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mTempArry.add(mList.get(i));
            }
        }
        return mTempArry;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = mInflater.inflate(R.layout.address_list_view_layout, null);
            UserAddressModel userAddressModel = (UserAddressModel) getItem(i);
            TextView address = (TextView) view.findViewById(R.id.address);
            CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.checkbox_address);
            mCheckBox.setTag(i);
            mCheckBox.setChecked(mSparseBooleanArray.get(i));
            mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
            address.setText(userAddressModel.getHouseNo()+" "+userAddressModel.getLine1()+", "+userAddressModel.getCity()+"\n" +
                    "\t "+userAddressModel.getState()+" ("+userAddressModel.getZipCode()+") ");
        }


        return view;
    }

    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {




        @Override


        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }


    };
}

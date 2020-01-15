package com.example.login.userUi.Adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.example.login.userUi.Models.ItemListModel;

import java.util.ArrayList;


public class MultiSelectionAdapter<T> extends BaseAdapter{
    Context mContext;
    LayoutInflater mInflater;
    ArrayList<ItemListModel> mList;
    SparseBooleanArray mSparseBooleanArray;
    int resource;
    public MultiSelectionAdapter(Context context,int resource, ArrayList<ItemListModel> list) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mSparseBooleanArray = new SparseBooleanArray();
        mList = new ArrayList<ItemListModel>();
        this.mList = list;
        this.resource=resource;
    }
    public ArrayList<ItemListModel> getCheckedItems() {
        ArrayList<ItemListModel> mTempArry = new ArrayList<ItemListModel>();
        for(int i=0;i<mList.size();i++) {
            if(mSparseBooleanArray.get(i)) {
                mTempArry.add(mList.get(i));
            }
        }
        return mTempArry;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.request_pickup_list_item, null);
        }
        ItemListModel itemListModel = (ItemListModel) getItem(position);
        TextView itemprice = (TextView) convertView.findViewById(R.id.itemprice);
        ImageView itemimage = (ImageView) convertView.findViewById(R.id.itemimage);
        TextView itemname = (TextView) convertView.findViewById(R.id.itemname);
        itemimage.setImageBitmap(itemListModel.getItemImage());
        itemprice.setText(itemListModel.getItemMeasure());
        itemname.setText(mList.get(position).toString());
        CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.chkEnable);
        mCheckBox.setTag(position);
        mCheckBox.setChecked(mSparseBooleanArray.get(position));
        mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
        return convertView;
    }
    OnCheckedChangeListener mCheckedChangeListener = new OnCheckedChangeListener() {




        @Override


        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }


    };
}



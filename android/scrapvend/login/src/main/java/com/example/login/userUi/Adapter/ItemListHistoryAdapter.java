package com.example.login.userUi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.login.Models.HistoryDetails;
import com.example.login.Models.ItemQuantityModel;
import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemListHistoryAdapter extends ArrayAdapter<ItemQuantityModel> {

    Context context;
    int resource;
    ArrayList<ItemQuantityModel> itemQuantityModelArrayList;

    public ItemListHistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemQuantityModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.itemQuantityModelArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.history_item_list_listview_layout, null);


        TextView itemName = (TextView) view.findViewById(R.id.item_name);
        TextView itemQty = (TextView) view.findViewById(R.id.item_qty);

        ItemQuantityModel itemQuantityModel = getItem(position);

        itemName.setText(itemQuantityModel.getItemName());
        itemQty.setText(itemQuantityModel.getItemqty());


        return view;
    }
}

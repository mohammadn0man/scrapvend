package com.example.login.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Models.ItemQuantityModel;
import com.example.login.R;

import java.util.ArrayList;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

public class ItemQuantityAdapter extends BaseAdapter
{

    EditText editTextqty;
    ItemQuantityModel employee;
    public static boolean flag = false;
    public static ArrayList<ItemQuantityModel> itemlist;
    private LayoutInflater inflater;
    Context context;

    public ItemQuantityAdapter(@NonNull Context context, ArrayList<ItemQuantityModel> itemlist) {

        this.itemlist =itemlist;
        this.context=context;

    }

    @Override
    public int getCount() {
         return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            notifyDataSetChanged();
            convertView = inflater.inflate(R.layout.itemquantitylist, null, true);
             employee=(ItemQuantityModel) getItem(position);
            TextView textViewName = convertView.findViewById(R.id.textViewItemName);
             editTextqty = convertView.findViewById(R.id.editItemQuantity);
            TextView textViewRate = convertView.findViewById(R.id.textViewItemRate);
            convertView.setTag(editTextqty);
            textViewRate.setText(employee.getItemRate());
            textViewName.setText(employee.getitemName());

        } else {
            editTextqty = (EditText) convertView.getTag();
        }
        editTextqty.setText(itemlist.get(position).getItemqty());
//        editTextqty.setId(position);
        editTextqty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemlist.get(position).setItemqty(s.toString());
                Log.d("MyItemAdapter", s +"s, p"+position + " -- "+count+"c, m"+itemlist.get(position).getItemqty() + ", qty " + employee.getItemqty());

                employee.setItemqty(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
}
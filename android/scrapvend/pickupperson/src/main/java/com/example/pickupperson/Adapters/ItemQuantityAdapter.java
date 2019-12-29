package com.example.pickupperson.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.ItemQuantityModel;
import com.example.pickupperson.R;

import java.sql.Blob;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ItemQuantityAdapter extends ArrayAdapter<ItemQuantityModel> {

    public static ArrayList<ItemQuantityModel> itemlist;
  //  private LayoutInflater inflater;
  //  public  ArrayList myItems=new ArrayList();
    Context context;
    int textViewResourceId;
    @NonNull
    @Override
    public int getCount() {return super.getCount();}


    public ItemQuantityAdapter(@NonNull Context context, int textViewResourceId, ArrayList<ItemQuantityModel> itemlist) {
        super(context,0, itemlist);
        this.itemlist =itemlist;
        this.context=context;
        this.textViewResourceId=textViewResourceId;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.itemquantitylist, null);

        ItemQuantityModel employee=getItem(position);
        TextView textViewName = v.findViewById(R.id.textViewItemName);
        textViewName.setText(employee.getitemName());
//        EditText editTextqty = v.findViewById(R.id.editItemQuantity);
        final TextView textViewRate = v.findViewById(R.id.textViewItemRate);
        textViewRate.setText(employee.getItemRate());

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
//            convertView = inflater.inflate(R.layout.itemquantitylist, null, true);

            holder.editText = (EditText) v.findViewById(R.id.editItemQuantity);
//            holder.editText.setText(employee.getEditTextValue());

            v.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)v.getTag();
        }

        holder.editText.setText(itemlist.get(position).getEditTextValue());

        holder.editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemlist.get(position).setEditTextValue(String.valueOf(Integer.parseInt(holder.editText.getText().toString()) * Integer.parseInt(textViewRate.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        //   ImageView img = v.findViewById(R.id.imageView3);
     /*   Blob bp = employee.getItemImage();
        // CONVERTING BLOB IMAGE INTO BITMAP IMAGE
        Bitmap btm;
        try {
            Log.d(TAG, "back to hello2" + bp.length());
            int blobLength = (int) bp.length();
            Log.d(TAG, "poornima" + (int) bp.length());
            byte[] blobAsBytes = bp.getBytes(1, blobLength);
            btm = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
            img.setImageBitmap(btm);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
                return v;
    }


    private class ViewHolder {

        protected EditText editText;

    }

}

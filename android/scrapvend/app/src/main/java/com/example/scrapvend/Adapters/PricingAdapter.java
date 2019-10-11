package com.example.scrapvend.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.scrapvend.Models.Pricing_ItemModel;
import com.example.scrapvend.R;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PricingAdapter extends ArrayAdapter<Pricing_ItemModel> {
    Context context;
    int resourse;
    ArrayList<Pricing_ItemModel> pricing;

    public PricingAdapter(@NonNull Context context, int resource, ArrayList<Pricing_ItemModel> pricing) {

        super(context, resource, pricing);
        this.context = context;
        this.resourse = resource;
        this.pricing = pricing;
    }

    public int getCount() {
        return super.getCount();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.pricing_list, null);

        Pricing_ItemModel pricingModel = getItem(position);
        TextView name = v.findViewById(R.id.textView12);
        TextView rate = v.findViewById(R.id.textView13);
        TextView measure = v.findViewById(R.id.textView14);
        ImageView img = v.findViewById(R.id.imageView3);
        name.setText(pricingModel.getItemName());
        rate.setText(pricingModel.getItemRate());
        measure.setText(pricingModel.getItemMeasure());
        Blob bp = pricingModel.getItemImage();
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
        }
        return v;
    }
}

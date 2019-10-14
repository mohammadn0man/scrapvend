package com.example.scrapvend.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.scrapvend.Models.PricingItemModel;
import com.example.scrapvend.R;

import java.sql.Blob;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PricingAdapter extends ArrayAdapter<PricingItemModel> {
    Context context;
    int resourse;
    ArrayList<PricingItemModel> pricing;

    public PricingAdapter(@NonNull Context context, int resource, ArrayList<PricingItemModel> pricing) {

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

        PricingItemModel pricingModel = getItem(position);
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
            int blobLength = (int) bp.length();
            byte[] blobAsBytes = bp.getBytes(1, blobLength);
            btm = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
            img.setImageBitmap(btm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
}

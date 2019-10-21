package com.example.scrapvend.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.scrapvend.Models.PickupPersonModel;
import com.example.scrapvend.R;

import java.sql.Blob;
import java.util.ArrayList;

public class PickuppersonAdapter extends ArrayAdapter<PickupPersonModel> {
    Context context;
    int resourse;
    ArrayList<PickupPersonModel> pickupperson;
    public PickuppersonAdapter(@NonNull Context context, int resource, ArrayList<PickupPersonModel> pickupperson) {

        super(context,resource,pickupperson);
        this.context=context;
        this.resourse=resource;
        this.pickupperson= pickupperson;
    }
    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.add_item_pickupperson, null);

        PickupPersonModel pickuppersonModel = getItem(position);
        TextView name = v.findViewById(R.id.textView9);
        TextView adhar = v.findViewById(R.id.textView10);
        TextView id = v.findViewById(R.id.textView11);
        ImageView img = v.findViewById(R.id.imageView2);

        name.setText(pickuppersonModel.getName());
        adhar.setText(pickuppersonModel.getAdhaar_no());
        id.setText(pickuppersonModel.getId());

        Blob bp = pickuppersonModel.getItemImage();
        // CONVERTING BLOB IMAGE INTO BITMAP IMAGE
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

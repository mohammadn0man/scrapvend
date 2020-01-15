package com.example.login.userUi.Adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;

public class CustomGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private  static String[] imagename,itemrate;
    private static Bitmap[] Imageid;
    private final String TAG = "grid view";

    public CustomGridViewAdapter(Context c, String[] imagename, Bitmap[] Imageid,String[] itemrate) {
        mContext = c;
        this.Imageid = Imageid;
        this.imagename = imagename;
        this.itemrate=itemrate;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagename.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            Log.d(TAG, "grid view "+imagename[position]);
            Log.d(TAG, "grid view "+imagename.length);

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_list_grid_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.item_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.item_image);
            TextView itemRate =(TextView) grid.findViewById(R.id.item_price);
            textView.setText(imagename[position]);
            itemRate.setText(itemrate[position]);
            imageView.setImageBitmap(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}

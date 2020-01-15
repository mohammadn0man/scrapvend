package com.example.login.userUi.home;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.login.DatabaseConnection.MySqlConnector;
import com.example.login.R;
import com.example.login.userUi.Adapter.CustomGridViewAdapter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.login.MainActivity.user;

public class ItemListGirdView extends Activity {
    GridView grid=null;
    private final String TAG = "sp";
    static String[] image_name,item_rate ;
    static Bitmap[] imageId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        new getdata().execute();

//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(ItemListGirdView.this, "You Clicked at " + image_name[+ position], Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }
    private class getdata extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MySqlConnector connection = new MySqlConnector();
            Connection conn = connection.getMySqlConnection();

            try {
                Log.d(TAG, "inside contact fragment");
                //FETCHING DETAILS


                Log.d(TAG, "message and subject = ");

                Statement statement = conn.createStatement();
                Log.d(TAG, "select Item_name,Item_image from item_details");
                ResultSet rs = statement.executeQuery("select COUNT(*)  FROM item_details Where View_value=1");
                int i=0;
                rs.next();
                int count=rs.getInt(1);
                Log.d(TAG,"count ="+count);
                image_name=new String[count];
                item_rate=new String[count];
                imageId=new Bitmap[count];
                ResultSet results = statement.executeQuery("select Item_name,Item_image,Item_rate,Item_measure from item_details Where View_value=1");
                while(results.next()) {
                    image_name[i]=results.getString(1);
                    item_rate[i]=results.getString(3)+"\\"+results.getString(4);

                    Log.d(TAG, "item name " + results.getString(1));
                    Blob bp = results.getBlob(2);
                    Bitmap btm = null;
                    try {
                        int blobLength = (int) bp.length();
                        byte[] blobAsBytes = bp.getBytes(1, blobLength);
                        btm = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    imageId[i]=btm;
                    Log.d(TAG, "item image = "+btm);
                    i++;
                }
                Log.d(TAG, "query Execute ");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    Log.d(TAG, "user name = " + user);
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            for(int j=0;j<1;j++)
//                Log.d(TAG, "item name " +image_name[j]);
            CustomGridViewAdapter adapter = new CustomGridViewAdapter(getApplicationContext(), image_name, imageId,item_rate);
            grid=(GridView)findViewById(R.id.imagegrid);
            grid.setAdapter(adapter);

//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);

        }

    }
}

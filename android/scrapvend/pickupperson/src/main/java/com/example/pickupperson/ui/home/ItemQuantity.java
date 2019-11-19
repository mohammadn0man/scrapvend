package com.example.pickupperson.ui.home;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pickupperson.Adapters.DetailsAdapter;
import com.example.pickupperson.Adapters.ItemQuantityAdapter;
import com.example.pickupperson.Models.Details;
import com.example.pickupperson.Models.ItemQuantityModel;
import com.example.pickupperson.R;
import com.example.pickupperson.ui.MySQLConnector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
public class ItemQuantity extends AppCompatActivity implements View.OnClickListener {
   // private final String TAG = "MyDB";
    ListView listView;
    ItemQuantityModel pmodel;
    ItemQuantityAdapter padapter;
    Context context;
    ImageView img1;
    TextView textViewName, textViewPrice;
    EditText editQuantity;
    Button okButton;
    String totalAmount;
    int value,i;
    private final String TAG = "MyDBPage3";
    ArrayList<ItemQuantityModel> arrayOfEmp = new ArrayList<>();

    private HomeViewModel homeViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemquantity);

        //final View root;
        Log.d(TAG, "Hello from Home");
        listView = (ListView) findViewById(R.id.itemListView);
        listView.setItemsCanFocus(true);
        textViewName = (TextView)findViewById(R.id.textViewItemName);
        textViewPrice = (TextView)findViewById(R.id.textViewItemRate);
        editQuantity=findViewById(R.id.editItemQuantity);
        okButton=findViewById(R.id.okButton);

        img1 = (ImageView)findViewById(R.id.imageView);
        new task().execute();
        context = this.getApplicationContext();
        Log.d(TAG, "before intent in home");

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                View root;
                EditText e;
                TextView t;
                value = 0;

//                padapter.notifyDataSetChanged();
                listView = (ListView) ItemQuantity.this.findViewById(R.id.itemListView);
                Log.d(TAG, String.valueOf(listView.getCount()));
                for(i=0;i<listView.getCount();i++){

                    Log.d(TAG, "msg:value = " + i);
//                    root = listView.getAdapter().getView(i,null,null);
                    root=listView.getChildAt(i);
                    Log.d(TAG, String.valueOf(root));
                    e = root.findViewById(R.id.editItemQuantity);
                    t = root.findViewById(R.id.textViewItemRate);
                    value+=(Integer.parseInt(e.getText().toString())*Integer.parseInt(t.getText().toString()));
                    Log.d(TAG, e.getText().toString());
                }
                totalAmount=String.valueOf(value);
                Log.d(TAG, totalAmount);
//                Intent i = new Intent(ItemQuantity.this,DetailedPickupInfo.class);
//                Log.d(TAG, "intent sent");
//                i.putExtra("TotalAmount", totalAmount);
//                startActivity(i);


                Intent intent=new Intent();
                intent.putExtra("TotalAmount", totalAmount);
                setResult(2,intent);
                finish();//finishing activity

            }
        });

    }

    @Override
    public void onClick(View v) {

    }


    /*public class ItemQuantity extends Fragment  {
        // private final String TAG = "MyDB";
        ListView listView;
        ItemQuantityModel pmodel;
        ItemQuantityAdapter padapter;
        Context context;
        ImageView img1;
        TextView textViewName, textViewPrice;
        EditText editQuantity;
        Button okButton;
        String totalAmount;
        int value,i;
        private final String TAG = "MyDBPage3";
        ItemQuantityAdapter adapter;
        ItemQuantityModel details;
        ArrayList<ItemQuantityModel> arrayOfEmp = new ArrayList<>();
        private HomeViewModel homeViewModel;

        public View onCreateView(@NonNull final LayoutInflater inflater,
                                 final ViewGroup container, Bundle savedInstanceState) {
            homeViewModel =
                    ViewModelProviders.of(this).get(HomeViewModel.class);
            final View root = inflater.inflate(R.layout.itemquantity, container, false);

            listView = (ListView)root.findViewById(R.id.itemListView);
            textViewName = (TextView) root.findViewById(R.id.textViewItemName);
            textViewPrice = (TextView)root.findViewById(R.id.textViewItemRate);
            editQuantity = root.findViewById(R.id.editItemQuantity);
            okButton = root.findViewById(R.id.okButton);
            img1 = (ImageView) root.findViewById(R.id.imageView);
            new task().execute();
            context = this.getContext();
            Log.d(TAG, "before intent in home");

            okButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    View root=inflater.inflate(R.layout.itemquantity, container, false);
                    ;
                    listView = (ListView) root.findViewById(R.id.itemListView);
                    EditText e;
                    TextView t;
                    value = 0;
                    Log.d(TAG, String.valueOf(listView.getCount()));
                    for (i = 0; i < listView.getCount(); i++) {
                        Log.d(TAG, String.valueOf(listView.getChildAt(i)));
                        Log.d(TAG, "msg:value = " + i);

                        e = listView.getChildAt(i).findViewById(R.id.editItemQuantity);
                        t = listView.getChildAt(i).findViewById(R.id.textViewItemRate);
                        value += (Integer.parseInt(e.getText().toString()) * Integer.parseInt(t.getText().toString()));
                        Log.d(TAG, String.valueOf(value));
                    }
                    totalAmount = String.valueOf(value);
                    Log.d(TAG, totalAmount);
    //                Intent i = new Intent(ItemQuantity.this,DetailedPickupInfo.class);
    //                Log.d(TAG, "intent sent");
    //                i.putExtra("TotalAmount", totalAmount);
    //                startActivity(i);


                    Intent intent = new Intent();
                    intent.putExtra("TotalAmount", totalAmount);
                    //setResult(2, intent);
                    //finish();//finishing activity

                }
            });

            return root;

        }

     */
        class task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                MySQLConnector connection = new MySQLConnector();

                Connection conn = connection.getMySqlConnection();
                Statement statement = conn.createStatement();

                int value=1;
                String query ="Select * from item_details";
                        Log.d(TAG, query);
                ResultSet results = statement.executeQuery(query);
                Log.d(TAG, "query executed");
                while (results.next()) {
                    Log.d(TAG, results.getString("Item_name") + results.getString("Item_rate"));
                    pmodel = new ItemQuantityModel(results.getString("Item_name"), results.getString("Item_rate"));
//                    pmodel = new ItemQuantityModel(results.getString("Item_name"), results.getString("Item_rate"));
                    pmodel.setItemqty("10");
                    arrayOfEmp.add(pmodel);
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {

                padapter = new ItemQuantityAdapter(context, R.layout.itemquantitylist, arrayOfEmp);

            listView.setAdapter(padapter);

            super.onPostExecute(aVoid);
        }
    }
}



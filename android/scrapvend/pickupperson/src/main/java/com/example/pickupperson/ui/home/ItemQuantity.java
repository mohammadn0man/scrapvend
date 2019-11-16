package com.example.pickupperson.ui.home;
import android.content.Context;
import android.content.Intent;
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
public class ItemQuantity extends AppCompatActivity {
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
    private final String TAG = "MyDBPage3";
    ItemQuantityAdapter adapter;
    ItemQuantityModel details;
    ArrayList<ItemQuantityModel> arrayOfEmp = new ArrayList<>();

    private HomeViewModel homeViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemquantity);

        //final View root;
        Log.d(TAG, "Hello from Home");
        listView = (ListView) findViewById(R.id.itemListView);
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
                int value;
                value = 0;
                for(int i=0;i<listView.getCount();i++)
                {
                    root=listView.getChildAt(i);
                    e=root.findViewById(R.id.editItemQuantity);
                    t=(TextView)root.findViewById(R.id.textViewItemRate);
                    value=(Integer.parseInt(e.getText().toString())*Integer.parseInt(t.getText().toString()));
                }
                totalAmount=String.valueOf(value);
                Intent i = new Intent(ItemQuantity.this,DetailedPickupInfo.class);
                Log.d(TAG, "intent sent");
                i.putExtra("ListViewClickedValue", totalAmount);
                startActivity(i);

            }
        });

  /*  public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "item activity");
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.itemquantity, container, false);

        listView = (ListView)findViewById(R.id.itemListView);
        textViewName = (TextView)findViewById(R.id.textViewItemName);
        textViewPrice = (TextView)findViewById(R.id.textViewItemPrice);
        editQuantity=findViewById(R.id.editItemQuantity);
        img1 = (ImageView)findViewById(R.id.imageView);

        new task().execute();
        Log.d(TAG, "back to oncreate again");
       context = this.getContext();
        return root;
    }*/}
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



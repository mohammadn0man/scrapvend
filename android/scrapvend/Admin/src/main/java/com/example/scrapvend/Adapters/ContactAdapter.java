package com.example.scrapvend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.scrapvend.Models.ContactModel;
import com.example.scrapvend.R;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<ContactModel> {
    Context context;
    int resourse;
    ArrayList<ContactModel> contact;
    public ContactAdapter(@NonNull Context context, int resource, ArrayList<ContactModel> contact) {

        super(context,resource,contact);
        this.context=context;
        this.resourse=resource;
        this.contact= contact;
    }
    public int getCount(){return super.getCount();}
    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.contact_list_layout, null);

        ContactModel contactModel = getItem(position);
        TextView name = v.findViewById(R.id.author_name);
        TextView subject = v.findViewById(R.id.subject);


        name.setText(contactModel.getName());
        subject.setText(contactModel.getSubject());



        return v;
    }
}

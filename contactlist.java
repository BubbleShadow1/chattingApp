package com.androidapp.whatsappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class contactlist extends AppCompatActivity {

    ArrayList<contacts> contactList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contactlist);
        recyclerView=findViewById(R.id.recyclerview);

        // Retrieve the contactList (you can pass it from the previous activity)
        contactList = getIntent().getParcelableArrayListExtra("contacts");
        recyclerView=findViewById(R.id.recyclerview);


        cutomAdapterforRVandcontacts customAdapter = new cutomAdapterforRVandcontacts(contactList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);

    }
}
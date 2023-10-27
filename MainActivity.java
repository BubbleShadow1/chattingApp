package com.androidapp.whatsappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int READ_CONTACTS_PERMISSION_REQUEST = 1;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    ArrayList<contacts> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        customAdapter customadapter = new customAdapter(getSupportFragmentManager());
        viewPager.setAdapter(customadapter);

        tabLayout.setupWithViewPager(viewPager);
        toolbar.setTitle("WhatsApp");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION_REQUEST);
                } else {
                    fetchContacts();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void fetchContacts() {
        contactList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(new contacts(name, phoneNumber));
            }
            cursor.close();
        }

        // Pass contactList to the next activity
        Intent intent = new Intent(MainActivity.this, contactlist.class);
        intent.putParcelableArrayListExtra("contacts", contactList);
        startActivity(intent);
    }
}

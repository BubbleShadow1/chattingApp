package com.androidapp.whatsappproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidapp.whatsappproject.databinding.ActivityChatting1Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class chatting1 extends AppCompatActivity {
    EditText editText;
    ImageButton button;

    Toolbar toolbar;

    ArrayList<contacts> cl = new ArrayList<>();
    ActivityChatting1Binding binding;
    String t,num;

    RecyclerView recyclerView;
    String Receiveruid;
    String senderid;
    DatabaseReference databaseReferencesender;
    DatabaseReference databaseReferencereciever;
    String receiverroom, senderroom;
    MessageAdapter messageAdapter;
    List<String> savedMessages; // To store retrieved messages from SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatting1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        button = binding.sendbt;
        editText = binding.editTextText;
        toolbar = binding.toolbarforchat;
        recyclerView = binding.recycleviewfortexttype;
        t = getIntent().getStringExtra("NAME");
        num = getIntent().getStringExtra("CONT");
        cl = getIntent().getParcelableArrayListExtra("NUMBER");
        toolbar.setTitle(t);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("My", Context.MODE_PRIVATE);

        senderid = "+91" + sharedPreferences.getString("phone", "NULL") + "@@" + getIntent().getStringExtra("CONT");
        Receiveruid = getIntent().getStringExtra("CONT") + "@@" + "+91" + sharedPreferences.getString("phone", "NULL");
        senderroom = senderid;
        receiverroom = Receiveruid;
        messageAdapter = new MessageAdapter(this);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (senderroom != null && !senderroom.isEmpty()) {
            databaseReferencesender = FirebaseDatabase.getInstance().getReference().child("chats").child(senderroom);
        }

        if (receiverroom != null && !receiverroom.isEmpty()) {
            databaseReferencereciever = FirebaseDatabase.getInstance().getReference().child("chats").child(receiverroom);
        }

        // Retrieve saved messages from shared preferences
        savedMessages = MessageStorageManager.getMessages(this, num);
        // Display saved messages
        for (String savedMessage : savedMessages) {
            messages1 savedMessage1 = new messages1(UUID.randomUUID().toString(), Receiveruid, savedMessage);
            messageAdapter.add(savedMessage1);
        }


        Toast.makeText(this, "num:"+num, Toast.LENGTH_SHORT).show();




        databaseReferencereciever.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageAdapter.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    messages1 messages1 = dataSnapshot.getValue(messages1.class);
                    messageAdapter.add(messages1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                if (message.trim().length() > 0) {
                    sendMessage(message);
                }
            }
        });






    }

    private void sendMessage(String message) {
        String messageid = UUID.randomUUID().toString();
        messages1 messages1 = new messages1(messageid, FirebaseAuth.getInstance().getUid(), message);
        messageAdapter.add(messages1);
        databaseReferencesender.child(messageid).setValue(messages1);

        // Save the message in shared preferences
        savedMessages.add(message);
        MessageStorageManager.saveMessages(this, savedMessages, num);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuforchat, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

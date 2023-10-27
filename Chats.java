package com.androidapp.whatsappproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Chats extends Fragment {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> chats = new ArrayList<>();
    Integer image[] = {R.drawable.premchand, R.drawable.sumitra, R.drawable.ramdhari};
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = rootView.findViewById(R.id.recycler);
//        updateRecyclerView();
//
//        // Initialize SharedPreferences
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("My", Context.MODE_PRIVATE);
//
//        // Retrieve data from SharedPreferences
//        String name1 = sharedPreferences.getString("us", "Default Name");
//        String contact = sharedPreferences.getString("co", "Default Contact");
//
//        // Add the retrieved data to the ArrayList
//        name.add(name1);
//        chats.add(contact);

//databaseReference= FirebaseDatabase.getInstance().getReference("users");
//databaseReference.addValueEventListener(new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError error) {
//
//    }
//});


        // Add sample data
        name.add("Name1");
        chats.add("Chat1");
        name.add("Name2");
        chats.add("Chat2");
        name.add("Name3");
        chats.add("Chat3");

        customAdapterforRecyclerView customAdapter = new customAdapterforRecyclerView(image, name, chats, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(customAdapter);

        return rootView;
    }
//    private void updateRecyclerView() {
//        name.clear();
//        chats.clear();
//
//        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("My", Context.MODE_PRIVATE);
//        String name1 = sharedPreferences.getString("us", "null");
//        String contact = sharedPreferences.getString("co", "null");
//
//        // Add data from SharedPreferences
//        name.add(name1);
//        chats.add(contact);
//
//        // Notify the adapter that the data has changed
//        if (recyclerView.getAdapter() != null) {
//            recyclerView.getAdapter().notifyDataSetChanged();
//        }
//    }
}

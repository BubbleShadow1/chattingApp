package com.androidapp.whatsappproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class cutomAdapterforRVandcontacts extends RecyclerView.Adapter<cutomAdapterforRVandcontacts.myviewholder> {

    public cutomAdapterforRVandcontacts(ArrayList<contacts> contactList,Context context) {
        this.contactList = contactList;
        this.context=context;
    }

    private ArrayList<contacts> contactList;
    private Context context;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayoutforcontact, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        contacts contact = contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhoneNumber.setText(contact.getPhoneNumber());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("My", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("us", contact.getName());
//                editor.putString("co", contact.getPhoneNumber());
//                editor.apply();
                Intent ii=new Intent();
                String phone=ii.getStringExtra("phone");
                    Intent i = new Intent(context, chatting1.class);
                    i.putExtra("NAME", contact.getName());
                    i.putExtra("CONT", contact.getPhoneNumber());
                    i.putExtra("phone",phone);
                    i.putParcelableArrayListExtra("NUMBER", contactList);
                    context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }



    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView contactName;
        TextView contactPhoneNumber;
        CardView cardView;


        public myviewholder(View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactname);
            contactPhoneNumber = itemView.findViewById(R.id.contactnumber);
            cardView=itemView.findViewById(R.id.cardview2);
        }
    }



}

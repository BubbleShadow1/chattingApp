package com.androidapp.whatsappproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class customAdapterforRecyclerView extends RecyclerView.Adapter<customAdapterforRecyclerView.myviewholder> {

    Toolbar toolbar;
    public customAdapterforRecyclerView(Integer[] images, ArrayList<String> names, ArrayList<String>chats, Context context) {
        this.images = images;
        this.names = names;
        this.chats = chats;
        this.context = context;
    }

    Integer images[];
   static ArrayList<String> names;

    usermodel usermodel;
   static ArrayList<String> chats;
    Context context;
    ViewPager viewPager;

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.customlayout,parent,false);
        myviewholder holder=new myviewholder(myview);
        return holder;

    }

    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.textView1.setText(names.get(position));
        holder.textView2.setText(chats.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                String selectedName = names.get(clickedPosition);

                // Create an Intent to open chatting1.java
                Intent intent = new Intent(context, chatting1.class);

                // Pass the selectedName as an extra to the intent
                intent.putExtra("NAME", selectedName);

                // Start the activity
                context.startActivity(intent);




            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    //view holder class are use to hold the refernce of custom layout widgets.
    class myviewholder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public myviewholder(View itemview)
        {
            super(itemview);
            cardView=itemview.findViewById(R.id.cardview);
            imageView=itemview.findViewById(R.id.peopleimg);
            textView1=itemview.findViewById(R.id.nametext);
            textView2=itemview.findViewById(R.id.chattext);
        }


    }



}

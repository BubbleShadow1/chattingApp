package com.androidapp.whatsappproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private Context context;
    private List<messages1> messages1List;

    public MessageAdapter(Context context) {
        this.context = context;
        messages1List = new ArrayList<>();
    }

    public void clear() {
        messages1List.clear();
        notifyDataSetChanged();
    }

    public void add(messages1 message) {
        messages1List.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        messages1 message = messages1List.get(position);
        if (message.getSenderid().equals(FirebaseAuth.getInstance().getUid())) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.senderlayout, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciever_layout, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messages1 message = messages1List.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            SentMessageViewHolder sentHolder = (SentMessageViewHolder) holder;
            sentHolder.bind(message);
        } else {
            ReceivedMessageViewHolder receivedHolder = (ReceivedMessageViewHolder) holder;
            receivedHolder.bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages1List.size();  // Return the size of the messages list
    }

    public class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView msg;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.msgsendertyp);
        }

        public void bind(messages1 message) {
            msg.setText(message.getMessage());
        }
    }

    public class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView msg;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.msgrectyp);
        }

        public void bind(messages1 message) {
            msg.setText(message.getMessage());
        }
    }
}


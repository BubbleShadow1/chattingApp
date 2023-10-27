package com.androidapp.whatsappproject;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageStorageManager {
    private static final String MESSAGE_KEY = "messages";

    public static void saveMessages(Context context, List<String> messages, String prefName) {
        SharedPreferences preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> messageSet = new HashSet<>(messages);
        editor.putStringSet(MESSAGE_KEY, messageSet);
        editor.apply();
    }

    public static List<String> getMessages(Context context, String prefName) {
        SharedPreferences preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        Set<String> messageSet = preferences.getStringSet(MESSAGE_KEY, new HashSet<>());
        return new ArrayList<>(messageSet);
    }
}


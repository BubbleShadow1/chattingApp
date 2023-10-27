package com.androidapp.whatsappproject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class contacts implements Parcelable {

    public contacts(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    private String name;
    private String phoneNumber;


    protected contacts(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<contacts> CREATOR = new Creator<contacts>() {
        @Override
        public contacts createFromParcel(Parcel in) {
            return new contacts(in);
        }

        @Override
        public contacts[] newArray(int size) {
            return new contacts[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
    }
}

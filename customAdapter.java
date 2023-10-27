package com.androidapp.whatsappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class customAdapter extends FragmentPagerAdapter {

    public customAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public customAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0) fragment=new Chats();
        else if(position==1)fragment=new status();
        else if(position==2) fragment=new Calls();
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0)
        {
            title="Chats";

        }
        else if(position==1)
        {
            title="Updates";
        }
        else if(position==2)
        {
            title="Calls";
        }
        return title;
    }
}


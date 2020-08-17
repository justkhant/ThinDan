package com.example.thindanAndroid.components.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfilePagerAdapter extends FragmentStateAdapter {

    private int numTabs;

    public ProfilePagerAdapter(@NonNull FragmentActivity fragmentActivity, int numTabs) {
        super(fragmentActivity);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                ProfileAboutFragment about = new ProfileAboutFragment();
                return about;
            case 1:
                ProfileImagesFragment images = new ProfileImagesFragment();
                return images;
            default:
                ProfileContactFragment contact = new ProfileContactFragment();
                return contact;
        }
    }

    @Override
    public int getItemCount() {
        return numTabs;
    }
}

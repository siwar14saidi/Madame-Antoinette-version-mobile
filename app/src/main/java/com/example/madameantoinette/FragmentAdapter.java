package com.example.madameantoinette;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Profil profil= new Profil();
                return profil;

            case 1:
                Conseil conseil =new Conseil();
                return conseil;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

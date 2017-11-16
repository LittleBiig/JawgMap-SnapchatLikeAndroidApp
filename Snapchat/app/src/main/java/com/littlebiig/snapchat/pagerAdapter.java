package com.littlebiig.snapchat;

/**
 * Created by Little on 26/10/2017.
 */
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class pagerAdapter extends FragmentPagerAdapter  {

    private final List<Fragment> fragments;


    //On fournit � l'adapter la liste des fragments � afficher
    public pagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    public float getPageWidth(int position) {
        if (position == 0 || position == 2) {
            return 0.8f;
        }
        return 1f;
    }


}

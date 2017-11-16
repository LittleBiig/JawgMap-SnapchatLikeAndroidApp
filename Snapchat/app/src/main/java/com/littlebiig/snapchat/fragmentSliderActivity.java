package com.littlebiig.snapchat;

/**
 * Created by Little on 26/10/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.Vector;


public class fragmentSliderActivity extends FragmentActivity {

    private pagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager);

        // Cr�ation de la liste de Fragments que fera d�filer le PagerAdapter
        List<Fragment> fragments = new Vector<Fragment>();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this,rightPageFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,middlePageFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,leftPageFragment.class.getName()));



        // Cr�ation de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new pagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);


        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);
        pager.setCurrentItem(1);


    }





}

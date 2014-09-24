package com.example.jks.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jks.tabswithswipe.AutomaticFragment;
import com.example.jks.tabswithswipe.FoodInputFragment;
import com.example.jks.tabswithswipe.StorageFragment;
import com.example.jks.tabswithswipe.ShoppingListFragment;


/**
 * Created by jks on 16.09.14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index){

            case 0:
            return new FoodInputFragment();
            case 1:
            return new StorageFragment();
            case 2:
                return new ShoppingListFragment();
            case 3:
                return new AutomaticFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

package com.example.stcon;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class switchadapter extends FragmentPagerAdapter{

    private Context context;
    private int totalTabs;
    public switchadapter(@NonNull FragmentManager fm, Context context,int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                available alpha = new available();
                return alpha;

            case 1:
                applied beta = new applied();
                return beta;

            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return totalTabs;
    }


}

package com.example.ipcs.parent;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Donglai on 12/4/15.
 */
public class MyFragmentPagerAdapter  extends FragmentPagerAdapter{

   private String tabTitles[]=new String[] {"Teacher","Schedule","Merchant"};
   private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
         this.context = context;
    }

    public Fragment getItem(int i) {
        if (i==0)
           return new TabFragment1();
        else if (i==1)
            return new TabFragment2();
        else if (i==2)
            return new TabFragment3();

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

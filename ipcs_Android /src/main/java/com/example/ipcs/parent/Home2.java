package com.example.ipcs.parent;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.ipcs.parent.stab.SlidingTabLayout;

/**
 * Created by Donglai on 8/4/15.
 */
public class Home2 extends FragmentActivity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),Home2.this));
        SlidingTabLayout slidingTabLayout=(SlidingTabLayout)findViewById(R.id.slidingtabs);
        slidingTabLayout.setViewPager(viewPager);
       /*ActionBar actionBar = getActionBar();


         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


            actionBar.addTab(tab1);
            actionBar.addTab(tab2);
            actionBar.addTab(tab3);
            tab1 = actionBar.newTab().setText("tab1");
            tab2 = actionBar.newTab().setText("tab2");

            tab3 = actionBar.newTab().setText("tab3");

            tab1.setTabListener(new TabListener(fragment1));
            tab2.setTabListener(new TabListener(fragment2));
            tab3.setTabListener(new TabListener(fragment3));

*/

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home2, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items



        switch (item.getItemId()) {
            case R.id.action1:
                Toast toast1=Toast.makeText(this,"button 1",Toast.LENGTH_SHORT);
                toast1.show();
                return true;
            case R.id.action2:
                Toast toast2=Toast.makeText(this,"button 2",Toast.LENGTH_SHORT);
                toast2.show();
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
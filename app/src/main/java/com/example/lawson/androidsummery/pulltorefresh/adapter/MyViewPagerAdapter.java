package com.example.lawson.androidsummery.pulltorefresh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Ian.Lu on 2016/10/21.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("lawson", "选择了第" + (position + 1) + "页");
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

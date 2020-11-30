package com.example.lawson.androidsummery.pulltorefresh.adapter;

import android.util.Log;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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

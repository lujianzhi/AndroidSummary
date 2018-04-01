package com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.BaseFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.OneFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.ThreeFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = FragmentLazyLoadActivity.PATH)
public class FragmentLazyLoadActivity extends AppCompatActivity {

    public static final String PATH = "/ian/fragment_lazy_load/one";

    private ViewPager viewPager;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lazy_load);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        fragmentList = new ArrayList<>();
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}

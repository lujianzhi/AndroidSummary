package com.example.lawson.androidsummery.fragment.normal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.normal.fragment.NormalFragmentOne;
import com.example.lawson.androidsummery.fragment.normal.fragment.NormalFragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class FragmentNormalActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_normal);

        viewPager = findViewById(R.id.view_pager);

        fragmentList = new ArrayList<>();
        fragmentList.add(NormalFragmentOne.getInstance());
        fragmentList.add(NormalFragmentTwo.getInstance());

        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager()));
    }

    private class FragmentViewPagerAdapter extends FragmentPagerAdapter {

        public FragmentViewPagerAdapter(FragmentManager fm) {
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

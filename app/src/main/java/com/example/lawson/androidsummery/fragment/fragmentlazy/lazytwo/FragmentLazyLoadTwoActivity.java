package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.BaseFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentA;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentB;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentC;

import java.util.ArrayList;
import java.util.List;

@Route(path = FragmentLazyLoadTwoActivity.PATH)
public class FragmentLazyLoadTwoActivity extends AppCompatActivity {

    public static final String PATH = "/ian/fragment_lazy_load/two";

    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lazy_load_two);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());
        fragmentList.add(new FragmentC());

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        });
    }
}

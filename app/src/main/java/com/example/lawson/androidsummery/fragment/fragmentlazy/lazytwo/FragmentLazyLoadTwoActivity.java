package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.*;

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
        fragmentList.add(new FragmentD());
        fragmentList.add(new FragmentE());
        fragmentList.add(new FragmentF());

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 6; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        for (int i = 0; i < 6; i++) {
            tabLayout.getTabAt(i).setText(String.valueOf(i));
        }
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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

package com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.BaseFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentA;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentB;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentC;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentD;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentE;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazytwo.fragment.FragmentF;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
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

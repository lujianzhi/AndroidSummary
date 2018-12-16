package com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.BaseFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.OneFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.ThreeFragment;
import com.example.lawson.androidsummery.fragment.fragmentlazy.lazyone.fragment.TwoFragment;
import com.example.lawson.androidsummery.test.ModelOne;
import com.example.lawson.androidsummery.test.ModelThree;
import com.example.lawson.androidsummery.test.ModelTwo;

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
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < 6; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        for (int i = 0; i < 6; i++) {
            tabLayout.getTabAt(i).setText(String.valueOf(i));
        }
        tabLayout.setupWithViewPager(viewPager);
        ModelTwo modelTwo = new ModelTwo("2");
        ModelThree modelThree = new ModelThree();
        modelThree.name = "3";
        ModelOne modelOne = new ModelOne(modelTwo, modelThree);
        Bundle bundle = new Bundle();
        bundle.putParcelable("obj", modelOne);
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);

        fragmentList = new ArrayList<>();
        fragmentList.add(oneFragment);
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new ThreeFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private class MyAdapter extends FragmentStatePagerAdapter {

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

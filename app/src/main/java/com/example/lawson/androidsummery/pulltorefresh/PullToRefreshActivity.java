package com.example.lawson.androidsummery.pulltorefresh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.pulltorefresh.adapter.MyViewPagerAdapter;
import com.example.lawson.androidsummery.pulltorefresh.fragment.PullFragment;

import java.util.ArrayList;
import java.util.List;

public class PullToRefreshActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private RadioButton tab1;
    private RadioButton tab2;
    private RadioButton tab3;
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

        initData();
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        viewPager = findViewById(R.id.viewPager);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        //设置缓存的页数为3+1(当前页面)
        viewPager.setOffscreenPageLimit(3);
        //设置两页之间的间距
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin));
        viewPager.setCurrentItem(0);
        tab1.setChecked(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("lawson", "选择了第" + (position + 1) + "页");
                changeRadioButtonState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void changeRadioButtonState(int position) {
        switch (position) {
            case 0:
                tab1.setChecked(true);
                break;
            case 1:
                tab2.setChecked(true);
                break;
            case 2:
                tab3.setChecked(true);
                break;
        }
    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        Fragment fragment1 = new PullFragment();
        Fragment fragment2 = new PullFragment();
        Fragment fragment3 = new PullFragment();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3:
                viewPager.setCurrentItem(2);
                break;
        }

    }
}

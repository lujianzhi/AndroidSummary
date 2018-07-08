package com.example.lawson.androidsummery.diyview.hideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class HideViewRVActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HideView mHideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mHideView = (HideView) findViewById(R.id.hide_view);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 9999; i++) {
            stringList.add(String.valueOf(i));
        }
        mRecyclerView.setAdapter(new MyAdapter(stringList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHideView.bindRecyclerView(mRecyclerView);
        mHideView.setVisibility(View.GONE);
    }
}

package com.example.lawson.androidsummery.diyview.hideview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HideViewRVActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HideView mHideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_view);

        mRecyclerView = findViewById(R.id.recycler_view);
        mHideView = findViewById(R.id.hide_view);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 9999; i++) {
            stringList.add(String.valueOf(i));
        }
        mRecyclerView.setAdapter(new MyAdapter(stringList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHideView.bindRecyclerView(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = recyclerView.getLayoutManager().findViewByPosition(0);
                Rect rect = new Rect();
                mRecyclerView.getHitRect(rect);
                if (view != null && view.getLocalVisibleRect(rect)) {
                    Log.i("Ian", "可见");
                } else {
                    Log.i("Ian", "不可见");
                }
            }
        });
        mHideView.setVisibility(View.GONE);
    }
}

package com.example.lawson.androidsummery.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageView ivBg;
    private MyAdapter myAdapter;
    private List<String> datas = new ArrayList<>();
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ivBg = (ImageView) findViewById(R.id.iv_bg);

        Button vertical = (Button) findViewById(R.id.vertical);
        vertical.setOnClickListener(this);
        Button horizontal = (Button) findViewById(R.id.horizontal);
        horizontal.setOnClickListener(this);
        Button grid = (Button) findViewById(R.id.grid);
        grid.setOnClickListener(this);
        Button falls = (Button) findViewById(R.id.falls);
        falls.setOnClickListener(this);

        for (int i = 0; i < 1; i++) {
            datas.add(String.valueOf(i));
        }

        myAdapter = new MyAdapter(datas);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                y += dy;
                Log.i("Ian", "dx : " + dx + " ; dy : " + dy + " ; y : " + y);
                translate(y);
            }
        });
    }

    private void translate(int y) {
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivBg.getLayoutParams();
//        layoutParams.topMargin = -y;
//        ivBg.invalidate();
        ivBg.setTranslationY(-y);
    }

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerViewsActivity.this);

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ian", "FirstVisible : " + linearLayoutManager.findFirstVisibleItemPosition() + " ; LastVisible : " + linearLayoutManager.findLastVisibleItemPosition());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vertical:
                recyclerView.setLayoutManager(linearLayoutManager);
//                recyclerView.addItemDecoration(new MyDividerItemDecoration(RecyclerViewsActivity.this, MyDividerItemDecoration.VERTICAL_DIVIDER));
                Log.i("Ian", "初始化FirstVisible : " + linearLayoutManager.findFirstVisibleItemPosition() + " ; LastVisible : " + linearLayoutManager.findLastVisibleItemPosition());
                break;

            case R.id.horizontal:

                break;

            case R.id.grid:

                break;

            case R.id.falls:

                break;

            default:
                break;
        }
    }
}

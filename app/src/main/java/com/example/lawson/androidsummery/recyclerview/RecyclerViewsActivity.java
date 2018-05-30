package com.example.lawson.androidsummery.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Button vertical = (Button) findViewById(R.id.vertical);
        vertical.setOnClickListener(this);
        Button horizontal = (Button) findViewById(R.id.horizontal);
        horizontal.setOnClickListener(this);
        Button grid = (Button) findViewById(R.id.grid);
        grid.setOnClickListener(this);
        Button falls = (Button) findViewById(R.id.falls);
        falls.setOnClickListener(this);

        for (int i = 0; i < 100; i++) {
            datas.add(String.valueOf(i));
        }

        myAdapter = new MyAdapter(datas);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vertical:
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewsActivity.this));
//                recyclerView.addItemDecoration(new MyDividerItemDecoration(RecyclerViewsActivity.this, MyDividerItemDecoration.VERTICAL_DIVIDER));
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

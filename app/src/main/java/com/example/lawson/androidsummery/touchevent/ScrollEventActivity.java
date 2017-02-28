package com.example.lawson.androidsummery.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.lawson.androidsummery.R;

public class ScrollEventActivity extends AppCompatActivity {

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_event);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);


    }
}

package com.example.lawson.androidsummery.touchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollEventActivity extends AppCompatActivity {

    private ListView list_view;
    private List<String> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_event);

        for (int i = 0; i < 100; i++) {
            numbers.add(String.valueOf(i));
        }

        list_view = findViewById(R.id.list_view);
        list_view.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, numbers));

    }


}

package com.example.lawson.androidsummery.diyview.hideview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class HideViewLVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_view_lv);

        HideView hideView = findViewById(R.id.hide_view);
        ListView listView = findViewById(R.id.list_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9999; i++) {
            list.add(String.valueOf(i));
        }
        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list));
        hideView.bindListView(listView);
        hideView.setVisibility(View.GONE);
    }
}

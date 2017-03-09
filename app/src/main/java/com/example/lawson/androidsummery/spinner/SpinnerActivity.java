package com.example.lawson.androidsummery.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button;
    private ArrayAdapter<String> adapter;
    private List<String> str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        str = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            str.add("第" + i);
        }

        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter.addAll(str);
        spinner.setAdapter(adapter);
        spinner.setSelection(11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("新增" + 40);
                adapter.add("新增" + 41);
                adapter.add("新增" + 42);
                adapter.add("新增" + 43);
                adapter.notifyDataSetChanged();
                spinner.setSelection(21);
            }
        });
    }
}

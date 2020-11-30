package com.example.lawson.androidsummery.spinner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter.addAll(str);
        spinner.setAdapter(adapter);
        spinner.setSelection(10, true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("新增" + 40);
                adapter.add("新增" + 41);
                adapter.add("新增" + 42);
                adapter.add("新增" + 43);
                adapter.notifyDataSetChanged();
                spinner.setSelection(21, true);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "onItemSelected", Toast.LENGTH_SHORT).show();
                Log.i("Ian", "onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
                Log.i("Ian", "onNothingSelected");
            }
        });
    }
}

package com.example.lawson.androidsummery.collection;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.detectmemory.entity.ObjWithoutContext;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Collection_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
    }

}

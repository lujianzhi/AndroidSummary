package com.example.lawson.androidsummery.drawerlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DrawerLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("" + i);
        }

        Adapter adapter = new Adapter(dataList);
        LinearLayoutManager l = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(l);
        mRecyclerView.setAdapter(adapter);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.VH> {

        private List<String> dataList;

        public Adapter(List<String> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(
                    LayoutInflater.from(DrawerLayoutActivity.this).inflate(R.layout.list_view_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.text.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class VH extends RecyclerView.ViewHolder {

            TextView text;

            public VH(View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text);
            }
        }
    }
}

package com.example.lawson.androidsummery.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian.Lu on 2017/7/18.
 * Project : AndroidSummary
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> datas = new ArrayList<>();

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.numberTV.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView numberTV;

        public ViewHolder(View itemView) {
            super(itemView);
            numberTV = (TextView) itemView.findViewById(R.id.number);
        }
    }
}

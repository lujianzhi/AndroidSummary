package com.example.lawson.androidsummery.pulltorefresh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.pulltorefresh.entity.PullBean;

import java.util.List;

/**
 * Created by Ian.Lu on 2016/10/21.
 */

public class MyAdapter extends BaseAdapter {

    private List<PullBean> data;

    public MyAdapter(List<PullBean> data) {
        this.data = data;
    }

    public void setData(List<PullBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PullBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_to_refresh_list_view, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PullBean pb = getItem(position);
        viewHolder.title.setText(pb.getTitle());
        viewHolder.content.setText(pb.getContent());

        return convertView;
    }

    private class ViewHolder {
        public TextView title;
        public TextView content;
    }
}

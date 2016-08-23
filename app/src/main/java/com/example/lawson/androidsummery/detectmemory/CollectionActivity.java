package com.example.lawson.androidsummery.detectmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.detectmemory.entity.ObjWithContext;
import com.example.lawson.androidsummery.detectmemory.entity.ObjWithoutContext;
import com.example.lawson.androidsummery.detectmemory.entity.SinglePoolObj;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private ListView listView;
    private static List<ObjWithContext> list;
//    private static List<SinglePoolObj> list;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        content = "123";

        listView = (ListView) findViewById(R.id.list);

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new ObjWithContext(this, content));
//            list.add(SinglePoolObj.obtain(content));
        }

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        list.clear();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            String str = list.get(position).getContent();
            viewHolder.textView.setText(str);

            return convertView;
        }

        class ViewHolder {

            TextView textView;

        }
    }
}

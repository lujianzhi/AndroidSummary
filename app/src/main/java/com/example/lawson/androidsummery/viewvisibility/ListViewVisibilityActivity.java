package com.example.lawson.androidsummery.viewvisibility;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.router.Constant;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.VIEW_VISIBILITY_ACTIVITY_LIST_VIEW)
public class ListViewVisibilityActivity extends AppCompatActivity {

    private ListView listView;
    private int mScrollState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_visibility);

        listView = findViewById(R.id.list_view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mScrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //开始滚动（SCROLL_STATE_FLING），正在滚动(SCROLL_STATE_TOUCH_SCROLL), 已经停止（SCROLL_STATE_IDLE）
                if (mScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    Log.i("Ian", "不可见 firstVisibleItem : " + firstVisibleItem + " ; visibleItemCount : " + visibleItemCount + " ; totalItemCount : " + totalItemCount);
                } else {
                    int first = listView.getFirstVisiblePosition();
                    int last = listView.getLastVisiblePosition();
                    Log.i("Ian", "可见  first : " + first + " ; last : " + last
                            + " firstVisibleItem : " + firstVisibleItem + " ; visibleItemCount : " + visibleItemCount + " ; totalItemCount : " + totalItemCount);
                }
            }
        });
    }
}

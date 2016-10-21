package com.example.lawson.androidsummery.pulltorefresh.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.pulltorefresh.adapter.MyAdapter;
import com.example.lawson.androidsummery.pulltorefresh.entity.PullBean;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian.Lu on 2016/10/21.
 */

public class PullFragment extends Fragment {

    private View mainView;

    private PullToRefreshListView pullToRefreshListView;
    private List<PullBean> data;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_pull_to_refresh, container, false);
        pullToRefreshListView = (PullToRefreshListView) mainView.findViewById(R.id.pullToRefresh);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        adapter = new MyAdapter(data);
        pullToRefreshListView.setAdapter(adapter);
        //设置可以上拉也可以下拉
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置文字
        ILoadingLayout header = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        header.setPullLabel("下拉刷新");
        header.setRefreshingLabel("正在获取最新数据...");
        header.setReleaseLabel("放开刷新");
        ILoadingLayout footer = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        footer.setPullLabel("上拉加载");
        footer.setRefreshingLabel("正在加载更多数据...");
        footer.setReleaseLabel("放开加载");

        //设置Mode为BOTH时，需要实现OnRefreshListener2接口
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉
                initData();
                adapter.setData(data);
                new FinishRefresh().execute();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉
                addData();
            }
        });
    }

    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //完成加载
            pullToRefreshListView.onRefreshComplete();
        }
    }

    private void initData() {
        data = new ArrayList<PullBean>();
        for (int i = 0; i < 10; i++) {
            PullBean pb = PullBean.obtain("title-" + i, "content-" + i);
            data.add(pb);
        }
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            data.add(PullBean.obtain("new title-" + i, "new content-" + i));
        }
        adapter.setData(data);
        new FinishRefresh().execute();
        adapter.notifyDataSetChanged();
    }
}

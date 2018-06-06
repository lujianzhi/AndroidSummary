package com.example.lawson.androidsummery.diyview.hideview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Ian.Lu on 2018/5/30.
 * Project : AndroidSummary
 */
public class HideView extends android.support.v7.widget.AppCompatImageView {
    private boolean hide;

    public HideView(Context context) {
        super(context);
    }

    public HideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && !hide) {
                    HideView.this.hideAndFadeOutAni();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    HideView.this.showAndFadeInAni();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void bindListView(ListView listView) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL && !hide) {
                    HideView.this.hideAndFadeOutAni();
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    HideView.this.showAndFadeInAni();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        listView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("Ian", "Action : " + event.getAction());
                return false;
            }
        });
    }

    private void hideAndFadeOutAni() {
        hide = true;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(this, "alpha", 0.5F);
        ObjectAnimator hide = ObjectAnimator.ofFloat(this, "translationX", 0, getWidth() / 2);
        set.playTogether(fadeOut, hide);
        set.setDuration(300).start();
    }

    private void showAndFadeInAni() {
        hide = false;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0.5F, 1);
        ObjectAnimator show = ObjectAnimator.ofFloat(this, "translationX", getWidth() / 2, 0);
        set.playTogether(fadeIn, show);
        set.setDuration(300).start();
    }
}

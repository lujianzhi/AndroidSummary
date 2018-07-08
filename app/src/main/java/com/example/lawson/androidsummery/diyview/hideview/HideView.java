package com.example.lawson.androidsummery.diyview.hideview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
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

    int totalDy = 0;
    int singleHeight = -1;
    boolean needShow = false;

    public void bindRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needShow) {
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING && !hide) {
                        HideView.this.hideAndFadeOutAni();
                    } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        HideView.this.showAndFadeInAni();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (singleHeight == -1) {
                    singleHeight = recyclerView.getLayoutManager().findViewByPosition(0).getHeight();
                }
                totalDy += dy;
                if (totalDy / singleHeight > 10) {
                    if (!needShow) {
                        setVisibility(View.VISIBLE);
                        hideAndFadeOutAni();
                    }
                    needShow = true;
                } else {
                    if (needShow) {
                        goneAni();
                    }
                    needShow = false;
                    if (hideSet != null) {
                        hideSet.cancel();
                    }
                    if (showSet != null) {
                        showSet.cancel();
                    }
                }
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
                Log.i("Ian", "firstVisibleItem : " + firstVisibleItem);
                if (firstVisibleItem > 10) {
                    if (!needShow) {
                        setVisibility(View.VISIBLE);
                        hideAndFadeOutAni();
                    }
                    needShow = true;
                } else {
                    if (needShow) {
                        goneAni();
                    }
                    needShow = false;
                    if (hideSet != null) {
                        hideSet.cancel();
                    }
                    if (showSet != null) {
                        showSet.cancel();
                    }
                }
            }
        });
    }

    private AnimatorSet hideSet;

    private void hideAndFadeOutAni() {
        hide = true;
        hideSet = new AnimatorSet();
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(this, "alpha", 0.2F);
//        ObjectAnimator hide = ObjectAnimator.ofFloat(this, "translationX", 0, getWidth() / 2);
        hideSet.playTogether(fadeOut);
        hideSet.setDuration(300).start();
    }

    private AnimatorSet showSet;

    private void showAndFadeInAni() {
        hide = false;
        showSet = new AnimatorSet();
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0.2F, 1);
//        ObjectAnimator show = ObjectAnimator.ofFloat(this, "translationX", getWidth() / 2, 0);
        showSet.playTogether(fadeIn);
        showSet.setDuration(300).start();
    }

    private void goneAni() {
        AnimatorSet goneSet = new AnimatorSet();
        ObjectAnimator gone = ObjectAnimator.ofFloat(this, "alpha", 0.2F, 0F);
        goneSet.playTogether(gone);
        goneSet.setDuration(300).start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        totalDy = 0;
        needShow = false;
    }
}

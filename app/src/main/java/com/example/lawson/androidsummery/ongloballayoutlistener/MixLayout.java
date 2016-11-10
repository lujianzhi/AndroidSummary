package com.example.lawson.androidsummery.ongloballayoutlistener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lawson.androidsummery.R;

/**
 * Created by Ian.Lu on 2016/11/10.
 * Projct : AndroidSummary
 */

public class MixLayout extends LinearLayout {

    private RelativeLayout relative_layout;
    private ImageView image_view;

    public MixLayout(Context context) {
        super(context);
        init(context);
    }

    public MixLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MixLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mix_layout, this);
        relative_layout = (RelativeLayout) findViewById(R.id.relative_layout);
        image_view = (ImageView) view.findViewById(R.id.image_view);

        image_view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("ian", "MixLayout - getWidth : " + getWidth());
                Log.i("ian", "MixLayout - getHeight : " + getHeight());
                Log.i("ian", "MixLayout - getMeasuredWidth : " + getMeasuredWidth());
                Log.i("ian", "MixLayout - getMeasuredHeight : " + getMeasuredHeight());
                Log.i("ian", "relative_layout.MixLayout - getWidth : " + relative_layout.getWidth());
                Log.i("ian", "relative_layout.MixLayout - getHeight : " + relative_layout.getHeight());
                Log.i("ian", "relative_layout.MixLayout - getMeasuredWidth : " + relative_layout.getMeasuredWidth());
                Log.i("ian", "relative_layout.MixLayout - getMeasuredHeight : " + relative_layout.getMeasuredHeight());
                Log.i("ian", "image_view.MixLayout - getWidth : " + image_view.getWidth());
                Log.i("ian", "image_view.MixLayout - getHeight : " + image_view.getHeight());
                Log.i("ian", "image_view.MixLayout - getMeasuredWidth : " + image_view.getMeasuredWidth());
                Log.i("ian", "image_view.MixLayout - getMeasuredHeight : " + image_view.getMeasuredHeight());
            }
        });
    }
}

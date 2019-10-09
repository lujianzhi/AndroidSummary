package com.example.lawson.androidsummery.test;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

import org.xml.sax.XMLReader;

public class SizeLabel implements Html.TagHandler {
    private float mSize;
    private int mStartIndex = 0;
    private int mStopIndex = 0;

    private Context mContext;

    public SizeLabel(Context context, float size) {
        this.mSize = size;
        this.mContext = context;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.toLowerCase().trim().equals("size")) {
            if (opening) {
                mStartIndex = output.length();
            } else {
                mStopIndex = output.length();
                output.setSpan(new AbsoluteSizeSpan(dip2px(mContext, mSize)), mStartIndex, mStopIndex,
                               Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    //dp转px
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }    //dp转px

}
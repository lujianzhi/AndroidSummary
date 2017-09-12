package com.example.lawson.androidsummery.bitmap.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by Ian.Lu on 2017/9/7.
 * Project : AndroidSummary
 */

public class ImageResizer {

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd, int viewWidth, int viewHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);

        options.inSampleSize = calculateSampleSize(viewWidth, viewHeight, options.outWidth, options.outHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int viewWidth, int viewHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateSampleSize(viewWidth, viewHeight, options.outWidth, options.outHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 官方推荐inSampleSize为2的指数倍数增长，即1,2,4,8....
     */
    private int calculateSampleSize(int viewWidth, int viewHeight, int bitmapWidth, int bitmapHeight) {
        int inSampleSize = 1;

        if (viewWidth < bitmapWidth || viewHeight < bitmapHeight) {
            int halfBitmapWidth = bitmapWidth / 2;
            int halfBitmapHeight = bitmapHeight / 2;
            while (viewWidth <= (halfBitmapWidth / inSampleSize) && viewHeight <= (halfBitmapHeight / inSampleSize)) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}

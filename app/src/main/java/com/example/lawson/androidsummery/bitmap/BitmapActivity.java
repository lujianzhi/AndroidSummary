package com.example.lawson.androidsummery.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;

public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView byte_count;
    private TextView destiny_Dpi;
    private ImageView image;
    private ImageView image2;
    private ImageView image3;
    private Button get_destiny;
    private Button xxhdpi;
    private Button load_compression;
    private Button load_without_compression;
    private Button inBitmap;
    private Button no_inBitmap;

    private String TAG = "lawson";
    private String fileStr;

    //手机屏幕宽度
    private int screenWith;
    //手机屏幕高度
    private int screenHeight;
    //手机密度
    private float density;
    //手机密度Dpi
    private int densityDpi;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        byte_count = (TextView) findViewById(R.id.byte_count);
        destiny_Dpi = (TextView) findViewById(R.id.destiny_Dpi);
        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        get_destiny = (Button) findViewById(R.id.get_destiny);
        xxhdpi = (Button) findViewById(R.id.xxhdpi);
        load_compression = (Button) findViewById(R.id.load_compression);
        load_without_compression = (Button) findViewById(R.id.load_without_compression);
        inBitmap = (Button) findViewById(R.id.inBitmap);
        no_inBitmap = (Button) findViewById(R.id.no_inBitmap);

        fileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/kisum3.jpg";

        get_destiny.setOnClickListener(this);
        xxhdpi.setOnClickListener(this);
        load_compression.setOnClickListener(this);
        load_without_compression.setOnClickListener(this);
        inBitmap.setOnClickListener(this);
        no_inBitmap.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_destiny:
                getScreenDensity_ByWindowManager();
                getScreenDensity_ByResources();
                getDefaultScreenDensity();
                destiny_Dpi.setText(String.valueOf(densityDpi));
                break;
            case R.id.xxhdpi:
                loadxxhdpi();
                break;
            case R.id.load_compression:
                loadCompression();
                break;
            case R.id.load_without_compression:
                loadWithoutCompression();
                break;
            case R.id.inBitmap:
                inBitmap();
                break;
            case R.id.no_inBitmap:
                noInBitmap();
                break;
        }
    }

    private void loadxxhdpi() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kisum3);
        byte_count.setText("byteCount : " + bitmap.getByteCount());
        Log.d(TAG, "byteCount : " + bitmap.getByteCount());
        image.setImageBitmap(bitmap);
    }

    private void loadWithoutCompression() {
        bitmap = BitmapFactory.decodeFile(fileStr);
        byte_count.setText("byteCount : " + bitmap.getByteCount());
        image.setImageBitmap(bitmap);
    }


    private void loadCompression() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileStr, options);
        int width = options.outWidth;
        int height = options.outHeight;
        int scaleX = width / screenWith;
        int scaleY = height / screenHeight;
        int scale = scaleX > scaleY ? scaleX : scaleY;
        options.inSampleSize = scale;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(fileStr, options);
        int width2 = options.outWidth;
        int height2 = options.outHeight;
        byte_count.setText("byteCount : " + bitmap.getByteCount());
        Log.d(TAG, "压缩后width : " + width2 + " ; height : " + height2 + " ; scale : " + scale);
        image.setImageBitmap(bitmap);
    }

    private void inBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //这个必须在decodeFile之前设置，否则会出现
        //BitmapFactory: Unable to reuse an immutable bitmap as an image decoder target.
        options.inMutable = true;
        bitmap = BitmapFactory.decodeFile(fileStr,options);
        image.setImageBitmap(bitmap);
        //使用谷歌推荐的方法，这里可以认为是复用了这个bitmap
        options.inBitmap = bitmap;

        image2.setImageBitmap(BitmapFactory.decodeFile(fileStr, options));
        image3.setImageBitmap(BitmapFactory.decodeFile(fileStr, options));
    }

    private void noInBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();

        bitmap = BitmapFactory.decodeFile(fileStr);
        image.setImageBitmap(bitmap);
        options.inBitmap = bitmap;

        image2.setImageBitmap(BitmapFactory.decodeFile(fileStr));
        image3.setImageBitmap(BitmapFactory.decodeFile(fileStr));
    }

    //获得手机的宽度和高度像素单位为px
// 通过WindowManager获取
    public void getScreenDensity_ByWindowManager() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        screenWith = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;
        density = mDisplayMetrics.density;
        densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + screenWith + "x" + screenHeight + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    // 通过Resources获取
    public void getScreenDensity_ByResources() {
        DisplayMetrics mDisplayMetrics = getResources().getDisplayMetrics();
        screenWith = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;
        density = mDisplayMetrics.density;
        densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + screenWith + "x" + screenHeight + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    // 获取屏幕的默认分辨率
    public void getDefaultScreenDensity() {
        Display mDisplay = getWindowManager().getDefaultDisplay();
        screenWith = mDisplay.getWidth();
        screenHeight = mDisplay.getHeight();
        Log.d(TAG, "Screen Default Ratio: [" + screenWith + "x" + screenHeight + "]");
        Log.d(TAG, "Screen mDisplay: " + mDisplay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }
}

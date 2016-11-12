package com.example.lawson.androidsummery.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lawson.androidsummery.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawActivity extends AppCompatActivity {

    //手机屏幕宽度
    private int screenWith;
    //手机屏幕高度
    private int screenHeight;

    private String fileStr;

    private DrawView drawView;
    private ImageView background;
    private Button save;
    private Bitmap backgroundBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        screenWith = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;

        fileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/q.jpg";

        drawView = (DrawView) findViewById(R.id.draw);
        background = (ImageView) findViewById(R.id.background);
        save = (Button) findViewById(R.id.save);

//        final Bitmap backgroundBitmap = BitmapFactory.decodeFile(fileStr);
//        if (backgroundBitmap != null && background != null) {
//            background.setImageBitmap(backgroundBitmap);
//        }

        backgroundBitmap = loadCompression();
        if (backgroundBitmap != null && background != null) {
            background.setImageBitmap(backgroundBitmap);
        }

        if (save != null) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (drawView != null) {

                        Bitmap drawBitmap = drawView.getDrawBitmap();

                        if (backgroundBitmap != null && background != null) {
//                            Bitmap finalBitmap = backgroundBitmap.copy(Bitmap.Config.ARGB_4444, true);
//                            Canvas canvas = new Canvas(finalBitmap);
//                            RectF rectf = new RectF(0, 0, background.getWidth(), background.getHeight());
//                            canvas.drawBitmap(drawBitmap, null, rectf, new Paint());

                            int maxWidth = backgroundBitmap.getWidth() > background.getWidth() ? backgroundBitmap.getWidth() : background.getWidth();
                            int maxHeight = backgroundBitmap.getHeight() > background.getHeight() ? backgroundBitmap.getHeight() : background.getHeight();
                            Bitmap finalBitmap = Bitmap.createBitmap(maxWidth, maxHeight, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(finalBitmap);
                            canvas.drawBitmap(backgroundBitmap, 0, 0, null);
                            canvas.drawBitmap(drawBitmap, 0, 0, null);
//                            canvas.drawBitmap(backgroundBitmap, null, new RectF(0, 0, maxWidth, maxHeight), new Paint());
//                            canvas.drawBitmap(drawBitmap, null, new RectF(0, 0, maxWidth, maxHeight), new Paint());

                            File finalFile;
                            FileOutputStream fos = null;
                            try {
                                finalFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.png");
                                if (finalFile.exists()) {
                                    finalFile.delete();
                                }
                                if (finalFile.createNewFile()) {
                                    fos = new FileOutputStream(finalFile);
                                    finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                    fos.flush();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (fos != null) {
                                        fos.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        Toast.makeText(DrawActivity.this, "OK", Toast.LENGTH_SHORT).show();

//                        if (backgroundBitmap != null) {
//                            Bitmap resultBitmap = Bitmap.createBitmap(backgroundBitmap, 0, 0, backgroundBitmap.getWidth(), backgroundBitmap.getHeight(), new Matrix(), false);
//                            drawView.mergePic(resultBitmap);
//                        }
//
//                        Bitmap finalBitmap = drawView.getDrawBitmap();

                    }
                }
            });
        }

    }

    private Bitmap loadCompression() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileStr, options);
        int width = options.outWidth;
        int height = options.outHeight;
        int scaleX = width / screenWith;
        int scaleY = height / screenHeight;
        int scale = scaleX > scaleY ? scaleX : scaleY;
        options.inMutable = true;
        options.inSampleSize = scale;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(fileStr, options);
        return bitmap;
    }

}

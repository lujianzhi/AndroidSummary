package com.example.lawson.androidsummery.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lawson.androidsummery.R;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int PHOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        imageView = findViewById(R.id.take_photo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTakePhoto();
            }
        });
    }

    private void startTakePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_CODE) {
                Bitmap photo = data.getParcelableExtra("data");
                if (photo != null) {
                    imageView.setImageBitmap(photo);
                }
            }
        }

    }
}

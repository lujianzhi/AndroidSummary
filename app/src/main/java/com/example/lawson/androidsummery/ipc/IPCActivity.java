package com.example.lawson.androidsummery.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.ipc.activity.messenger.MessengerActivity;
import com.example.lawson.androidsummery.ipc.activity.process.ColonProcessActivity;
import com.example.lawson.androidsummery.ipc.activity.process.FullProcessActivity;
import com.example.lawson.androidsummery.ipc.activity.aidl.AIDLActivity;
import com.example.lawson.androidsummery.ipc.been.MySerializable;
import com.example.lawson.androidsummery.ipc.binder.Book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class IPCActivity extends AppCompatActivity {

    public static final String BUNDLE = "bundle";
    public static final String BUNDLE_BOOK_KEY = "book";
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ian.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ColonProcessActivity.class);
                Bundle bundle = new Bundle();
                Book book = new Book(1, "第一本书");
                bundle.putParcelable(BUNDLE_BOOK_KEY, book);
                intent.putExtra(BUNDLE, bundle);
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeObjToSDCard();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MessengerActivity.class));
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AIDLActivity.class));
            }
        });

    }

    /**
     * 序列化对象到SD卡中
     */
    private void storeObjToSDCard() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                MySerializable mySerializable = new MySerializable(100, "yo");
                File file = new File(FILE_PATH);
                ObjectOutputStream outputStream = null;
                try {
                    if (!file.exists()) {
                        if (file.createNewFile()) {
                            outputStream = new ObjectOutputStream(new FileOutputStream(file));
                        }
                    } else {
                        outputStream = new ObjectOutputStream(new FileOutputStream(file));
                    }
                    outputStream.writeObject(mySerializable);
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    IPCActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getBaseContext(), FullProcessActivity.class));
                        }
                    });
                }
            }
        }.start();
    }
}

package com.example.lawson.androidsummery.ipc.activity.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.ipc.binder.Book;
import com.example.lawson.androidsummery.ipc.binder.IBookManager;
import com.example.lawson.androidsummery.ipc.service.AIDLService;

import java.util.List;

public class AIDLActivity extends AppCompatActivity {

    private TextView typeOfListTv;
    private TextView dataFromServerTv;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(service);
            try {
                List<Book> bookList = iBookManager.getBookList();
                iBookManager.addBook(new Book(4, "第四本书"));
                typeOfListTv.setText("客户端接受的List类型为 : " + bookList.getClass().getCanonicalName());
                dataFromServerTv.setText(bookList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        typeOfListTv = findViewById(R.id.type_of_list);
        dataFromServerTv = findViewById(R.id.data_from_server);
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

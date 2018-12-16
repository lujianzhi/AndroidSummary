package com.example.lawson.androidsummery.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.lawson.androidsummery.ipc.binder.Book;
import com.example.lawson.androidsummery.ipc.binder.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Ian.Lu on 2017/9/3.
 * Project : AndroidSummary
 */

public class AIDLService extends Service {
    //之前说AIDL能够使用的List只有ArrayList，但是这里使用了不继承与ArrayList的CopyOnWriteArrayList
    //因为AIDL中所支持的是抽象的List，而List只是一个接口，因此虽然服务端返回一个CopyOnWriteArrayList
    //但是Binder中会按照List的规范去访问数据并最终形成一个ArrayList传递给客户端
    //和此类似的还有ConcurrentHashMap
    private CopyOnWriteArrayList<Book> bookCopyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() {
            return bookCopyOnWriteArrayList;
        }
        @Override
        public void addBook(Book book) {
            bookCopyOnWriteArrayList.add(book);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        bookCopyOnWriteArrayList.add(new Book(1, "第一本书"));
        bookCopyOnWriteArrayList.add(new Book(2, "第二本书"));
        bookCopyOnWriteArrayList.add(new Book(3, "第三本书"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}

// IBookManager.aidl
package com.example.lawson.androidsummery.ipc.binder;

//需要导入Book所在的包，否则报错
import com.example.lawson.androidsummery.ipc.binder.Book;
interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);
}

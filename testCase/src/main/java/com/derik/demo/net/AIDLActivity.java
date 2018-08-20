package com.derik.demo.net;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.derik.demo.Book;
import com.derik.demo.IBookManager;
import com.derik.demo.R;

import java.util.List;

public class AIDLActivity extends Activity {

    //由AIDL文件生成的Java类
    private IBookManager mBookManager = null;

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private boolean mBound = false;

    //包含Book对象的list
    private List<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    /**
     * 按钮的点击事件，点击之后调用服务端的addBookIn方法
     *
     * @param view
     */
    public void addBook(View view) {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;


        try {
            Book book = new Book();
            book.setName("APP研发录In book1");
            book.setPrice(30);
            Log.e(getLocalClassName(), "1.0 " + book.toString());
            // 对象引用传递，实际上Service上对传入参数的修改，会使参数发生改变
            mBookManager.addBookIn(book);
            Log.e(getLocalClassName(), "1.1 " + book.toString());


            Book book2 = new Book();
            book2.setName("APP研发录Out book2");
            book2.setPrice(31);
            Log.e(getLocalClassName(), "2.0 " + book2.toString());
            mBookManager.addBookOut(book2);
            Log.e(getLocalClassName(), "2.1 " + book2.toString());


            Book book3 = new Book();
            book3.setName("APP研发录Inout book3");
            book3.setPrice(32);
            Log.e(getLocalClassName(), "3.0 " + book3.toString());
            mBookManager.addBookInout(book3);
            Log.e(getLocalClassName(), "3.1 " + book3.toString());


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试与服务端建立连接
     */
    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.derik.demo.action");
        intent.setPackage("com.derik.demo");
        // 隐式的绑定服务
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            mBookManager = IBookManager.Stub.asInterface(service);
            mBound = true;

            if (mBookManager != null) {
                try {
                    // 获取来自服务器的数据，并打印
                    mBooks = mBookManager.getBooks();
                    Log.e(getLocalClassName(), mBooks.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getLocalClassName(), "service disconnected");
            mBound = false;
        }
    };
}
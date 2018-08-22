package com.derik.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.derik.demo.net.AIDLActivity;
import com.derik.demo.net.async.AsyncTaskTestActivity;
import com.derik.demo.net.WebViewTestActivity;
import com.derik.demo.net.socket.TcpTestActivity;
import com.derik.demo.net.socket.UdpTestActivity;
import com.derik.demo.net.url.HttpURLTestActivity;
import com.derik.demo.net.url.URLTestActivity;

public class NetActivity extends Activity {

    private static final String TAG = "NetActivity";
    private Intent targetIntent;

    private String[] targetNames = new String[]{
            "WebView",
            "URL",
            "HTTP",
            "AIDL",
            "UDP",
            "TCP",
            "AsyncTask"
    };

    private String[] targetDescs = new String[]{
            "WebView使用实例",
            "URLTestActivity",
            "HttpURLConnection",
            "AIDL",
            "UDP Test",
            "TCP Test",
            "Download With AsyncTask"
    };

    private Class<?>[] targetClasses = new Class[]{
            WebViewTestActivity.class,
            URLTestActivity.class,
            HttpURLTestActivity.class,
            AIDLActivity.class,
            UdpTestActivity.class,
            TcpTestActivity.class,
            AsyncTaskTestActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initViews();
//        隐式启动AIDL Service，Service在单独的一个进程中，不能直接通信
//        使用AIDL进行交互
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.derik.demo.action");
        serviceIntent.setPackage("com.derik.demo");
        startService(serviceIntent);
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecycleViewAdapter adapter = new RecycleViewAdapter(targetNames, targetDescs);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                targetIntent = new Intent(NetActivity.this, targetClasses[position]);
                startActivity(targetIntent);
            }
        });
    }

}

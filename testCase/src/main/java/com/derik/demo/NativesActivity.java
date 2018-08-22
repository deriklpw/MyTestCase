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

import com.derik.demo.jni.JniTestActivity;

public class NativesActivity extends Activity {

    private static final String TAG = "NativesActivity";
    private Intent targetIntent;

    private String[] targetNames = new String[] {
            "JNI"
    };
    private String[] targetDescs = new String[]{
            "JNI Test"
    };
    private Class<?>[] targets = new Class[]{
            JniTestActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natives);
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecycleViewAdapter adapter = new RecycleViewAdapter(targetNames, targetDescs);
        recyclerView.setAdapter(adapter);
        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                targetIntent = new Intent(NativesActivity.this, targets[position]);
                startActivity(targetIntent);
            }
        });
    }
}

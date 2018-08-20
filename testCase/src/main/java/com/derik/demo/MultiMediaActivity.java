package com.derik.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.derik.demo.media.camera.CameraTestActivity;
import com.derik.demo.media.camera.ScreenCaptureActivity;
import com.derik.demo.media.install_tts.SystemVideoActivity;
import com.derik.demo.media.soundpool.SoundPoolTestActivity;

public class MultiMediaActivity extends Activity {

    private static final String TAG = "MultiMediaActivity";
    private Intent targetIntent;

    private String[] targetNames = {
            "Camera",
            "TTS",
            "SoundPool",
            "Camera",
            "Capture picture"
    };
    private String[] targetDescs = {
            "Camera Test",
            "TTS Test",
            "SoundPool Test",
            "拍照",
            "屏幕截图"
    };
    private Class<?>[] targets = {
            CameraTestActivity.class,
            SystemVideoActivity.class,
            SoundPoolTestActivity.class,
            CameraTestActivity.class,
            ScreenCaptureActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
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
        recyclerView.addItemDecoration(new ItemDivider().setDividerColor(Color.GRAY).setDividerWith(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                targetIntent = new Intent(MultiMediaActivity.this, targets[position]);
                startActivity(targetIntent);

            }
        });
    }

}

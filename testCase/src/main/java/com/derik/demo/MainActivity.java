package com.derik.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.derik.library.utils.Log;
import com.derik.library.view.MsgToast;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Intent targetIntent;
    private ActionBar actionBar;
    private String[] targetNames = new String[]{
            "Storage",
            "Views",
            "MultiMedia",
            "Net",
            "Natives",
            "Others",
    };
    private Class<?>[] targetClasses = new Class[]{
            StorageActivity.class,
            ViewsActivity.class,
            MultiMediaActivity.class,
            NetActivity.class,
            NativesActivity.class,
            OthersActivity.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton actionBarEnable = (ToggleButton) findViewById(R.id.main_action_bar_toggle);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBarEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    actionBar.show();
                } else {
                    actionBar.hide();
                }
            }
        });
        initViews();
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

        RecycleViewAdapter adapter = new RecycleViewAdapter(targetNames, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                targetIntent = new Intent(MainActivity.this, targetClasses[position]);
                if (position == 0) {
                    startActivityForResult(targetIntent, 128);
                } else {
                    startActivity(targetIntent);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == resultCode) {
            MsgToast.show(this, "" + resultCode);
            Log.i("ActivityResult", "" + resultCode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

//    public void triggerDrawOverlaysPermission() {
//        if (Utils.isAndroidM()) {
//            if (!Settings.canDrawOverlays(getContext())) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                getContext().startActivity(intent);
//            }
//        }
//    }

}

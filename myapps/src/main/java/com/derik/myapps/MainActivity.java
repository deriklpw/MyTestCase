package com.derik.myapps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mData;
    private RecyclerAdapter recycleAdapter;
    private Intent intent;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_list_view);

        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 点格排列
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        //设置分隔线

//        recyclerView.addItemDecoration( new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        recyclerView.addItemDecoration(new ItemDivider().setDividerWith(2).setDividerColor(Color.BLUE));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置Adapter
        initData();
        recycleAdapter = new RecyclerAdapter(MainActivity.this, mData);

        recyclerView.setAdapter(recycleAdapter);

        //使自定义方法和监听器
        recycleAdapter.setOnClickListener(new RecyclerListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch(position){
                    case 0:
                        intent = new Intent(MainActivity.this, FirstGroupActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                    default:
                        break;
                }
            }
        });

        recycleAdapter.setOnLongClickListener(new RecyclerListener.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                Log.e("onLongClick", "" + position);
            }
        });

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("Group: " + i);
        }
    }
} 
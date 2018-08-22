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

import com.derik.demo.views.PercentLayoutActivity;
import com.derik.demo.views.attribute.AttributeTest;
import com.derik.demo.views.fragment.FragmentTestMain;
import com.derik.demo.views.gesture.GestureAddActivity;
import com.derik.demo.views.gesture.GestureTestActivity;
import com.derik.demo.views.graph.GraphTestActivity;
import com.derik.demo.views.resource.MenuTestActivity;
import com.derik.demo.views.sensor.SensorTestActivity;
import com.derik.demo.views.component.AutoCompleteTextViewTest;
import com.derik.demo.views.viewpager.TabbedTestActivity;
import com.derik.demo.views.viewpager.TabHostActivity;
import com.derik.demo.views.viewpager.ViewPagerTabStripActivity;

public class ViewsActivity extends Activity {

    private static final String TAG = "ViewsActivity";
    private Intent targetIntent;

    private String[] targetNames = new String[]{
            "AutoCompleteTextView",
            "ViewPager TabStrip",
            "ViewPager Frag",
            "TabHost",
            "SensorManager",
            "Menu",
            "graphic",
            "Attribute",
            "Fragment",
            "GestureDetector",
            "Gesture",
            "PercentLayout"
    };

    private String[] targetDescs = new String[]{
            "提示编辑框",
            "ViewPager and PagerTabStrip",
            "ViewPager and Fragment",
            "TabHost",
            "传感器",
            "optionMenu, contextMenu",
            "graph anim",
            "Attribute",
            "Fragment test",
            "GestureDetector Test",
            "Gesture Add and Recognize",
            "百分比布局"
    };
    private Class<?>[] targetClasses = new Class[]{
            AutoCompleteTextViewTest.class,
            ViewPagerTabStripActivity.class,
            TabbedTestActivity.class,
            TabHostActivity.class,
            SensorTestActivity.class,
            MenuTestActivity.class,
            GraphTestActivity.class,
            AttributeTest.class,
            FragmentTestMain.class,
            GestureTestActivity.class,
            GestureAddActivity.class,
            PercentLayoutActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
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

        RecycleViewAdapter adapter = new RecycleViewAdapter(targetNames, targetDescs);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                targetIntent = new Intent(ViewsActivity.this, targetClasses[position]);
                startActivity(targetIntent);
            }
        });
    }

}

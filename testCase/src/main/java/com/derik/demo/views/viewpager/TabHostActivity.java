package com.derik.demo.views.viewpager;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.derik.demo.R;

public class TabHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("已接电话")
                .setContent(R.id.tab01);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
                .setIndicator("呼出电话", getResources().getDrawable(R.drawable.ic_launcher))
                .setContent(R.id.tab02);
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
                .setIndicator("未接电话")
                .setContent(R.id.tab03);
        tabHost.addTab(tab3);

    }
}

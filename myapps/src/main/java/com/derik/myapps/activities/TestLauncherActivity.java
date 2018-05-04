package com.derik.myapps.activities;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.derik.myapps.component.EventViewActivity;
import com.derik.myapps.component.ToolbarMenuActivity;

public class TestLauncherActivity extends LauncherActivity {

    private String[] name = {"EventViewActivity", "ToolbarMenuActivity"};
    private Class<?>[] clazzs = {EventViewActivity.class, ToolbarMenuActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected Intent intentForPosition(int position) {

        return new Intent(TestLauncherActivity.this, clazzs[position]);
    }
}

package com.derik.myapps.component;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.derik.myapps.R;

public class EventViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        System.out.println("create");
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    @Override
    public void onConfigurationChanged(Configuration newCfg){
        super.onConfigurationChanged(newCfg);
        String screen = newCfg.orientation == Configuration.ORIENTATION_LANDSCAPE?"横向屏幕":"纵向屏幕";
        Toast.makeText(this, "系统的屏幕方向发生改变"+"\n修改后的屏幕方向为"+screen,Toast.LENGTH_SHORT).show();

    }
}

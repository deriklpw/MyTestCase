package com.derik.demo.others;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.derik.demo.OthersActivity;
import com.derik.demo.R;

public class MultiProcessActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_process);
        textView = findViewById(R.id.textView1);
        multiprocess();
    }

    private void multiprocess() {
        int pid = android.os.Process.myPid();
        Log.i("MultiProcessTest_1", OthersActivity.isChecked + ", myId=" + pid);
        String processNameString = "";
        ActivityManager mActivityManager = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processNameString = appProcess.processName;
            }
            Log.i("MultiProcessTest_1", processNameString);
        }

        if (processNameString != null) {
            textView.setText(processNameString);
            textView.append(": " + OthersActivity.isChecked);
        }
    }

}

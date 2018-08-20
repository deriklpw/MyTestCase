package com.derik.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.derik.demo.others.DesktopTestActivity;
import com.derik.demo.media.install_tts.SystemVideoActivity;
import com.derik.demo.media.soundpool.SoundUtils;
import com.derik.demo.others.BroadcastPermissionActivity;
import com.derik.demo.others.MultiProcessActivity;
import com.derik.demo.tools.MessageDialog;
import com.derik.library.view.MsgToast;

public class OthersActivity extends Activity {
    private static final String TAG = "OthersActivity";
    private Intent targetIntent;
    private String msg;
    private String[] targetNames = {"BroadcastPermission",
            "Intent filter",
            "Dots",
            "Heap",
            "Finish",
            "SystemVideo",
            "MultiProcess",
            "DeskTop"
    };
    private String[] targetDescs = {"broadcast permission Test",
            "Start activity by intent filter",
            "像素计算",
            "HeapSize",
            "Finish with result",
            "SystemVideo",
            "两个Activity不在一个进程中",
            "桌面快捷方式"
    };

    public static boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
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
                switch (position) {
                    case 0:
                        SoundUtils.play(OthersActivity.this, SoundUtils.SOUND_UI2);
                        targetIntent = new Intent(OthersActivity.this, BroadcastPermissionActivity.class);
                        startActivity(targetIntent);
                        break;
                    case 1:
                        // JavaCodeDefView
                        targetIntent = new Intent();

                        //①单考虑组件的action和category，组件这两者要求大于intent的设置（不能只含category），该intent既可以启动组件
                        //单考虑组件含有action时，同上
                        targetIntent.setAction("android.intent.action.myaction1");
                        //单考虑组件含有的category，启动的intent只设置category时，并不能启动组件，需结合action使用
                        targetIntent.addCategory("android.intent.category.mycategory1");
                        targetIntent.addCategory("android.intent.category.mycategory2");

                        //目标组件含有data时，启动intent的data必须大于组件要求
                        targetIntent.setDataAndType(Uri.parse("http://192.168.0.100:8080/file_server/list_page"), "application/json");
                        startActivity(targetIntent);
                        break;
                    case 2:
                        try {
                            Runtime.getRuntime().exec("logcat -c");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Point point = new Point();
                        // 获取可用size
                        getWindowManager().getDefaultDisplay().getSize(point);
                        Log.i(TAG, "the screen size is " + point.toString() + "; point.x " + point.x);
                        msg = "the screen size is " + point.toString() + "; point.x " + point.x + "\n\n";

                        //获取真实的size
                        getWindowManager().getDefaultDisplay().getRealSize(point);
                        Log.i(TAG, "the screen real size is " + point.toString() + "; point.y " + point.y);
                        msg += "the screen real size is " + point.toString() + "; point.y " + point.y + "\n\n";

                        //获取屏幕像素密度相关
//                        DisplayMetrics dis1 = new DisplayMetrics();
//                        DisplayMetrics dis2 = new DisplayMetrics();
//                        getWindowManager().getDefaultDisplay().getRealMetrics(dis1);//全部
//                        getWindowManager().getDefaultDisplay().getMetrics(dis2);//可用

                        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();//可用
                        Log.i(TAG, "Density is " + displayMetrics.density + "; DensityDpi is " + displayMetrics.densityDpi + ": height: " + displayMetrics.heightPixels +
                                ", width: " + displayMetrics.widthPixels);

                        msg += "Density is " + displayMetrics.density + "; DensityDpi is " + displayMetrics.densityDpi + ": height: " + displayMetrics.heightPixels +
                                ", width: " + displayMetrics.widthPixels + "\n\n";

                        //计算尺寸
                        getScreenSizeOfDevice2();
                        break;
                    case 3:
                        Runtime rt=Runtime.getRuntime();
                        long maxMemory=rt.maxMemory();

                        Log.i("heapTotalMemory:",Long.toString(rt.totalMemory()/(1024*1024))+"MB");
                        Log.i("heapFreeMemory:",Long.toString(rt.freeMemory()/(1024*1024))+"MB");
                        Log.i("heapMaxMemory:",Long.toString(maxMemory/(1024*1024))+"MB");
                        MsgToast.show(OthersActivity.this, "heapSizeLimit:"+Long.toString(maxMemory/(1024*1024))+"MB");
                        break;
                    case 4:
                        MsgToast.show(OthersActivity.this, position + " id:" + position);
                        setResult(128, getIntent());
                        finish();
                        break;
                    case 5:
                        targetIntent = new Intent(OthersActivity.this, SystemVideoActivity.class);
                        startActivity(targetIntent);
                        break;
                    case 6:
                        // 测试多进程间不能交互数据
                        multiprocess();
                        break;
                    case 7:
                        targetIntent = new Intent(OthersActivity.this, DesktopTestActivity.class);
                        startActivity(targetIntent);
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
                    case 17:
                        break;
                    case 18:
                        break;
                    case 19:
                        break;
                    case 20:
                        break;
                    default:
                }
            }
        });
    }

    //多进程测试
    private void multiprocess() {
        // 当前类在进程一中
        isChecked = true;
        int pid = android.os.Process.myPid();
        Log.i("MultiProcess", isChecked + ", myId=" + pid);
        String processNameString = "";
        ActivityManager mActivityManager = (ActivityManager) this
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processNameString = appProcess.processName;
            }
            Log.i("MultiProcess", processNameString);
        }
        // 此Activity在第二个进程中，如果为isChecked为false，说明不能交互
        Intent mIntent = new Intent(this, MultiProcessActivity.class);
        startActivity(mIntent);
    }

    private void getScreenSizeOfDevice2() {
        //方是一：
        Display display = getWindowManager().getDefaultDisplay();

        Point point = new Point();
        display.getRealSize(point);

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        //方式二：
        // 获取，此方式不如方是一使用广
        // DisplayMetrics dm = getResources().getDisplayMetrics();

        //计算x轴上的尺寸，再平方
        double x = Math.pow(point.x / dm.xdpi, 2);
        //计算y轴上的尺寸，再平方
        double y = Math.pow(point.y / dm.ydpi, 2);
        //x+y求根，得出对角线尺寸
        double screenInches = Math.sqrt(x + y);
        Log.i(TAG, "dm.xdpi: " + dm.xdpi + " dm.ydpi: " + dm.ydpi + " " + point.toString());

        msg += "dm.xdpi: " + dm.xdpi + " dm.ydpi: " + dm.ydpi + " " + point.toString() + "\n\n";
        Log.i(TAG, "Screen inches : " + screenInches);
        msg += "Screen inches : " + screenInches + "\n\n";
        MessageDialog.show(OthersActivity.this, msg);
    }

    private void getDisplayInfomation() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(TAG, "the screen size(not real size) is " + point.toString());
    }

    //pixel = dip*density;
    private int convertDpToPixel(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.density + 0.5f);
    }

    private int convertPixelToDp(int pixel) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (pixel / displayMetrics.density + 0.5f);
    }

}

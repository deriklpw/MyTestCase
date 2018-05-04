package com.derik.demo;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.derik.demo.a_first.DrawableTest;
import com.derik.demo.a_first.fragment.FragmentTestMain;
import com.derik.demo.a_first.attribute.AttributeTest;
import com.derik.demo.a_first.gesture.GestureAddActivity;
import com.derik.demo.a_first.gesture.GestureTestActivity;
import com.derik.demo.a_first.soundpool.SoundUtils;
import com.derik.demo.a_first.sqlite.SQLiteActivity;
import com.derik.demo.a_first.filesharepre.SharePreferencesFileActivity;
import com.derik.demo.a_first.soundpool.SoundPoolTest;
import com.derik.demo.a_first.install_tts.SystemVideo;
import com.derik.demo.others.BroadcastPermissionActivity;
import com.derik.demo.tools.BitmapTools;
import com.derik.demo.tools.MessageDialog;
import com.derik.demo.tools.MsgToast;

public class FirstTestCaseActivity extends Activity {

    protected static final String TAG = FirstTestCaseActivity.class.getName();
    private ListView listView;
    private String[] text1;
    private String[] textString2;
    private Intent intent;
    public static boolean isChecked = false;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_listview);
        initView();
        Message msg = Message.obtain();
        // 测试多进程间不能交互数据
//         multiprocess();
    }

    //多进程测试
    private void multiprocess(){
        // 当前类在进程一中
        isChecked = true;
        Log.i("MultiProcess", isChecked + "");
        int pid = android.os.Process.myPid();
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
        Intent mIntent = new Intent(this, SecondTestCaseActivity.class);
        startActivity(mIntent);
    }

    protected void initView() {
        text1 = new String[]{"BroadcastPermissionActivity", "ViewActivity", "SharePreferences/IO", "SQLite",
                "Dots", "Attribute", "Heap", "finish", "fragment", "Animator", "GestureDetector", "systemVideo", "SoundPool", "Gesture",
                "Percent", "15", "16", "17", "18", "19", "20"};
        textString2 = new String[]{"broadcast permission", "View test",
                "SharePreferences and file", "SQLite and SQLiteOpenHelper",
                "像素计算", "Attribute Resources", "HeapSize", "ActivityForResult", "Fragment Test", "ClipDrawable, AnimationDrawable, Animator使用", "GestureDetector Test", "systemVideo", "SoundPool", "Gesture Add and Recognize",
                "Layout", "15", "16", "17", "18", "19", "20"};

        // layoutInflater = getLayoutInflater();
        // linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.activity_listview, null);
        // listView = (ListView)linearLayout.findViewById(R.id.listview1);

        listView = (ListView) findViewById(R.id.listview1);
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("clicked" + position + " id:" + id);
                switch (position) {
                    case 0:
                        SoundUtils.play(FirstTestCaseActivity.this, SoundUtils.SOUND_UI2);
                        intent = new Intent(FirstTestCaseActivity.this,
                                BroadcastPermissionActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        // JavaCodeDefView
                        intent = new Intent();

                        //①单考虑组件的action和category，组件这两者要求大于intent的设置（不能只含category），该intent既可以启动组件
                        //单考虑组件含有action时，同上
                        intent.setAction("android.intent.action.myaction1");
                        //单考虑组件含有的category，启动的intent只设置category时，并不能启动组件，需结合action使用
                        intent.addCategory("android.intent.category.mycategory1");
                        intent.addCategory("android.intent.category.mycategory2");

                        //目标组件含有data时，启动intent的data必须大于组件要求
                        intent.setDataAndType(Uri.parse("http://192.168.0.100:8080/file_server/list_page"), "application/json");
                        startActivity(intent);
                        break;

                    case 2:

                        intent = new Intent(FirstTestCaseActivity.this,
                                SharePreferencesFileActivity.class);
                        // 图片资源打包成流，随intent一同发送至目的组件
                        intent.putExtra("bitmapScreenshot", BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                        startActivity(intent);
                        break;

                    case 3:

                        intent = new Intent(FirstTestCaseActivity.this,
                                SQLiteActivity.class);
                        startActivity(intent);
                        break;

                    case 4:

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

                    case 5:

                        intent = new Intent(FirstTestCaseActivity.this,
                                AttributeTest.class);
                        startActivity(intent);
                        break;

                    case 6:
                        Runtime rt=Runtime.getRuntime();
                        long maxMemory=rt.maxMemory();

                        Log.i("heapTotalMemory:",Long.toString(rt.totalMemory()/(1024*1024))+"MB");
                        Log.i("heapFreeMemory:",Long.toString(rt.freeMemory()/(1024*1024))+"MB");
                        Log.i("heapMaxMemory:",Long.toString(maxMemory/(1024*1024))+"MB");
                        MsgToast.show(FirstTestCaseActivity.this, "heapSizeLimit:"+Long.toString(maxMemory/(1024*1024))+"MB");
                        break;

                    case 7:
                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        setResult(128, getIntent());
                        finish();
                        break;

                    case 8:

                        intent = new Intent(FirstTestCaseActivity.this, FragmentTestMain.class);
                        startActivity(intent);
                        break;

                    case 9:

                        intent = new Intent(FirstTestCaseActivity.this, DrawableTest.class);
                        startActivity(intent);
                        break;

                    case 10:

                        intent = new Intent(FirstTestCaseActivity.this, GestureTestActivity.class);
                        startActivity(intent);
                        break;

                    case 11:

                        intent = new Intent(FirstTestCaseActivity.this, SystemVideo.class);
                        startActivity(intent);
                        break;

                    case 12:

                        intent = new Intent(FirstTestCaseActivity.this, SoundPoolTest.class);
                        startActivity(intent);
                        break;

                    case 13:

                        intent = new Intent(FirstTestCaseActivity.this, GestureAddActivity.class);
                        startActivity(intent);
                        break;

                    case 14:

                        intent = new Intent(FirstTestCaseActivity.this, PercentLayoutActivity.class);
                        startActivity(intent);
                        break;

                    case 15:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    case 16:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    case 17:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    case 18:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    case 19:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    case 20:

                        MsgToast.show(FirstTestCaseActivity.this, position + " id:" + id);
                        break;

                    default:
                        break;
                }
            }
        });

        // listView.setOnItemSelectedListener(new OnItemSelectedListener() {
        // @Override
        // public void onItemSelected(AdapterView<?> parent, View view,
        // int position, long id) {
        // System.out.println("selected" + position);
        // }
        //
        // @Override
        // public void onNothingSelected(AdapterView<?> parent) {
        // System.out.println("nothing");
        // }
        // });
    }

    public class ListViewAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            System.out.println("getView " + position + " " + convertView);//调试语句
            LinearLayout layout;
            Holder holder;
            if (convertView == null){
                holder = new Holder();
                layout = new LinearLayout(FirstTestCaseActivity.this);
                LinearLayout layout2 = new LinearLayout(FirstTestCaseActivity.this);
                layout2.setOrientation(LinearLayout.VERTICAL); // 1
                layout.setOrientation(LinearLayout.HORIZONTAL); // 0

                ImageView image1 = new ImageView(FirstTestCaseActivity.this);
                //缩小图像后显示
                BitmapTools bitmapTools = new BitmapTools();
                Bitmap bitmap =bitmapTools.inSampleSize(getApplicationContext(), R.drawable.ic_launcher, Bitmap.Config.RGB_565, 50, 50);

                image1.setImageBitmap(bitmap);
                TextView textview1 = new TextView(FirstTestCaseActivity.this);
                TextView textview2 = new TextView(FirstTestCaseActivity.this);
                textview1.setTag("textView1");
                textview1.setText(text1[position]);
                textview2.setTag("textView2");
                textview2.setText(textString2[position]);
                textview1.setTextSize(20);
                textview2.setTextSize(20);
                textview1.setTextColor(Color.RED);
                textview2.setTextColor(Color.BLUE);
                layout.addView(image1);
                layout2.addView(textview1);
                layout2.addView(textview2);
                layout.addView(layout2);
                // 将后面会修改数据的组件保存起来
                holder.textView1 = textview1;
                holder.textView2 = textview2;
                layout.setTag(holder);
            } else {

                holder = (Holder) convertView.getTag();
                // 修改之前保存的组件的内容，从而达到改变view的内容
                holder.textView1.setText(text1[position]);
                holder.textView2.setText(textString2[position]);
                return convertView;

                // 此方法依据先找到组件，再修改组件内容
//                layout = (LinearLayout) convertView;
//                TextView textView1 = (TextView)layout.findViewWithTag("textView1");
//                textView1.setText(text1[position]);
//                TextView textView2 = (TextView)layout.findViewWithTag("textView2");
//                textView2.setText(textString2[position]);

            }

            return layout;
        }

        class Holder{
            TextView textView1;
            TextView textView2;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return text1.length;
        }
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
        MessageDialog.show(FirstTestCaseActivity.this, msg);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

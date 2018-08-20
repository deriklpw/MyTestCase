package com.derik.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.derik.demo.storage.DrawableTest;
import com.derik.demo.storage.filesharepre.SharePreferencesFileActivity;
import com.derik.demo.storage.sqlite.SQLiteActivity;
import com.derik.library.utils.BitmapTools;

public class StorageActivity extends Activity {

    protected static final String TAG = "StorageActivity";
    private ListView listView;
    private String[] text1;
    private String[] textString2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_listview);
        initView();
    }

    protected void initView() {
        text1 = new String[]{
                "SharePreferences/IO",
                "SQLite",
                "Animator"
        };
        textString2 = new String[]{
                "SharePreferences and file",
                "SQLite and SQLiteOpenHelper",
                "ClipDrawable, AnimationDrawable, Animator使用"
        };


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
                        intent = new Intent(StorageActivity.this,
                                SharePreferencesFileActivity.class);
                        // 图片资源打包成流，随intent一同发送至目的组件
                        intent.putExtra("bitmapScreenshot", BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(StorageActivity.this,
                                SQLiteActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(StorageActivity.this, DrawableTest.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    public class ListViewAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            System.out.println("getView " + position + " " + convertView);//调试语句
            LinearLayout layout;
            Holder holder;
            if (convertView == null) {
                holder = new Holder();
                layout = new LinearLayout(StorageActivity.this);
                LinearLayout layout2 = new LinearLayout(StorageActivity.this);
                layout2.setOrientation(LinearLayout.VERTICAL); // 1
                layout.setOrientation(LinearLayout.HORIZONTAL); // 0

                ImageView image1 = new ImageView(StorageActivity.this);
                //缩小图像后显示
                BitmapTools bitmapTools = BitmapTools.getInstance();
                Bitmap bitmap = bitmapTools.inSampleSize(getApplicationContext(), R.drawable.ic_launcher, Bitmap.Config.RGB_565, 50, 50);

                image1.setImageBitmap(bitmap);
                TextView textview1 = new TextView(StorageActivity.this);
                TextView textview2 = new TextView(StorageActivity.this);
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

        class Holder {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

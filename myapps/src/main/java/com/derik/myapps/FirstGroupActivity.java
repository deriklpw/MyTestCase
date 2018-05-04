package com.derik.myapps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.derik.myapps.activities.TestLauncherActivity;
import com.derik.myapps.activities.TestSettingsActivity;
import com.derik.myapps.attribute.AttributeActivity;
import com.derik.myapps.component.CommonButton;
import com.derik.myapps.component.ToolbarMenuActivity;
import com.derik.myapps.component.WeightActivity;
import com.derik.myapps.eventbus.LoginActivity;
import com.derik.myapps.fragment.FragmentActivity;
import com.derik.myapps.graphics.BitmapActivity;
import com.derik.myapps.graphics.ComBtnActivity;

public class FirstGroupActivity extends AppCompatActivity {

    private Intent intent;
    private final String[] items = {"Def view", "Attribute", "LinearLayoutWeight", "Fragment", "Toolbar", "Bitmap",
            "LauncherActivity", "EventBus", "SettingActivity", "ViewDef", "text10", "text11", "text12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        init();
        System.out.println("FirstGroupActivity.onCreate" + checkDeviceHasNavigationBar());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean checkDeviceHasNavigationBar() {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(this)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.listView);
        MyListViewAdapter listAdapter = new MyListViewAdapter();
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                System.out.println("adapter item:" + parent.getItemAtPosition(position));
//                System.out.println("adapter view:" + ((TextView)view.findViewById(R.id.list_line_text)).getText());
                switch(position){
                    case 0:
                        intent = new Intent();
                        intent.setAction("android.intent.action.actionTest");
//                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setDataAndType(Uri.parse("http://10.191.131.12:80/test"),"test/test");

                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(FirstGroupActivity.this, AttributeActivity.class);
                        startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(FirstGroupActivity.this, WeightActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(FirstGroupActivity.this, FragmentActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(FirstGroupActivity.this, ToolbarMenuActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(FirstGroupActivity.this, BitmapActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(FirstGroupActivity.this, TestLauncherActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(FirstGroupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(FirstGroupActivity.this, TestSettingsActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(FirstGroupActivity.this, ComBtnActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        System.out.println("clicked:" + position);
                        break;
                    case 11:
                        System.out.println("clicked:" + position);
                        break;
                    case 12:
                        System.out.println("clicked:" + position);
                        break;
                    case 13:
                        System.out.println("clicked:" + position);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return "123";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("getView " + position + " " + convertView);//调试语句

            Holder holder;
            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.listview_line, parent, false);
                holder = new Holder();
                holder.textView = (TextView) convertView.findViewById(R.id.list_line_text);

                convertView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();

            }

            holder.textView.setText(items[position]);
            return convertView;
        }
    }

    class Holder {
        TextView textView;
    }

}

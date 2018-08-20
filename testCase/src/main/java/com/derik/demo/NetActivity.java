package com.derik.demo;

import android.app.Activity;
import android.content.Intent;
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

import com.derik.demo.net.AIDLActivity;
import com.derik.demo.net.Async.AsyncTaskTestActivity;
import com.derik.demo.net.WebViewTestActivity;
import com.derik.demo.net.socket.TcpTestActivity;
import com.derik.demo.net.socket.UdpTestActivity;
import com.derik.demo.net.url.HttpURLTestActivity;
import com.derik.demo.net.url.URLTestActivity;

public class NetActivity extends Activity {

    private ListView listView;
    private String[] text1;
    private String[] text2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();
        //隐式启动AIDL Service，Service在单独的一个进程中，不能直接通信
        //使用AIDL进行交互
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.derik.demo.action");
        serviceIntent.setPackage("com.derik.demo");
        startService(serviceIntent);
    }

    protected void initView() {
        text1 = new String[]{
                "WebView",
                "URL",
                "HTTP",
                "AIDL",
                "UDP",
                "TCP",
                "AsyncTask"
        };

        text2 = new String[]{
                "WebView使用实例",
                "URLTestActivity",
                "HttpURLConnection",
                "AIDL",
                "UDP Test",
                "TCP Test",
                "Download With AsyncTask"
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
                        intent = new Intent(NetActivity.this, WebViewTestActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(NetActivity.this, URLTestActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(NetActivity.this, HttpURLTestActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(NetActivity.this, AIDLActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(NetActivity.this, UdpTestActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(NetActivity.this, TcpTestActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(NetActivity.this, AsyncTaskTestActivity.class);
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
            LinearLayout layout = new LinearLayout(NetActivity.this);
            LinearLayout layout2 = new LinearLayout(NetActivity.this);
            layout2.setOrientation(LinearLayout.VERTICAL);  //1
            layout.setOrientation(LinearLayout.HORIZONTAL);  //0

            ImageView image1 = new ImageView(NetActivity.this);
            image1.setImageResource(R.drawable.ic_launcher);
            TextView textview1 = new TextView(NetActivity.this);
            TextView textview2 = new TextView(NetActivity.this);
            textview1.setText(text1[position]);
            textview2.setText(text2[position]);
            textview1.setTextSize(20);
            textview2.setTextSize(20);
            textview1.setTextColor(Color.RED);
            textview2.setTextColor(Color.BLUE);
            layout.addView(image1);
            layout2.addView(textview1);
            layout2.addView(textview2);
            layout.addView(layout2);
            return layout;
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

    ;

}

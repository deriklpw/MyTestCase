package com.derik.demo;

import com.derik.demo.b_second.AIDLActivity;
import com.derik.demo.b_second.camera.CameraTest;
import com.derik.demo.b_second.camera.ScreenCaptureActivity;
import com.derik.demo.b_second.socket.ClientActivity;
import com.derik.demo.b_second.DesktopTest;
//import com.derik.demo.b_second.httpclient.HttpClientTest;
import com.derik.demo.b_second.WebViewTest;
import com.derik.demo.b_second.url.HttpURLTest;
import com.derik.demo.b_second.url.URLTest;
import com.derik.demo.tools.MsgToast;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SecondTestCaseActivity extends Activity {
	
	private ListView listView;
	private String[] text1;
	private String[] text2;
	private Intent intent;
//	private LayoutInflater layoutInflater ;
//	private LinearLayout linearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initView();
//		 multiprocess();
		Intent serviceIntent = new Intent();
		serviceIntent.setAction("com.derik.demo.action");
		serviceIntent.setPackage("com.derik.demo");
		startService(serviceIntent);
		
	}

	private void multiprocess(){
		Log.i("MultiProcessTest_1", FirstTestCaseActivity.isChecked + "");
		int pid = android.os.Process.myPid();
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
	}

	protected void initView() {
		text1 = new String[] {"TCP/IP", "HttpClient", "WebView", "DesktopTest", "URL", "HTTP",
				"AIDL", "Camera", "Capture picture"};
		text2 = new String[] {"使用示例", "HttpClient使用示例", "WebView使用实例", "桌面快捷方式", "URLTest", "HttpURLConnection",
				"AIDL", "拍照", "屏幕截图"};
		//layoutInflater = getLayoutInflater();
		//linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.activity_listview, null);
		//listView = (ListView)linearLayout.findViewById(R.id.listview1);
		
		listView = (ListView)findViewById(R.id.listview1);
		ListViewAdapter listViewAdapter = new ListViewAdapter();
		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("clicked" + position + " id:" + id);
				switch (position) {
				case 0:

					intent = new Intent(SecondTestCaseActivity.this, ClientActivity.class);
					startActivity(intent);
					
					break;
				case 1:
//					intent = new Intent(SecondTestCaseActivity.this, HttpClientTest.class);
//					startActivity(intent);
					
					break;
				case 2:
					intent = new Intent(SecondTestCaseActivity.this, WebViewTest.class);
					startActivity(intent);
					
					break;
				case 3:
					intent = new Intent(SecondTestCaseActivity.this, DesktopTest.class);
					startActivity(intent);

					break;
				case 4:
					intent = new Intent(SecondTestCaseActivity.this, URLTest.class);
					startActivity(intent);

					break;
				case 5:
					intent = new Intent(SecondTestCaseActivity.this, HttpURLTest.class);
					startActivity(intent);

					break;
				case 6:
					intent = new Intent(SecondTestCaseActivity.this, AIDLActivity.class);
					startActivity(intent);

					break;
				case 7:
					intent = new Intent(SecondTestCaseActivity.this, CameraTest.class);
					startActivity(intent);

					break;
				case 8:
					intent = new Intent(SecondTestCaseActivity.this, ScreenCaptureActivity.class);
					startActivity(intent);

					break;
				case 9:
					MsgToast.show(SecondTestCaseActivity.this,  position + " id:" + id);
					break;
					
				default:
					break;
				}
			}
		});

//		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				System.out.println("selected" + position);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				System.out.println("nothing");
//			}
//		});
	}
	
	public class ListViewAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LinearLayout layout = new LinearLayout(SecondTestCaseActivity.this);
			LinearLayout layout2 = new LinearLayout(SecondTestCaseActivity.this);
			layout2.setOrientation(LinearLayout.VERTICAL);  //1
			layout.setOrientation(LinearLayout.HORIZONTAL);  //0
			
			ImageView image1 = new ImageView(SecondTestCaseActivity.this);
			image1.setImageResource(R.drawable.ic_launcher);
			TextView textview1 = new TextView(SecondTestCaseActivity.this);
			TextView textview2 = new TextView(SecondTestCaseActivity.this);
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
	};

}
